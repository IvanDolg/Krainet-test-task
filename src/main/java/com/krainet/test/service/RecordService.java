package com.krainet.test.service;

import com.krainet.test.dto.ApiResponseDto;
import com.krainet.test.dto.record.RecordDto;

public interface RecordService {
    RecordDto save(RecordDto recordDto);
    ApiResponseDto findById(Long id);
    RecordDto update(Long id, RecordDto recordDto);
    void delete(Long id);
}
