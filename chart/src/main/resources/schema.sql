DROP TABLE IF EXISTS CODE_GROUP;
DROP TABLE IF EXISTS CODE;

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