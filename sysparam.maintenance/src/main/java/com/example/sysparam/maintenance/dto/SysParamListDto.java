package com.example.sysparam.maintenance.dto;

import org.aspectj.apache.bcel.classfile.Code;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysParamListDto {
	private String channelId;

	private String statusCode;

	private String reasonCode;

	private String description;

	private String reactivate;

	private String reissue;

	@Override
	public String toString() {
		return "SysParamDto [channelId=" + channelId + ", statusCode=" + statusCode + ", reasonCode=" + reasonCode
				+ ", description=" + description + ", reactivate=" + reactivate + ", reissue=" + reissue
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
	
	
}
