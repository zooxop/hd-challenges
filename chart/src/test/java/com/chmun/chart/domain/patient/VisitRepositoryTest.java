package com.chmun.chart.domain.patient;

import com.chmun.chart.domain.hospital.Hospital;
import com.chmun.chart.domain.hospital.HospitalRepository;
import com.chmun.chart.util.DateUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class VisitRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private HospitalRepository hospitalRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private VisitRepository visitRepository;

    @Test
    public void test_환자_방문_저장_조회() {
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
                "010-1111-2222"
        );
        patient = patientRepository.save(patient);

        Visit visit = new Visit(
                null,
                hospital,
                patient,
                DateUtil.convertToLocalDate("2024-03-05"),
                "1"
        );
        visit = visitRepository.save(visit);

        // when
        Visit foundVisit = visitRepository.findById(Long.valueOf(1)).orElse(null);

        // then
        assertThat(foundVisit).isNotNull();
        assertThat(foundVisit.getVisitDate()).isEqualTo("2024-03-05");
    }
}
