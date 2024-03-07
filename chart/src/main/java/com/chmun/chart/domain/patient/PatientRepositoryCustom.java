package com.chmun.chart.domain.patient;

import com.chmun.chart.domain.hospital.Hospital;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PatientRepositoryCustom {
    List<Patient> findPatient(Hospital hospital, String name, String chartId, String birthday, String useYn);

    Page<Patient> findPatientWithPaging(Hospital hospital, String name, String chartId, String birthday, String useYn, Pageable pageable);
}
