package com.chmun.chart.domain.patient;

import com.chmun.chart.domain.hospital.Hospital;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.util.StringUtils;

import org.springframework.data.domain.Pageable;
import java.util.List;

public class PatientRepositoryImpl implements PatientRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    public PatientRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public List<Patient> findPatient(Hospital hospital, String name, String chartId, String birthday, String useYn) {
        QPatient patient = QPatient.patient;

        // BooleanExpression 을 이용해서 where 조건을 동적으로 생성
        return queryFactory
                .selectFrom(patient)
                .where(
                        patient.hospital.eq(hospital),  // 병원 ID
                        nameStartsWith(name),
                        chartIdStartsWith(chartId),
                        birthdayStartsWith(birthday),
                        patient.useYn.eq(useYn)  // 삭제 여부
                ).orderBy(patient.patientId.desc())
                .fetch();
    }

    @Override
    public Page<Patient> findPatientWithPaging(Hospital hospital, String name, String chartId, String birthday, String useYn, Pageable pageable) {
        QPatient patient = QPatient.patient;

        // BooleanExpression 을 이용해서 where 조건을 동적으로 생성
        List<Patient> queryResults = queryFactory
                .selectFrom(patient)
                .where(
                        patient.hospital.eq(hospital),  // 병원 ID
                        nameStartsWith(name),
                        chartIdStartsWith(chartId),
                        birthdayStartsWith(birthday),
                        patient.useYn.eq(useYn)  // 삭제 여부
                ).orderBy(patient.patientId.desc())
                .offset(pageable.getOffset())  // 페이징 처리
                .limit(pageable.getPageSize())
                .fetch();

        // 전체 카운트 조회
        Long count = queryFactory
                .select(patient.count())
                .from(patient)
                .where(
                        patient.hospital.eq(hospital),  // 병원 ID
                        nameStartsWith(name),
                        chartIdStartsWith(chartId),
                        birthdayStartsWith(birthday),
                        patient.useYn.eq(useYn)  // 삭제 여부
                )
                .fetchOne();

        return new PageImpl<>(queryResults, pageable, count);
    }

    private BooleanExpression nameStartsWith(String name){
        return StringUtils.hasText(name) ? QPatient.patient.name.startsWith(name) : null;  // "name%"
    }

    private BooleanExpression chartIdStartsWith(String chartId) {
        return StringUtils.hasText(chartId) ? QPatient.patient.chartId.startsWith(chartId) : null;  // "chartId%"
    }

    private BooleanExpression birthdayStartsWith(String birthday) {
        return StringUtils.hasText(birthday) ? QPatient.patient.birthday.startsWith(birthday) : null;  // "birthday%"
    }
}
