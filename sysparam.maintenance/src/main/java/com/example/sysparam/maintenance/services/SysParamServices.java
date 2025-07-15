package com.example.sysparam.maintenance.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sysparam.maintenance.dto.SysParamDetailDto;
import com.example.sysparam.maintenance.dto.SysParamListDto;
import com.example.sysparam.maintenance.dto.SysParamUpdateDto;
import com.example.sysparam.maintenance.entity.SystemParameter;
import com.example.sysparam.maintenance.exception.DuplicateParameterException;
import com.example.sysparam.maintenance.exception.RecordNotFound;
import com.example.sysparam.maintenance.mapper.SysParamDetailMapper;
import com.example.sysparam.maintenance.mapper.SysParamListMapper;
import com.example.sysparam.maintenance.repository.SysParamRepository;

import jakarta.transaction.Transactional;

@Service
public class SysParamServices {

	@Autowired
	private SysParamRepository repository;

	@Autowired
	private SysParamListMapper mapperL;

	@Autowired
	private SysParamDetailMapper mapperD;

	public SysParamListDto detailCardStatus(String channelId, String statusCode, String reasonCode) {

		Optional<SystemParameter> entity = Optional
				.of(repository.findByChannelIdAndStatusCodeAndReasonCode(channelId, statusCode, reasonCode)
						.orElseThrow(() -> new RecordNotFound("Record Not Found!")));

		SysParamListDto dto = mapperL.toDto(entity.get());

		// System.out.println(dto);

		return dto;
	}

	public List<SysParamListDto> listAllCardStatus(String channelId, String statusCode, String reasonCode) {
		List<SystemParameter> entity = repository.findWithCustomSearch(channelId, statusCode, reasonCode);

		List<SysParamListDto> dto = mapperL.toDtoList(entity);

		return dto;
	}

	@Transactional
	public SysParamDetailDto createNewCardStatus(SysParamDetailDto dto) {

		// Optional<SystemParameter> entity =
		// repository.findByChannelIdAndStatusCodeAndReasonCode(dto.getChannelId(),dto.getStatusCode(),dto.getReasonCode());

		SystemParameter entity;

		boolean dupData = repository
				.findByChannelIdAndStatusCodeAndReasonCode(dto.getChannelId(), dto.getStatusCode(), dto.getReasonCode())
				.isPresent();

		if (dupData) {
			throw new DuplicateParameterException("Duplicate parameter found for ChannelId: " + dto.getChannelId()
					+ ", StatusCode: " + dto.getStatusCode() + ", ReasonCode: " + dto.getReasonCode());
		} else {
			entity = mapperD.toEntity(dto);
			repository.save(entity);
		}

		SysParamDetailDto dto2 = mapperD.toDto(entity);

		return dto2;
	}

	@Transactional
	public SysParamDetailDto updateExistingCardStatus(String channelId, String reasonCode, String statusCode,
			SysParamDetailDto dto) {

		Boolean exist = repository.findByChannelIdAndStatusCodeAndReasonCode(channelId, statusCode, reasonCode)
				.isPresent();
		/* Boolean conflict; */

		if (!exist) {
			throw new RecordNotFound("Record Not Found for channelId: " + channelId + " ,statusCode: " + statusCode
					+ " ,reasonCode: " + reasonCode);

		}
		/*
		 * conflict = repository
		 * .findByChannelIdAndStatusCodeAndReasonCode(dto.getChannelId(),
		 * dto.getStatusCode(), dto.getReasonCode()) .isPresent();
		 */
		/* wrong logic 
		 * if (conflict) { throw new
		 * DuplicateParameterException("Duplicate parameter found for ChannelId: " +
		 * dto.getChannelId() + ", StatusCode: " + dto.getStatusCode() +
		 * ", ReasonCode: " + dto.getReasonCode()); }
		 */

		
		SysParamUpdateDto updateDto = mapperD.toUpdateDto(dto);
		
		
		// SystemParameter entity = mapperD.toEntity(dto);
		SystemParameter entity = repository.findByChannelIdAndReasonCodeAndStatusCode(channelId, reasonCode, statusCode);
		
		mapperD.updateEntityFromDto(updateDto, entity);
		
		repository.save(entity);

		SysParamDetailDto dtoReturn = mapperD.toDto(entity);

		return dtoReturn;

	}

	@Transactional
	public void deleteExistingCardStatus(String channelId, String reasonCode, String statusCode) {

		Optional<SystemParameter> entity = repository.findByChannelIdAndStatusCodeAndReasonCode(channelId, statusCode,
				reasonCode);
		if (entity.isEmpty()) {
			throw new RecordNotFound("Record Not Found for channelId: " + channelId + " ,statusCode: " + statusCode
					+ " ,reasonCode: " + reasonCode);
		}

		repository.deleteByChannelIdAndStatusCodeAndReasonCode(channelId, statusCode, reasonCode);
	}

}
