package com.myform.test.dto.mapper;

import com.myform.test.dto.FormDtoResponse;
import com.myform.test.model.Form;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface FormDtoMapper {

    FormDtoResponse toFormDtoResponse (Form entity);

}
