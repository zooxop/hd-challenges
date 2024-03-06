package com.chmun.chart.dto.patient;

public class PatientListRequestDto {
    private Long hospitalId;

    public PatientListRequestDto() {

    }
    public PatientListRequestDto(Long hospitalId) {
        this.hospitalId = hospitalId;
    }

    public Long getHospitalId() {
        return hospitalId;
    }
}
