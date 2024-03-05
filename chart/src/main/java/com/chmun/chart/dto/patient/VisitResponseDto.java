package com.chmun.chart.dto.patient;

import com.chmun.chart.domain.patient.Visit;
import com.chmun.chart.util.DateUtil;

public class VisitResponseDto {
    private Long hospitalId;
    private Long patientId;
    private String visitDate;
    private String visitCode;

    public VisitResponseDto() {
        this.hospitalId = null;
        this.patientId = null;
        this.visitDate = null;
        this.visitCode = null;
    }

    public VisitResponseDto(Visit visit) {
        this.hospitalId = visit.getHospitalId().getHospitalId();
        this.patientId = visit.getPatientId().getPatientId();
        this.visitDate = DateUtil.convertToString(visit.getVisitDate());
        this.visitCode = visit.getVisitCode();
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