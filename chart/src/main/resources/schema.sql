DROP TABLE IF EXISTS CODE_GROUP;
DROP TABLE IF EXISTS CODE;
DROP TABLE IF EXISTS HOSPITAL;
DROP TABLE IF EXISTS PATIENT;
DROP TABLE IF EXISTS VISIT;

CREATE TABLE CODE_GROUP
(
    code_group      VARCHAR(10)  NOT NULL,
    code_group_name VARCHAR(10)  NOT NULL,
    description     VARCHAR(100) NOT NULL,
    PRIMARY KEY (code_group)
);

CREATE TABLE CODE
(
    code VARCHAR(10) NOT NULL,
    code_name VARCHAR(10) NOT NULL,
    code_group VARCHAR(10) NOT NULL,
    PRIMARY KEY (code),
    FOREIGN KEY (code_group) REFERENCES code_group(code_group)
);

CREATE TABLE HOSPITAL
(
    hospital_id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(45) NOT NULL,
    organization_id VARCHAR(20) NOT NULL,
    director_name VARCHAR(10) NOT NULL,
    PRIMARY KEY (hospital_id)
);

CREATE TABLE PATIENT
(
    patient_id BIGINT NOT NULL AUTO_INCREMENT,
    hospital_id BIGINT NOT NULL,
    name VARCHAR(45) NOT NULL,
    chart_id VARCHAR(13) NOT NULL,
    gender VARCHAR(10) NOT NULL,
    birthday VARCHAR(10),
    phone VARCHAR(20),
    use_yn VARCHAR(2) NOT NULL,
    PRIMARY KEY (patient_id, hospital_id)
);

CREATE TABLE VISIT
(
    visit_id BIGINT NOT NULL AUTO_INCREMENT,
    hospital_id BIGINT NOT NULL,
    patient_id BIGINT NOT NULL,
    visit_date DATE NOT NULL,
    visit_code VARCHAR(10) NOT NULL,
    PRIMARY KEY (visit_id)
);