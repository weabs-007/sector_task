package com.myform.test.dto.mapper;

import com.myform.test.dto.SectorDto;
import com.myform.test.model.Sector;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface SectorDtoMapper {

    List<SectorDto> toSectorDtoCollection(List<Sector> entities);

}
