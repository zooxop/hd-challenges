package com.chmun.chart.web;

import com.chmun.chart.dto.error.ErrorMsgDto;
import com.chmun.chart.dto.patient.PatientListRequestDto;
import com.chmun.chart.dto.patient.PatientListResponseDto;
import com.chmun.chart.dto.patient.PatientRequestDto;
import com.chmun.chart.dto.patient.PatientResponseDto;
import com.chmun.chart.service.PatientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PatientController {
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/api/v1/patient/all")
    public List<PatientResponseDto> findAll() {
        return patientService.findAll();
    }

    @GetMapping("/api/v1/patient/{id}")
    public PatientResponseDto findById(@PathVariable Long id) {
        return patientService.findById(id);
    }

    @PostMapping("/api/v1/patient/create")
    public ErrorMsgDto create(@RequestBody PatientRequestDto dto) {
        return patientService.save(dto);
    }

    @PutMapping("/api/v1/patient/update/{id}")
    public ErrorMsgDto update(@PathVariable Long id, @RequestBody PatientRequestDto dto) {
        return patientService.update(id, dto);
    }

    @DeleteMapping("/api/v1/patient/delete/{id}")
    public ErrorMsgDto delete(@PathVariable Long id) {
        return patientService.delete(id);
    }

    @PostMapping("/api/v1/patient/list")
    public List<PatientListResponseDto> getList(@RequestBody PatientListRequestDto dto) {
        return patientService.getList(dto.getHospitalId());
    }

    @PostMapping("/api/v1/patient/search")
    public List<PatientListResponseDto> searchPatient(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "chartId", required = false) String chartId,
            @RequestParam(value = "birthday", required = false) String birthday,
            @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
            @RequestBody PatientListRequestDto dto
    ) {
        // pageNo 0 이하 값 입력 방지
        if (pageNo <= 0) {
            pageNo = 1;
        }

        // pageSize 0 이하 값 입력 방지
        if (pageSize <= 0) {
            pageSize = 10;
        }

        return patientService.search(dto.getHospitalId(), name, chartId, birthday, pageNo, pageSize);
    }
}
