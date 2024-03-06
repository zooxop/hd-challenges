package com.chmun.chart.dto.patient;

public class PatientListResponseDto {

    private String name;
    private String chartId;
    private String gender;
    private String birthday;
    private String phone;
    private String lastVisitDate;

    public PatientListResponseDto(
            String name,
            String chartId,
            String gender,
            String birthday,
            String phone,
            String lastVisitDate
    ) {
        this.name = name;
        this.chartId = chartId;
        this.gender = gender;
        this.birthday = birthday;
        this.phone = phone;
        this.lastVisitDate = lastVisitDate;
    }

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

    public String getLastVisitDate() {
        return lastVisitDate;
    }
}
