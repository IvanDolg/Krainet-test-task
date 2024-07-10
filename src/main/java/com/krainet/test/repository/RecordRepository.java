package com.krainet.test.repository;

import com.krainet.test.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordRepository extends JpaRepository<Record, Long> {
}
