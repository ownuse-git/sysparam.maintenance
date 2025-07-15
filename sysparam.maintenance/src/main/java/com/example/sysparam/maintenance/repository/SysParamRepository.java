package com.example.sysparam.maintenance.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.sysparam.maintenance.entity.SystemParameter;

public interface SysParamRepository extends JpaRepository<SystemParameter, Long>{

	public Optional<SystemParameter> findByChannelIdAndStatusCodeAndReasonCode(String channelId, String statusCode,
			String reasonCode);
	
	public SystemParameter findByChannelIdAndReasonCodeAndStatusCode(String channelId, String reasonCode,
			String statusCode);

	public void deleteByChannelIdAndStatusCodeAndReasonCode(String channelId, String statusCode, String reasonCode);

	@Query("SELECT a FROM SystemParameter a WHERE (:channelId IS NULL OR a.channelId = :channelId) "
			+ "AND (:statusCode IS NULL OR a.statusCode = :statusCode) "
			+ "AND (:reasonCode IS NULL OR a.reasonCode = :reasonCode)")
	public List<SystemParameter> findWithCustomSearch(@Param("channelId") String channelId,
			@Param("statusCode") String statusCode, @Param("reasonCode") String reasonCode);
}
