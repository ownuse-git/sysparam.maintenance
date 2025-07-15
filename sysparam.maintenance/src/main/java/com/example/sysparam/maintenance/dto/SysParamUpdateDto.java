package com.example.sysparam.maintenance.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysParamUpdateDto {

	@Column(name = "id")
	private Long Id;
	@Size(max = 1, message = "Channel ID must not more than 1 byte")
	@NotBlank(message = "channelId must not be blank.")
	private String channelId;
	@Column(name = "ESSTSC", length = 4)
	@Size(max = 4, message = "Status Code must not more than 4 bytes")
	private String statusCode;
	@Size(max = 2, message = "reasonCode not more than 2 bytes")
	@NotBlank(message = "reasonCode must not be blank.")
	private String reasonCode;
	@Size(max = 40, message = "description not more thatn 40 bytes")
	private String description;
	@Size(max = 1, message = "reactivate not more than 1 byte")
	@NotBlank(message = "reactivate must not be blank.")
	private String reactivate;
	@Size(max = 1, message = "reissue not more than 1 byte")
	@NotBlank(message = "reissue must not be blank.")
	private String reissue;
	@Size(max = 4, message = "statusCode2 not more than 4 byte")
	@NotBlank(message = "statusCode2 must not be blank.")
	private String statusCode2;
	@Size(max = 1, message = "verifyUser not more than 1 byte")
	@NotBlank(message = "verifyUser must not be blank.")
	private String verifyUser;

	@Override
	public String toString() {
		return "SysParamDetailDto [channelId=" + channelId + ", statusCode=" + statusCode + ", reasonCode=" + reasonCode
				+ ", description=" + description + ", reactivate=" + reactivate + ", reissue=" + reissue
				+ ", statusCode2=" + statusCode2 + ", verifyUser=" + verifyUser + "]";
	}

}
