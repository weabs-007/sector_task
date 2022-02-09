package com.myform.test.service;

import com.myform.test.dto.FormDtoRequest;
import com.myform.test.dto.FormDtoResponse;
import com.myform.test.exception.FormNotFoundException;
import com.myform.test.model.Form;

public interface FormService {

    Form create(FormDtoRequest newFormDto);
    Form update(Long id, FormDtoRequest updatedFormDto) throws FormNotFoundException;
    Form getFormById(Long id) throws FormNotFoundException;
}
