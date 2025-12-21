package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.ApiUsageLog;

public interface ApiUsageLogRepository extends JpaRepository<ApiUsageLog, Long> {

    // All logs for an API key
    List<ApiUsageLog> findByApiKey_Id(Long id);

    // Logs between epoch seconds (TEST-SAFE FIX)
    @Query("""
        SELECT l FROM ApiUsageLog l
        WHERE l.apiKey.id = :keyId
        AND l.timestamp >= FUNCTION('FROM_UNIXTIME', :startEpoch)
        AND l.timestamp <= FUNCTION('FROM_UNIXTIME', :endEpoch)
    """)
    List<ApiUsageLog> findForKeyBetween(
            @Param("keyId") Long keyId,
            @Param("startEpoch") long startEpoch,
            @Param("endEpoch") long endEpoch
    );

    // Count logs between epoch seconds
    @Query("""
        SELECT COUNT(l) FROM ApiUsageLog l
        WHERE l.apiKey.id = :keyId
        AND l.timestamp >= FUNCTION('FROM_UNIXTIME', :startEpoch)
        AND l.timestamp <= FUNCTION('FROM_UNIXTIME', :endEpoch)
    """)
    int countForKeyBetween(
            @Param("keyId") Long keyId,
            @Param("startEpoch") long startEpoch,
            @Param("endEpoch") long endEpoch
    );
}
