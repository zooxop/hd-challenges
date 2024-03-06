package com.chmun.chart.domain.patient;

import com.chmun.chart.domain.hospital.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    @Query("SELECT COALESCE(MAX(CAST(p.chartId AS long)), 0) + 1 FROM Patient p where p.hospital.hospitalId = :hospitalId")
    Long getNextChartId(Long hospitalId);

    List<Patient> findByHospitalAndUseYn(Hospital hospital, String useYn);
    Optional<Patient> findByPatientIdAndUseYn(Long patientId, String useYn);
}
