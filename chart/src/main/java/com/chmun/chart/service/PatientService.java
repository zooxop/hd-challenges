package com.chmun.chart.service;

import com.chmun.chart.domain.hospital.Hospital;
import com.chmun.chart.domain.hospital.HospitalRepository;
import com.chmun.chart.domain.patient.Patient;
import com.chmun.chart.domain.patient.PatientRepository;
import com.chmun.chart.dto.error.ErrorMsgDto;
import com.chmun.chart.dto.patient.PatientRequestDto;
import com.chmun.chart.dto.patient.PatientResponseDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
    private final PatientRepository patientRepository;
    private final HospitalRepository hospitalRepository;

    public PatientService(
            PatientRepository patientRepository,
            HospitalRepository hospitalRepository
    ) {
        this.patientRepository = patientRepository;
        this.hospitalRepository = hospitalRepository;
    }

    public List<PatientResponseDto> findAll() {
        List<Patient> patientList = patientRepository.findAll();

        List<PatientResponseDto> responseDtoList = new ArrayList<>();

        for (Patient patient: patientList) {
            PatientResponseDto dto = new PatientResponseDto(patient);
            responseDtoList.add(dto);
        }

        return responseDtoList;
    }

    public PatientResponseDto findById(Long id) {

        Optional<Patient> patientOptional = patientRepository.findById(id);
        if (patientOptional.isEmpty()) {
            return new PatientResponseDto();
        }

        Patient patient = patientOptional.get();

        return new PatientResponseDto(patient);
    }

    public ErrorMsgDto save(PatientRequestDto dto) {
        ErrorMsgDto msg;

        Optional<Hospital> hospitalOptional = hospitalRepository.findById(dto.getHospitalId());
        if (hospitalOptional.isEmpty()) {
            msg = new ErrorMsgDto("error", "병원 코드 조회 오류.");
            return msg;
        }

        Hospital hospital = hospitalOptional.get();

        try {
            Patient newData = new Patient(
                    null,
                    hospital,
                    dto.getName(),
                    dto.getChartId(),
                    dto.getGender(),
                    dto.getBirthday(),
                    dto.getPhone()
            );

            patientRepository.save(newData);

            msg = new ErrorMsgDto("success", "환자 정보 저장 성공.");
        } catch (Exception e) {
            msg = new ErrorMsgDto("error", "환자 정보 저장 실패. ::" + e.getMessage());
        }

        return msg;
    }

    public ErrorMsgDto update(Long id, PatientRequestDto newData) {
        ErrorMsgDto msg;

        Optional<Patient> patientOptional = patientRepository.findById(id);
        if (patientOptional.isEmpty()) {
            msg = new ErrorMsgDto("error", "환자 정보 조회 오류. id=" + id);
            return msg;
        }

        Patient target = patientOptional.get();

        target.update(
                newData.getName(),
                newData.getGender(),
                newData.getBirthday(),
                newData.getPhone()
        );

        try {
            patientRepository.save(target);
            msg = new ErrorMsgDto("success", "환자 정보 수정 성공.");
        } catch (Exception e) {
            msg = new ErrorMsgDto("error", "환자 정보 수정 실패. :: " + e.getMessage());
        }

        return msg;
    }

    public ErrorMsgDto delete(Long id) {
        ErrorMsgDto msg;

        Optional<Patient> patientOptional = patientRepository.findById(id);
        if (patientOptional.isEmpty()) {
            msg = new ErrorMsgDto("error", "환자 정보 조회 오류. id=" + id);
            return msg;
        }

        Patient patient = patientOptional.get();
        try {
            patientRepository.delete(patient);
            msg = new ErrorMsgDto("success", "환자 정보 삭제 성공.");
        } catch (Exception e) {
            msg = new ErrorMsgDto("error", "환자 정보 삭제 실패. :: " + e.getMessage());
        }

        return msg;
    }
}
