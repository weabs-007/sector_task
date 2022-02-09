package com.myform.test.controller;

import com.myform.test.dto.FormDtoRequest;
import com.myform.test.dto.FormDtoResponse;
import com.myform.test.dto.mapper.FormDtoMapper;
import com.myform.test.exception.FormNotFoundException;
import com.myform.test.model.Form;
import com.myform.test.service.FormService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Tag(name = "Forms", description = "FormRestController API v1")
@CrossOrigin(origins = "http://localhost:8086", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class FormController {

    private final FormService formService;
    private final FormDtoMapper mapper;

    @PostMapping("/form")
    @ApiOperation(value = "create", notes = "Update form details", tags = "Forms")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "401", description = "Authentication Data is missing or invalid"),
            @ApiResponse(responseCode = "403", description = "Forbidden operation"),
            @ApiResponse(responseCode = "404", description = "Controller not found")}
    )
    public ResponseEntity<FormDtoResponse> create(@Valid @RequestBody FormDtoRequest formDto) {
        Form newForm = formService.create(formDto);
        return ResponseEntity.status(HttpStatus.OK).body(mapper.toFormDtoResponse(newForm));
    }

    @PutMapping("/form/{id}")
    @ApiOperation(value = "update", notes = "Update form details", tags = "Forms")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "401", description = "Authentication Data is missing or invalid"),
            @ApiResponse(responseCode = "403", description = "Forbidden operation"),
            @ApiResponse(responseCode = "404", description = "Controller not found")}
    )
    public ResponseEntity<FormDtoResponse> update(@PathVariable Long id, @Valid @RequestBody FormDtoRequest formDto) throws FormNotFoundException {
        Form newForm = formService.update(id, formDto);
        return ResponseEntity.status(HttpStatus.OK).body(mapper.toFormDtoResponse(newForm));
    }

    @GetMapping("/form/{id}")
    @ApiOperation(value = "getFormById", notes = "Get form by ID", tags = "Forms")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "401", description = "Authentication Data is missing or invalid"),
            @ApiResponse(responseCode = "403", description = "Forbidden operation"),
            @ApiResponse(responseCode = "404", description = "Controller not found")}
    )
    public ResponseEntity<FormDtoResponse> getFormById(@PathVariable Long id) throws FormNotFoundException {
        Form newForm = formService.getFormById(id);
        return ResponseEntity.status(HttpStatus.OK).body(mapper.toFormDtoResponse(newForm));

    }

}
