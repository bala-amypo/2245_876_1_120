package com.example.demo.repository;

import java.time.Instant;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.example.demo.entity.ApiUsageLog;

public interface ApiUsageLogRepository extends JpaRepository<ApiUsageLog, Long> {

    List<ApiUsageLog> findByApiKey_Id(Long id);

    @Query("""
        SELECT l FROM ApiUsageLog l
        WHERE l.apiKey.id = :keyId
        AND l.timestamp BETWEEN :start AND :end
    """)
    List<ApiUsageLog> findForKeyBetween(
            @Param("keyId") Long keyId,
            @Param("start") Instant start,
            @Param("end") Instant end
    );

    @Query("""
        SELECT COUNT(l) FROM ApiUsageLog l
        WHERE l.apiKey.id = :keyId
        AND l.timestamp BETWEEN :start AND :end
    """)
    int countForKeyBetween(
            @Param("keyId") Long keyId,
            @Param("start") Instant start,
            @Param("end") Instant end
    );
}
