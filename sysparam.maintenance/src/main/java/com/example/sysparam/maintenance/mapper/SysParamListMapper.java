package com.example.sysparam.maintenance.mapper;
import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.example.sysparam.maintenance.dto.SysParamListDto;
import com.example.sysparam.maintenance.entity.SystemParameter;

@Mapper(componentModel = "spring")
public interface SysParamListMapper {


	    SysParamListDto toDto(SystemParameter entity);

	    // Update entity using DTO — does not touch ID or non-matching fields
	    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	    void updateEntityFromDto(SysParamListDto dto, @MappingTarget SystemParameter entity);

	    // Create new entity from DTO (for insert)
	    @Mapping(target = "id", ignore = true)
	    SystemParameter toEntity(SysParamListDto dto);
	    
	    
	    List<SysParamListDto> toDtoList(List<SystemParameter> entity);
	    
	    @Mapping(target = "id", ignore = true)
	    List<SystemParameter> toEntityList(List<SysParamListDto> dto);
}
