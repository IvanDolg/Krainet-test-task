package com.krainet.test.service.impl;

import com.krainet.test.dto.record.RecordDto;
import com.krainet.test.entity.Record;
import com.krainet.test.mapper.AutoRecordMapper;
import com.krainet.test.repository.RecordRepository;
import com.krainet.test.service.RecordService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class RecordServiceImpl implements RecordService {

    private RecordRepository recordRepository;

    @Override
    public RecordDto save(RecordDto recordDto) {
        Record record = AutoRecordMapper.MAPPER.mapToRecord(recordDto);
        record.setStartTime(recordDto.getStartTime());
        record.setEndTime(recordDto.getEndTime());

        Record savedRecord = recordRepository.save(record);
        return AutoRecordMapper.MAPPER.mapToRecordDto(savedRecord);
    }

    @Override
    public RecordDto findById(Long id) {
        return null;
    }

    @Override
    public RecordDto update(Long id, RecordDto recordDto) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
