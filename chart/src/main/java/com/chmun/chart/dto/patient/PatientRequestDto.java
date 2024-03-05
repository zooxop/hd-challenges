package com.chmun.chart.dto.patient;

public class PatientRequestDto {
    private Long hospitalId;
    private String name;
    private String chartId;
    private String gender;
    private String birthday;
    private String phone;

    public PatientRequestDto(
            Long hospitalId,
            String name,
            String chartId,
            String gender,
            String birthday,
            String phone
    ) {
        this.hospitalId = hospitalId;
        this.name = name;
        this.chartId = chartId;
        this.gender = gender;
        this.birthday = birthday;
        this.phone = phone;
    }

    public Long getHospitalId() {
        return hospitalId;
    }

    public String getChartId() { return chartId; }

    public String getName() {
        return name;
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
