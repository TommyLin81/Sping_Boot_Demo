CREATE TABLE IF NOT EXISTS students
(
    id           INT PRIMARY KEY AUTO_INCREMENT,
    name         VARCHAR(30) COMMENT '學生名字',
    graduate     BOOLEAN COMMENT '是否畢業（0：未畢業｜1：已畢業）',
    created_date TIMESTAMP,
    updated_date TIMESTAMP
);

CREATE TABLE IF NOT EXISTS attendances
(
    id           INT PRIMARY KEY AUTO_INCREMENT,
    student_id   INT(10) NOT NULL COMMENT 'students.id',
    classroom_id INT(10) DEFAULT NULL COMMENT 'classrooms.id',
    attend_date  TIMESTAMP COMMENT '簽到的時間',
    created_date TIMESTAMP,
    updated_date TIMESTAMP
);

CREATE TABLE IF NOT EXISTS classrooms
(
    id           INT PRIMARY KEY AUTO_INCREMENT,
    name         VARCHAR(30) COMMENT '教室名稱',
    created_date TIMESTAMP,
    updated_date TIMESTAMP
);

INSERT INTO students (name, graduate, created_date, updated_date)
VALUES ('Amy', true, '2021-09-01 10:20:33', '2021-09-01 10:20:33');
INSERT INTO students (name, graduate, created_date, updated_date)
VALUES ('Rom', false, '2021-08-10 17:21:14', '2021-08-10 17:21:14');
INSERT INTO students (name, graduate, created_date, updated_date)
VALUES ('Judy', true, '2021-09-05 12:19:48', '2021-09-05 12:19:48');
INSERT INTO students (name, graduate, created_date, updated_date)
VALUES ('Mike', true, '2021-09-03 15:01:15', '2021-09-03 15:01:15');

INSERT INTO classrooms (name, created_date, updated_date)
VALUES ('A101', '2022-01-01 08:00:00', '2022-01-01 08:00:00');
INSERT INTO classrooms (name, created_date, updated_date)
VALUES ('A102', '2022-01-01 08:00:00', '2022-01-01 08:00:00');
INSERT INTO classrooms (name, created_date, updated_date)
VALUES ('B301', '2022-01-01 08:00:00', '2022-01-01 08:00:00');

INSERT INTO attendances (student_id, classroom_id, attend_date, created_date, updated_date)
VALUES (1, 1, '2022-01-10 08:01:01', '2022-01-10 08:01:01', '2022-01-10 08:01:01');
INSERT INTO attendances (student_id, classroom_id, attend_date, created_date, updated_date)
VALUES (2, 1, '2022-01-10 08:02:02', '2022-01-10 08:02:02', '2022-01-10 08:02:02');
INSERT INTO attendances (student_id, classroom_id, attend_date, created_date, updated_date)
VALUES (3, 1, '2022-01-10 08:03:03', '2022-01-10 08:03:03', '2022-01-10 08:03:03');
INSERT INTO attendances (student_id, classroom_id, attend_date, created_date, updated_date)
VALUES (4, 1, '2022-01-10 09:04:04', '2022-01-10 09:04:04', '2022-01-10 09:04:04');

