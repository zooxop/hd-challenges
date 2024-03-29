package com.chmun.chart.domain.patient;

import com.chmun.chart.domain.hospital.Hospital;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "patient")
public class Patient {

    @Id
    @Column(name = "patient_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long patientId;

    @ManyToOne
    @JoinColumn(name = "hospital_id", nullable = false)
    private Hospital hospital;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "chart_id", nullable = false)
    private String chartId;

    @Column(name = "gender", nullable = false)
    private String gender;

    @Column(name = "birthday")
    private String birthday;

    @Column(name = "phone")
    private String phone;

    @Column(name = "use_yn")
    private String useYn;

    @OneToMany(mappedBy = "patientId", fetch = FetchType.LAZY)
    private List<Visit> visitList;

    public Patient() {

    }

    public Patient(
            Long patientId,
            Hospital hospital,
            String name,
            String chartId,
            String gender,
            String birthday,
            String phone,
            String useYn,
            List<Visit> visitList
    ) {
        this.patientId = patientId;
        this.hospital = hospital;
        this.name = name;
        this.chartId = chartId;
        this.gender = gender;
        this.birthday = birthday;
        this.phone = phone;
        this.useYn = useYn;
        this.visitList = visitList;
    }

    public Long getPatientId() {
        return patientId;
    }

    public Hospital getHospital() {
        return hospital;
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

    public String getUseYn() { return useYn; }

    public List<Visit> getVisitList() {
        return visitList;
    }

    public void update(String name, String gender, String birthday, String phone) {
        this.name = name;
        this.gender = gender;
        this.birthday = birthday;
        this.phone = phone;
    }

    public void delete() {
        this.useYn = "N";
    }
}

