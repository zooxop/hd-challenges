package com.chmun.chart.dto.patient;

public class VisitRequestDto {
    private Long hospitalId;
    private Long patientId;
    private String visitDate;
    private String visitCode;

    public VisitRequestDto(Long hospitalId, Long patientId, String visitDate, String visitCode) {
        this.hospitalId = hospitalId;
        this.patientId = patientId;
        this.visitDate = visitDate;
        this.visitCode = visitCode;
    }

    public Long getHospitalId() {
        return hospitalId;
    }

    public Long getPatientId() {
        return patientId;
    }

    public String getVisitDate() {
        return visitDate;
    }

    public String getVisitCode() {
        return visitCode;
    }
}
