package com.krainet.test.controller;

import com.krainet.test.dto.ApiResponseDto;
import com.krainet.test.dto.record.RecordDto;
import com.krainet.test.service.RecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "Record Controller",
        description = "Record controller exposes rest apis"
)
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/records")
public class RecordController {
    private RecordService recordService;

    @Operation(
            summary = "Create new record",
            description = "Used to save new record to a database"
    )
    @ApiResponses(
            @ApiResponse(
                    responseCode = "201",
                    description = "Record created"
            )
    )
    @PostMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<RecordDto> save(@RequestBody RecordDto recordDto) {
        RecordDto savedRecord = recordService.save(recordDto);
        return new ResponseEntity<>(savedRecord, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get record by id",
            description = "Used to retrieve record from db by id"
    )
    @ApiResponses(
            @ApiResponse(
                    responseCode = "200",
                    description = "Record found"
            )
    )
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<ApiResponseDto> findById(@PathVariable Long id) {
        ApiResponseDto apiResponseDto = recordService.findById(id);
        return new ResponseEntity<>(apiResponseDto, HttpStatus.OK);
    }

    @Operation(
            summary = "Update record by id",
            description = "Used to update record from db by id"
    )
    @ApiResponses(
            @ApiResponse(
                    responseCode = "200",
                    description = "Record found"
            )
    )
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<RecordDto> update(@PathVariable Long id,
                                            @RequestBody RecordDto recordDto) {
        RecordDto updatedRecord = recordService.update(id, recordDto);
        return new ResponseEntity<>(updatedRecord, HttpStatus.OK);
    }

    @Operation(
            summary = "Delete record by id",
            description = "Used to delete record from db by id"
    )
    @ApiResponses(
            @ApiResponse(
                    responseCode = "200",
                    description = "Record deleted"
            )
    )
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        recordService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
