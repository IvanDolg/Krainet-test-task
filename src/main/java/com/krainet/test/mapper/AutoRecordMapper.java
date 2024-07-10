package com.krainet.test.mapper;

import com.krainet.test.dto.projectDto.ProjectDto;
import com.krainet.test.dto.record.RecordDto;
import com.krainet.test.entity.Project;
import com.krainet.test.entity.Record;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AutoRecordMapper {
    AutoRecordMapper MAPPER = Mappers.getMapper(AutoRecordMapper.class);

    RecordDto mapToRecordDto(Record record);
    Record mapToRecord(RecordDto recordDto);
}