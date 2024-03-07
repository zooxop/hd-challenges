package com.chmun.chart.domain.patient;

import com.chmun.chart.domain.hospital.Hospital;

import java.util.List;

public interface PatientRepositoryCustom {
    List<Patient> findPatient(Hospital hospital, String name, String chartId, String birthday, String useYn);
}
