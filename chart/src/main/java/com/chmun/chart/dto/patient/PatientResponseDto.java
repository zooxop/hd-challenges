package com.chmun.chart.dto.patient;

import com.chmun.chart.domain.patient.Patient;

public class PatientResponseDto {
    private Long patientId;
    private Long hospitalId;
    private String name;
    private String chartId;
    private String gender;
    private String birthday;
    private String phone;

    public PatientResponseDto() {
        this.patientId = null;
        this.hospitalId = null;
        this.name = null;
        this.chartId = null;
        this.gender = null;
        this.birthday = null;
        this.phone = null;
    }

    public PatientResponseDto(Patient patient) {
        this.patientId = patient.getPatientId();
        this.hospitalId = patient.getHospital().getHospitalId();
        this.name = patient.getName();
        this.chartId = patient.getChartId();
        this.gender = patient.getGender();
        this.birthday = patient.getBirthday();
        this.phone = patient.getPhone();
    }

    public Long getHospitalId() { return hospitalId; }

    public String getName() {
        return name;
    }

    public String getChartId() {
        return chartId;
    }

    public String getGender() {
        return gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getPhone() {
        return phone;
    }
}
