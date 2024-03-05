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