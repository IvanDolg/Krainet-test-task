package com.krainet.test.service.impl;

import com.krainet.test.dto.ApiResponseDto;
import com.krainet.test.dto.projectDto.ProjectDto;
import com.krainet.test.dto.record.RecordDto;
import com.krainet.test.dto.userDto.UserDto;
import com.krainet.test.entity.Record;
import com.krainet.test.mapper.AutoRecordMapper;
import com.krainet.test.repository.RecordRepository;
import com.krainet.test.service.ProjectService;
import com.krainet.test.service.RecordService;
import com.krainet.test.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class RecordServiceImpl implements RecordService {

    private RecordRepository recordRepository;
    private UserService userService;
    private ProjectService projectService;

    @Override
    public RecordDto save(RecordDto recordDto) {
        Record record = AutoRecordMapper.MAPPER.mapToRecord(recordDto);
        Record savedRecord = recordRepository.save(record);
        return AutoRecordMapper.MAPPER.mapToRecordDto(savedRecord);
    }

    @Override
    public ApiResponseDto findById(Long id) {
        Record record = recordRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Record not found")
        );

        RecordDto recordDto = AutoRecordMapper.MAPPER.mapToRecordDto(record);
        UserDto userDto = userService.findById(record.getUserId()).get();
        ProjectDto projectDto = projectService.findById(record.getProjectId());


        return new ApiResponseDto(recordDto, projectDto, userDto);
    }

    @Override
    public RecordDto update(Long id, RecordDto recordDto) {
        Record record = recordRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Record not found")
        );
        record.setStartTime(recordDto.getStartTime());
        record.setEndTime(recordDto.getEndTime());
        record.setUserId(recordDto.getUserId());
        record.setProjectId(recordDto.getProjectId());

        Record savedRecord = recordRepository.save(record);
        return AutoRecordMapper.MAPPER.mapToRecordDto(savedRecord);
    }

    @Override
    public void delete(Long id) {
        recordRepository.deleteById(id);
    }
}
