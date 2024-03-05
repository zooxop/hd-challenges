package com.chmun.chart.domain.patient;

import com.chmun.chart.domain.hospital.Hospital;
import com.chmun.chart.util.DateUtil;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "visit")
public class Visit {

    @Id
    @Column(name = "visit_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long visitId;

    @ManyToOne
    @JoinColumn(name = "hospital_id", nullable = false)
    private Hospital hospitalId;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patientId;

    @Column(name = "visit_date", nullable = false)
    private LocalDate visitDate;

    @Column(name = "visit_code", nullable = false)
    private String visitCode;

    public Visit() {

    }

    public Visit(Long visitId, Hospital hospitalId, Patient patientId, LocalDate visitDate, String visitCode) {
        this.visitId = visitId;
        this.hospitalId = hospitalId;
        this.patientId = patientId;
        this.visitDate = visitDate;
        this.visitCode = visitCode;
    }

    public Long getVisitId() {
        return visitId;
    }

    public Hospital getHospitalId() {
        return hospitalId;
    }

    public Patient getPatientId() {
        return patientId;
    }

    public LocalDate getVisitDate() {
        return visitDate;
    }

    public String getVisitCode() {
        return visitCode;
    }

    public void update(String visitDate, String visitCode) {
        this.visitDate = DateUtil.convertToLocalDate(visitDate);
        this.visitCode = visitCode;
    }
}