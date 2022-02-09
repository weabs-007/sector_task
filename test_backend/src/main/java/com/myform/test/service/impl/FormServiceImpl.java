package com.myform.test.service.impl;

import com.myform.test.dto.FormDtoRequest;
import com.myform.test.dto.mapper.FormDtoMapper;
import com.myform.test.exception.FormNotFoundException;
import com.myform.test.model.Form;
import com.myform.test.model.Sector;
import com.myform.test.repository.FormRepository;
import com.myform.test.repository.SectorRepository;
import com.myform.test.service.FormService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class FormServiceImpl implements FormService {

    private final FormRepository formRepository;
    private final SectorRepository sectorRepository;

    @Override
    public Form create(FormDtoRequest newFormDto) {

        // Create new form
        Form newForm = new Form()
                .setUsername(newFormDto.getUsername())
                .setAgreement(newFormDto.getAgreement())
                .setSectors(parseSectorIds(newFormDto));

        return formRepository.save(newForm);

    }

    @Override
    public Form update(Long id, FormDtoRequest updatedFormDto) throws FormNotFoundException {
        Optional<Form> formData = formRepository.findById(id);
        if (formData.isPresent()) {

            Set<Sector> sectors = parseSectorIds(updatedFormDto);

            Form fromDBForm = formRepository.findById(id)
                    .orElseThrow(() -> new FormNotFoundException("No form found with Id = " + id));
            fromDBForm.setUsername(updatedFormDto.getUsername());
            fromDBForm.setAgreement(updatedFormDto.getAgreement());
            fromDBForm.setSectors(sectors);

            return formRepository.save(fromDBForm);

        } else {
            throw new FormNotFoundException("Form not found with id : " + id);
        }
    }


    @Override
    public Form getFormById(Long id) throws FormNotFoundException {
        return formRepository.findById(id)
                .orElseThrow(() -> new FormNotFoundException("No form found with Id = " + id));
    }

    private Set<Sector> parseSectorIds(FormDtoRequest newFormDto) {
        Set<Integer> intSectors = newFormDto.getSectorsId();
        Set<Sector> sectors = new HashSet<>();

        for (int id:intSectors) {
            Sector sectorAdd = sectorRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Error: Sector is not found."));
            sectors.add(sectorAdd);
        }

        return sectors;
    }



}
