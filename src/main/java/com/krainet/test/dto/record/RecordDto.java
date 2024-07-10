package com.krainet.test.dto.record;

import com.krainet.test.entity.Project;
import com.krainet.test.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecordDto {
    private Long id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Long userId;
    private Long projectId;
}
