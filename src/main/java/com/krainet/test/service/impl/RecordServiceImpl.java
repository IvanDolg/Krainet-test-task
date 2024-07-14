package com.krainet.test.service.impl;

import com.krainet.test.dto.ApiResponseDto;
import com.krainet.test.dto.projectDto.ProjectDto;
import com.krainet.test.dto.record.RecordDto;
import com.krainet.test.dto.userDto.UserDto;
import com.krainet.test.entity.Record;
import com.krainet.test.exception.RecordAlreadyExistsException;
import com.krainet.test.exception.RecurseNotFoundException;
import com.krainet.test.mapper.AutoRecordMapper;
import com.krainet.test.repository.RecordRepository;
import com.krainet.test.service.ProjectService;
import com.krainet.test.service.RecordService;
import com.krainet.test.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class RecordServiceImpl implements RecordService {

    private RecordRepository recordRepository;
    private UserService userService;
    private ProjectService projectService;

    @Override
    public RecordDto save(RecordDto recordDto) {

        // FIXME: To avoid checking the existence of a user by id,
        //  because you need to enter the id when saving the user
        Optional<Record> optionalRecord = recordRepository.findById(recordDto.getId());

        if (optionalRecord.isPresent()) {
            throw new RecordAlreadyExistsException("Record already exists");
        }

        Record record = AutoRecordMapper.MAPPER.mapToRecord(recordDto);
        Record savedRecord = recordRepository.save(record);
        return AutoRecordMapper.MAPPER.mapToRecordDto(savedRecord);
    }

    @Override
    public ApiResponseDto findById(Long id) {
        Record record = recordRepository.findById(id).orElseThrow(
                () -> new RecurseNotFoundException("Record", "id", String.valueOf(id))
        );

        RecordDto recordDto = AutoRecordMapper.MAPPER.mapToRecordDto(record);
        UserDto userDto = userService.findById(record.getUserId());
        ProjectDto projectDto = projectService.findById(record.getProjectId());


        return new ApiResponseDto(recordDto, projectDto, userDto);
    }

    @Override
    public RecordDto update(Long id, RecordDto recordDto) {
        Record record = recordRepository.findById(id).orElseThrow(
                () -> new RecurseNotFoundException("Record", "id", String.valueOf(id))
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
        Record record = recordRepository.findById(id).orElseThrow(
                () -> new RecurseNotFoundException("Record", "id", String.valueOf(id))
        );

        recordRepository.delete(record);
    }
}
