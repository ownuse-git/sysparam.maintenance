package com.example.sysparam.maintenance.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.example.sysparam.maintenance.dto.SysParamDetailDto;
import com.example.sysparam.maintenance.dto.SysParamUpdateDto;
import com.example.sysparam.maintenance.entity.SystemParameter;

@Mapper(componentModel = "spring")
public interface SysParamDetailMapper {
	SysParamDetailDto toDto(SystemParameter entity);

	// Update entity using DTO — non-matching fields
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	// target in the @Mapping is referring to field in the entity (which is the final target)
	// so when the ignore for the entity is true, even the dto inside got value will also not update the entity values
	// secure to prevent changes of the key
	@Mapping(target = "channelId", ignore = true)
	@Mapping(target= "reasonCode", ignore = true)
	@Mapping(target = "statusCode", ignore = true)
	void updateEntityFromDto(SysParamUpdateDto dto, @MappingTarget SystemParameter entity);

	// Create new entity from DTO (for insert)
	@Mapping(target = "id", ignore = true)
	SystemParameter toEntity(SysParamDetailDto dto);
	
	SysParamUpdateDto toUpdateDto (SysParamDetailDto dto); 

}
