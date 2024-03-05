INSERT INTO CODE_GROUP VALUES ('성별코드', '성별코드', '성별을 표시');
INSERT INTO CODE_GROUP VALUES ('방문상태코드', '방문상태코드', '환자방문의 상태(방문중, 종료, 취소)');
INSERT INTO CODE_GROUP VALUES ('진료과목코드', '진료과목코드', '진료과목(내과, 안과 등)');
INSERT INTO CODE_GROUP VALUES ('진료유형코드', '진료유형코드', '진료의 유형(약처방, 검사 등)');

INSERT INTO CODE VALUES ('M', '남', '성별코드');
INSERT INTO CODE VALUES ('F', '여', '성별코드');
INSERT INTO CODE VALUES ('X', '모름', '성별코드');  -- 기획서 참고 (성별 값이 '모름'인 케이스가 있음.)
INSERT INTO CODE VALUES ('1', '방문중', '방문상태코드');
INSERT INTO CODE VALUES ('2', '종료', '방문상태코드');
INSERT INTO CODE VALUES ('3', '취소', '방문상태코드');
INSERT INTO CODE VALUES ('01', '내과', '진료과목코드');
INSERT INTO CODE VALUES ('02', '안과', '진료과목코드');
INSERT INTO CODE VALUES ('D', '약처방', '진료과목코드');
INSERT INTO CODE VALUES ('T', '검사', '진료과목코드');

INSERT INTO HOSPITAL (name, organization_id, director_name) VALUES ('인천병원', '1111111111', '손흥민');
INSERT INTO HOSPITAL (name, organization_id, director_name) VALUES ('서울병원', '2222222222', '황희찬');
INSERT INTO HOSPITAL (name, organization_id, director_name) VALUES ('경기병원', '3333333333', '김민재');

INSERT INTO PATIENT (hospital_id, name, chart_id, gender, birthday, phone) VALUES (1, '테스트', '201900085', 'X', null, null);  -- 1
INSERT INTO PATIENT (hospital_id, name, chart_id, gender, birthday, phone) VALUES (1, '테스트', '201900086', 'M', '1985-11-11', null);  -- 2
INSERT INTO PATIENT (hospital_id, name, chart_id, gender, birthday, phone) VALUES (1, '테스트', '201900087', 'X', null, null);  -- 3
INSERT INTO PATIENT (hospital_id, name, chart_id, gender, birthday, phone) VALUES (1, '테스트', '201900088', 'M', '1922-11-11', null);  -- 4
INSERT INTO PATIENT (hospital_id, name, chart_id, gender, birthday, phone) VALUES (1, '테스트', '201900089', 'M', '2005-11-23', '010-1111-2222');  -- 5
INSERT INTO PATIENT (hospital_id, name, chart_id, gender, birthday, phone) VALUES (1, '테스트', '201900090', 'M', '2016-11-11', null);  -- 6
INSERT INTO PATIENT (hospital_id, name, chart_id, gender, birthday, phone) VALUES (1, '테스트', '201900091', 'M', '2015-11-11', null);  -- 7
INSERT INTO PATIENT (hospital_id, name, chart_id, gender, birthday, phone) VALUES (1, '테스트', '201900092', 'M', '2016-11-11', null);  -- 8
INSERT INTO PATIENT (hospital_id, name, chart_id, gender, birthday, phone) VALUES (1, '테스트', '201900093', 'F', '1922-11-11', null);  -- 9
INSERT INTO PATIENT (hospital_id, name, chart_id, gender, birthday, phone) VALUES (1, '테스트', '201900094', 'F', '1922-11-11', null);  -- 10