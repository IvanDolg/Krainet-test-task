package com.krainet.test.dto.record;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
