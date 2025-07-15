package com.example.sysparam.maintenance.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "ebpsts") // maps to actual table name
public class SystemParameter {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;

	@Column(name = "ESCHID", length = 1)
	@Size(max = 1, message = "Channel ID must not more than 1 byte")
	@NotBlank(message = "channelId must not be blank.")
	private String channelId;

	@Column(name = "ESSTSC", length = 4)
	@Size(max = 4, message = "Status Code must not more than 4 bytes")
	@NotBlank(message = "statusCode must not be blank.")
	private String statusCode;

	@Column(name = "ESRSCD", length = 2)
	@Size(max = 2, message = "reasonCode not more than 2 bytes")
	@NotBlank(message = "reasonCode must not be blank.")
	private String reasonCode;

	@Column(name = "ESDESC", length = 40)
	@Size(max = 40, message = "description not more thatn 40 bytes")
	private String description;

	@Column(name = "ESRACT", length = 1)
	@Size(max = 1, message = "reactivate not more than 1 byte")
	@NotBlank(message = "reactivate must not be blank.")
	private String reactivate;

	@Column(name = "ESRISS", length = 1)
	@Size(max = 1, message = "reissue not more than 1 byte")
	@NotBlank(message = "reissue must not be blank.")
	private String reissue;

	@Column(name = "ESSTS2", length = 4)
	@Size(max = 4, message = "statusCode2 not more than 4 byte")
	@NotBlank(message = "statusCode2 must not be blank.")
	private String statusCode2;

	@Column(name = "ESRUSR", length = 1)
	@Size(max = 1, message = "verifyUser not more than 1 byte")
	@NotBlank(message = "verifyUser must not be blank.")
	private String verifyUser;

	public SystemParameter(Long id, @Size(max = 1, message = "Channel ID must not more than 1 byte") String channelId,
				@Size(max = 4, message = "Status Code must not more than 4 bytes") String statusCode,
				@Size(max = 2, message = "reasonCode not more than 2 bytes") String reasonCode,
				@Size(max = 40, message = "description not more thatn 40 bytes") String description,
				@Size(max = 1, message = "reactivate not more than 1 byte") String reactivate,
				@Size(max = 1, message = "reissue not more than 1 byte") String reissue,
				@Size(max = 4, message = "statusCode2 not more than 4 byte") String statusCode2,
				@Size(max = 1, message = "verifyUser not more than 1 byte") String verifyUser) {
			Id = id;
			this.channelId = channelId;
			this.statusCode = statusCode;
			this.reasonCode = reasonCode;
			this.description = description;
			this.reactivate = reactivate;
			this.reissue = reissue;
			this.statusCode2 = statusCode2;
			this.verifyUser = verifyUser;
		}

	public SystemParameter() {
			// TODO Auto-generated constructor stub
		}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getReactivate() {
		return reactivate;
	}

	public void setReactivate(String reactivate) {
		this.reactivate = reactivate;
	}

	public String getReissue() {
		return reissue;
	}

	public void setReissue(String reissue) {
		this.reissue = reissue;
	}

	public String getStatusCode2() {
		return statusCode2;
	}

	public void setStatusCode2(String statusCode2) {
		this.statusCode2 = statusCode2;
	}

	public String getVerifyUser() {
		return verifyUser;
	}

	public void setVerifyUser(String verifyUser) {
		this.verifyUser = verifyUser;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getReasonCode() {
		return reasonCode;
	}

	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
}
