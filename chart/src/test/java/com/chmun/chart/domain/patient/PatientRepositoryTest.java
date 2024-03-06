package com.chmun.chart.domain.patient;

import com.chmun.chart.domain.hospital.Hospital;
import com.chmun.chart.domain.hospital.HospitalRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class PatientRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private HospitalRepository hospitalRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Test
    public void test_환자_저장_조회() {

        // given
        Hospital hospital = hospitalRepository.findById(Long.valueOf(1)).orElse(null);
        if (hospital == null) {
            Assertions.fail("병원 엔티티 조회 에러");
        }

        entityManager.persist(hospital);

        Patient patient = new Patient(
                null,
                hospital,
                "테스트",
                "202400123",
                "M",
                "1996-07-26",
                "010-1111-2222",
                "Y",
                null
        );
        patient = patientRepository.save(patient);

        // when
        Patient foundPatient = patientRepository.findById(patient.getPatientId()).orElse(null);

        // then
        assertThat(foundPatient).isNotNull();
        assertThat(foundPatient.getName()).isEqualTo("테스트");
        assertThat(foundPatient.getHospital().getDirectorName()).isEqualTo("매디슨");
    }
}
