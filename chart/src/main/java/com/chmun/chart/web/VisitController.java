package com.chmun.chart.web;

import com.chmun.chart.dto.error.ErrorMsgDto;
import com.chmun.chart.dto.patient.VisitRequestDto;
import com.chmun.chart.dto.patient.VisitResponseDto;
import com.chmun.chart.service.VisitService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VisitController {

    private final VisitService visitService;

    public VisitController(VisitService visitService) {
        this.visitService = visitService;
    }

    @GetMapping("/api/v1/visit/all")
    public List<VisitResponseDto> findAll() {
        return visitService.findAll();
    }

    @GetMapping("/api/v1/visit/{id}")
    public VisitResponseDto findById(@PathVariable Long id) {
        return visitService.findById(id);
    }

    @PostMapping("/api/v1/visit/create")
    public ErrorMsgDto create(@RequestBody VisitRequestDto dto) {
        return visitService.save(dto);
    }

    @PutMapping("/api/v1/visit/update/{id}")
    public ErrorMsgDto update(@PathVariable Long id, @RequestBody VisitRequestDto dto) {
        return visitService.update(id, dto);
    }

    @DeleteMapping("/api/v1/visit/delete/{id}")
    public ErrorMsgDto delete(@PathVariable Long id) {
        return visitService.delete(id);
    }
}
