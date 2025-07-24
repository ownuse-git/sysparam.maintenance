package com.example.sysparam.maintenance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.sysparam.maintenance.dto.SysParamDetailDto;
import com.example.sysparam.maintenance.dto.SysParamListDto;
import com.example.sysparam.maintenance.services.SysParamServices;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

@RestController
@RequestMapping("/api")
@Validated
public class SysParamController {
	// Testing For Different Branches
	@Autowired
	private SysParamServices services;

	public SysParamController(SysParamServices services) {
		this.services = services;
	}

	@GetMapping("/tutorial/detail")
	public ResponseEntity<?> getDetail(@Valid @NotBlank @RequestParam String channelId,
			@Valid @NotBlank @RequestParam String reasonCode, @Valid @NotBlank @RequestParam String statusCode) {

		SysParamListDto dto = services.detailCardStatus(channelId, statusCode, reasonCode);

		return ResponseEntity.status(HttpStatus.OK).body(dto);
	}

	@GetMapping("/tutorial/list")
	public ResponseEntity<?> getListing(@RequestParam(required = false) String channelId,
			@RequestParam(required = false) String reasonCode, @RequestParam(required = false) String statusCode) {

		List<SysParamListDto> dto = services.listAllCardStatus(channelId, statusCode, reasonCode);

		return ResponseEntity.status(HttpStatus.OK).body(dto);
	}

	@PostMapping("/tutorial/new")
	public ResponseEntity<?> newRecord(@Valid @RequestBody SysParamDetailDto dto) {

		SysParamDetailDto dtoCreate = services.createNewCardStatus(dto);

		return ResponseEntity.status(HttpStatus.CREATED).body(dtoCreate);
	}

	@PutMapping("/tutorial/update")
	public ResponseEntity<?> updateRecord(@Valid @NotBlank @RequestParam String channelId,
			@Valid @NotBlank @RequestParam String reasonCode, @Valid @NotBlank @RequestParam String statusCode,
			@Valid @RequestBody SysParamDetailDto dto) {
		SysParamDetailDto dtoUpdate = services.updateExistingCardStatus(channelId, reasonCode, statusCode, dto);
		return ResponseEntity.status(HttpStatus.OK).body(dtoUpdate);

	}

	@DeleteMapping("/tutorial/delete")
	public ResponseEntity<?> deleteRecord(@Valid @NotBlank @RequestParam String channelId,
			@Valid @NotBlank @RequestParam String reasonCode, @Valid @NotBlank @RequestParam String statusCode) {
		services.deleteExistingCardStatus(channelId, reasonCode, statusCode);
		return ResponseEntity.status(HttpStatus.OK).body("Deleted Successfully for channelId: " + channelId
				+ " ,reasonCode: " + reasonCode + " ,statusCode: " + statusCode);
	}
}
