package com.myform.test.service.impl;

import com.myform.test.dto.FormDtoRequest;
import com.myform.test.exception.FormNotFoundException;
import com.myform.test.model.Form;
import com.myform.test.model.Sector;
import com.myform.test.service.FormService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class FormServiceImplTest {

    @Mock
    private FormService service;

    private Form response;
    private FormDtoRequest request;

    @BeforeEach
    void init() {
        Sector sector = new Sector()
                .setName("TestSector")
                .setSectors(new ArrayList<>())
                .setParentSectorName(null);
        request = new FormDtoRequest()
                .setUsername("TestUsername1")
                .setAgreement(true)
                .setSectorsId(Collections.singleton(1));
        response = new Form()
                .setUsername("TestUsername1")
                .setAgreement(true)
                .setSectors(Collections.singleton(sector));
    }


    @Test
    void create() {

        when(service.create(request)).thenReturn(response);

        Form actualForm = service.create(request);

        verify(service, times(1)).create(request);
        verifyNoMoreInteractions(service);

        assertEquals(request.getUsername(), actualForm.getUsername());
        assertEquals(request.getAgreement(), actualForm.getAgreement());

    }

    @Test
    void update() throws FormNotFoundException {
        Long formId = 1L;

        when(service.update(formId, request)).thenReturn(response);

        Form form3 = service.update(formId, request);

        assertEquals(form3, response);

    }

    @Test
    void getFormById() throws FormNotFoundException {
        Long formId = 1L;

        when(service.getFormById(formId)).thenReturn(response);

        Form actualForm = service.getFormById(formId);

        verify(service, times(1)).getFormById(formId);
        verifyNoMoreInteractions(service);
        assertEquals(actualForm, response);

    }
}