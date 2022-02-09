package com.myform.test.service.impl;

import com.myform.test.model.Sector;
import com.myform.test.repository.SectorRepository;
import com.myform.test.service.SectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SectorServiceImpl implements SectorService {

    private final SectorRepository sectorRepository;

    @Override
    public List<Sector> getAll() {
        return sectorRepository.findAllByParentSectorNameNull();
    }
}
