package com.chmun.chart.service;

import com.chmun.chart.domain.hospital.Hospital;
import com.chmun.chart.domain.hospital.HospitalRepository;
import com.chmun.chart.domain.patient.Patient;
import com.chmun.chart.domain.patient.PatientRepository;
import com.chmun.chart.domain.patient.Visit;
import com.chmun.chart.domain.patient.VisitRepository;
import com.chmun.chart.dto.error.ErrorMsgDto;
import com.chmun.chart.dto.patient.VisitRequestDto;
import com.chmun.chart.dto.patient.VisitResponseDto;
import com.chmun.chart.util.DateUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VisitService {
    private final VisitRepository visitRepository;
    private final PatientRepository patientRepository;
    private final HospitalRepository hospitalRepository;

    public VisitService(
            VisitRepository visitRepository,
            PatientRepository patientRepository,
            HospitalRepository hospitalRepository
    ) {
        this.visitRepository = visitRepository;
        this.patientRepository = patientRepository;
        this.hospitalRepository = hospitalRepository;
    }

    public List<VisitResponseDto> findAll() {
        List<Visit> visitList = visitRepository.findAll();

        List<VisitResponseDto> responseDtoList = new ArrayList<>();

        for (Visit visit: visitList) {
            VisitResponseDto dto = new VisitResponseDto(visit);
            responseDtoList.add(dto);
        }

        return responseDtoList;
    }

    public VisitResponseDto findById(Long id) {
        Optional<Visit> visitOptional = visitRepository.findById(id);
        if (visitOptional.isEmpty()) {
            return new VisitResponseDto();
        }

        Visit visit = visitOptional.get();

        return new VisitResponseDto(visit);
    }

    public ErrorMsgDto save(VisitRequestDto dto) {
        ErrorMsgDto msg;

        Optional<Hospital> hospitalOptional = hospitalRepository.findById(dto.getHospitalId());
        if (hospitalOptional.isEmpty()) {
            msg = new ErrorMsgDto("error", "병원 코드 조회 오류.");
            return msg;
        }

        Hospital hospital = hospitalOptional.get();

        Optional<Patient> patientOptional = patientRepository.findById(dto.getPatientId());
        if (patientOptional.isEmpty()) {
            msg = new ErrorMsgDto("error", "환자 번호 조회 오류.");
            return msg;
        }

        Patient patient = patientOptional.get();

        try {
            Visit newData = new Visit(null, hospital, patient, DateUtil.convertToLocalDate(dto.getVisitDate()), dto.getVisitCode());
            visitRepository.save(newData);
            msg = new ErrorMsgDto("success", "방문 정보 저장 성공.");
        } catch (Exception e) {
            msg = new ErrorMsgDto("error", "방문 정보 저장 실패. ::" + e.getMessage());
        }

        return msg;
    }

    public ErrorMsgDto update(Long id, VisitRequestDto dto) {
        ErrorMsgDto msg;

        Optional<Visit> visitOptional = visitRepository.findById(id);
        if (visitOptional.isEmpty()) {
            msg = new ErrorMsgDto("error", "방문 정보 조회 오류.");
            return msg;
        }

        Visit target = visitOptional.get();

        target.update(dto.getVisitDate(), dto.getVisitCode());

        try {
            visitRepository.save(target);
            msg = new ErrorMsgDto("success", "방문 정보 수정 성공");
        } catch (Exception e) {
            msg = new ErrorMsgDto("error", "방문 정보 수정 실패. :: " + e.getMessage());
        }

        return msg;
    }

    public ErrorMsgDto delete(Long id) {
        ErrorMsgDto msg;

        Optional<Visit> visitOptional = visitRepository.findById(id);
        if (visitOptional.isEmpty()) {
            msg = new ErrorMsgDto("error", "방문 정보 조회 오류.");
            return msg;
        }

        try {
            visitRepository.deleteById(id);
            msg = new ErrorMsgDto("success", "방문 정보 삭제 성공");
        } catch (Exception e) {
            msg = new ErrorMsgDto("error", "방문 정보 삭제 실패. :: " + e.getMessage());
        }

        return msg;
    }
}
