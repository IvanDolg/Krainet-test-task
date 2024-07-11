package com.krainet.test.controller;

import com.krainet.test.dto.ApiResponseDto;
import com.krainet.test.dto.record.RecordDto;
import com.krainet.test.service.RecordService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/records")
public class RecordController {
    private RecordService recordService;

    @PostMapping
    public ResponseEntity<RecordDto> save(@RequestBody RecordDto recordDto) {
        RecordDto savedRecord = recordService.save(recordDto);
        return new ResponseEntity<>(savedRecord, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDto> findById(@PathVariable Long id) {
        ApiResponseDto apiResponseDto = recordService.findById(id);
        return new ResponseEntity<>(apiResponseDto, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecordDto> update(@PathVariable Long id,
                                            @RequestBody RecordDto recordDto) {
        RecordDto updatedRecord = recordService.update(id, recordDto);
        return new ResponseEntity<>(updatedRecord, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        recordService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
