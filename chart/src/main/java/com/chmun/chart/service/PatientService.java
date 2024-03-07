package com.chmun.chart.service;

import com.chmun.chart.domain.code.Code;
import com.chmun.chart.domain.code.CodeGroup;
import com.chmun.chart.domain.code.CodeGroupRepository;
import com.chmun.chart.domain.hospital.Hospital;
import com.chmun.chart.domain.hospital.HospitalRepository;
import com.chmun.chart.domain.patient.Patient;
import com.chmun.chart.domain.patient.PatientRepository;
import com.chmun.chart.dto.error.ErrorMsgDto;
import com.chmun.chart.dto.patient.PatientListResponseDto;
import com.chmun.chart.dto.patient.PatientRequestDto;
import com.chmun.chart.dto.patient.PatientResponseDto;
import com.chmun.chart.util.DateUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Year;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PatientService {
    private final PatientRepository patientRepository;
    private final HospitalRepository hospitalRepository;
    private final CodeGroupRepository codeRepository;

    public PatientService(
            PatientRepository patientRepository,
            HospitalRepository hospitalRepository,
            CodeGroupRepository codeRepository
    ) {
        this.patientRepository = patientRepository;
        this.hospitalRepository = hospitalRepository;
        this.codeRepository = codeRepository;
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

        Optional<Patient> patientOptional = patientRepository.findByPatientIdAndUseYn(id, "Y");
        if (patientOptional.isEmpty()) {
            return new PatientResponseDto();
        }

        Patient patient = patientOptional.get();

        return new PatientResponseDto(patient);
    }

    public List<PatientListResponseDto> getList(Long hospitalId) {

        Hospital hospital = hospitalRepository.findById(hospitalId).orElse(null);
        if (hospital == null) {
            return new ArrayList<>();
        }

        // 해당 병원의 환자 데이터 모두 가져오기
        List<Patient> patientList = patientRepository.findByHospitalAndUseYn(hospital, "Y");
        if (patientList.isEmpty()) {
            return new ArrayList<>();
        }

        return makePatientListResponseDto(patientList);
    }

    public List<PatientListResponseDto> search(Long hospitalId, String name, String chartId, String birthday) {

        Hospital hospital = hospitalRepository.findById(hospitalId).orElse(null);
        if (hospital == null) {
            return new ArrayList<>();
        }

        List<Patient> patientList = patientRepository.findPatient(hospital, name, chartId, birthday, "Y");
        if (patientList.isEmpty()) {
            return new ArrayList<>();
        }

        // Patient 리스트를 PatientListResponseDto 리스트로 만들어서 리턴.
        return makePatientListResponseDto(patientList);
    }

    @Transactional
    public ErrorMsgDto save(PatientRequestDto dto) {
        ErrorMsgDto msg;

        Optional<Hospital> hospitalOptional = hospitalRepository.findById(dto.getHospitalId());
        if (hospitalOptional.isEmpty()) {
            msg = new ErrorMsgDto("error", "병원 코드 조회 오류.");
            return msg;
        }

        Hospital hospital = hospitalOptional.get();

        // 해당 병원의 가장 마지막 차트번호 + 1
        Long newChartId = patientRepository.getNextChartId(hospital.getHospitalId());

        // (해당 병원의) 차트번호가 최초로 발생하는 경우
        if (newChartId == 1) {
            // 현재년도 4자리 + 5자리 숫자
            // e.g.) 202400001
            newChartId = Long.parseLong(Year.now().getValue() + "00001");
        }

        try {
            Patient newData = new Patient(
                    null,
                    hospital,
                    dto.getName(),
                    newChartId.toString(),
                    dto.getGender(),
                    dto.getBirthday(),
                    dto.getPhone(),
                    dto.getUseYn(),
                    null
            );

            patientRepository.save(newData);

            msg = new ErrorMsgDto("success", "환자 정보 저장 성공.");
        } catch (Exception e) {
            msg = new ErrorMsgDto("error", "환자 정보 저장 실패. ::" + e.getMessage());
        }

        return msg;
    }

    @Transactional
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
            patient.delete();  // use_yn 플래그 값을 'N' 으로 변경
            patientRepository.save(patient);
            msg = new ErrorMsgDto("success", "환자 정보 삭제 성공.");
        } catch (Exception e) {
            msg = new ErrorMsgDto("error", "환자 정보 삭제 실패. :: " + e.getMessage());
        }

        return msg;
    }

    // Patient 리스트를 PatientListResponseDto 리스트로 만들어서 리턴.
    private List<PatientListResponseDto> makePatientListResponseDto(List<Patient> patientList) {
        List<PatientListResponseDto> responseDtoList = new ArrayList<>();

        // 성별 코드 치환하기
        // e.g.) M->남, F->여
        CodeGroup codeGroup = codeRepository.findAllByCodeGroup("성별코드");
        Map<String, String> genderCodeNameMap = codeGroup.getCodeSet().stream()
                .collect(Collectors.toMap(Code::getCode, Code::getCodeName));


        for (Patient patient: patientList) {
            // 성별 코드 이름을 Map 에서 가져오기
            String genderCodeName = genderCodeNameMap.getOrDefault(patient.getGender(), "모름");

            // 방문일자를 최신순으로 정렬하여 가장 최근 날짜를 가져온다.
            String lastVisitDate;
            if (!patient.getVisitList().isEmpty()) {
                patient.getVisitList().sort((visit1, visit2) -> visit2.getVisitDate().compareTo(visit1.getVisitDate()));
                lastVisitDate = DateUtil.convertToString(patient.getVisitList().get(0).getVisitDate());
            } else {
                lastVisitDate = null;
            }

            PatientListResponseDto dto = new PatientListResponseDto(
                    patient.getName(),
                    patient.getChartId(),
                    genderCodeName,
                    patient.getBirthday(),
                    patient.getPhone(),
                    lastVisitDate
            );

            responseDtoList.add(dto);
        }

        return responseDtoList;
    }
}
