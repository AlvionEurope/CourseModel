CREATE TABLE professor(
                          id SERIAL PRIMARY KEY,
                          name VARCHAR(100) NOT NULL,
                          address varchar(100),
                          telephone varchar(100),
                          payment decimal
);

CREATE TABLE course(
                       id SERIAL PRIMARY KEY,
                       name VARCHAR(100) NOT NULL,
                       number int UNIQUE,
                       cost decimal,
                       professor_id int REFERENCES professor(id) ON DELETE SET NULL
);

CREATE TABLE student(
                        id SERIAL PRIMARY KEY,
                        name VARCHAR(100) NOT NULL,
                        address varchar(100),
                        telephone varchar(100),
                        email varchar(100),
                        grade_book int UNIQUE,
                        academic_performance decimal
);
CREATE TABLE coursestage(
                            id SERIAL PRIMARY KEY,
                            course_id int REFERENCES course(id),
                            student_id int REFERENCES student(id),
                            grade int
);
CREATE TABLE coursestudent(
                              id SERIAL PRIMARY KEY,
                              course_id int REFERENCES course(id),
                              student_grade_book int,
                              is_finished boolean,
                            CONSTRAINT course_student_uniq UNIQUE (course_id, student_grade_book)
);
CREATE TABLE report(
                              id SERIAL PRIMARY KEY,
                              nameProfessor VARCHAR(100),
                              sumStudentAllCourse int,
                              averageStageAllStudent int
);

INSERT INTO student  (name, address, telephone, email, grade_book, academic_performance)
VALUES
    ('Ivan', 'Tomsk', +79566621548, 'ivan@email.ru',1235, 4.3),
    ('Stepan', 'Moscow', +79524445551, 'stepa@mail.ru', 1236, 4.2),
    ('Dima', 'Omsk', +79534446665, 'dima@mail.ru',1237, 3.9),
    ('Petya', 'Irkutsk', +79586321568, 'petya@list.ru',1238, 4.8),
    ('Fedor', 'Sochi', +79954632154, 'fedor1@gmail.com',1239, 4.4),
    ('Nina', 'Krasnodar', +79563214568, 'Nina25@ya.ru',3335, 3.7),
    ('Ira', 'Vladivostok', +79654821345, 'ira222@ya.ru',3336, 4.3),
    ('Igor', 'Samara', +79562849578, 'iigor444@ya.ru',3337, 4.5),
    ('Vasya', 'Novosibirsk', +79598547824, 'ivasyanov@gmail.com',3339, 4.6),
    ('Liza', 'Almata', +79656321448, 'lizaalmata@ya.ru',3338, 3.5);

INSERT INTO professor  (name, address, telephone, payment)
VALUES
    ('Petr Nikolaevich', 'Samara', +795212345687, 10000),
    ('Irina Vladimirovna', 'Krasnodar', +795156984756, 11000),
    ('Ivan Petrovich', 'Moscow', +795896542155, 9000),
    ('Konstantin Ivanovich', 'Habarovsk', +795311455785, 8000);

INSERT INTO course  (name, number, cost, professor_id)
VALUES
    ('Literature', 5441, 3000, 1),
    ('Mathematics', 5444, 2000, 3),
    ('Russian language', 6458, 1000, 4),
    ('English language', 4458, 3500, 1),
    ('Biology', 3128, 4000, null);

INSERT INTO coursestudent  (course_id, student_grade_book, is_finished)
VALUES
    (1, 1235, false),
    (1, 1236, false),
    (1, 1237, false),
    (1, 1238, true),
    (1, 1239, false),
    (2, 3335, true),
    (2, 3336, false),
    (2, 3337, false),
    (2, 1237, false),
    (2, 3339, false),
    (2, 3338, true),
    (2, 1235, false),
    (2, 1236, false),
    (3, 3337, false),
    (3, 1237, false),
    (3, 3339, false),
    (3, 1239, true),
    (3, 1235, false),
    (3, 1236, false),
    (4, 1235, false),
    (4, 1236, true),
    (4, 3337, false),
    (4, 3338, false),
    (5, 1238, false),
    (5, 1239, false),
    (5, 1236, true),
    (5, 3335, false),
    (5, 3336, false);

INSERT INTO coursestage  (course_id, student_id, grade)
VALUES
    (1, 1, 3),
    (1, 2, 4),
    (1, 3, 3),
    (1, 4, 2),
    (1, 5, 4),
    (1, 1, 3),
    (1, 2, 4),
    (1, 3, 2),
    (1, 4, 5),
    (1, 5, 4),
    (1, 1, 3),
    (1, 2, 5),
    (1, 3, 2),
    (1, 4, 5),
    (1, 5, 5),
    (2, 1, 3),
    (2, 2, 5),
    (2, 3, 4),
    (2, 6, 3),
    (2, 7, 5),
    (2, 8, 2),
    (2, 9, 5),
    (2, 10, 5),
    (2, 1, 5),
    (2, 2, 4),
    (2, 3, 5),
    (2, 6, 4),
    (2, 7, 3),
    (2, 8, 4),
    (2, 9, 4),
    (2, 10, 4),
    (3, 8, 5),
    (3, 3, 4),
    (3, 9, 3),
    (3, 5, 4),
    (3, 1, 4),
    (3, 2, 4),
    (3, 8, 4),
    (3, 3, 5),
    (3, 9, 4),
    (3, 5, 5),
    (3, 1, 3),
    (3, 2, 5),
    (4, 1, 4),
    (4, 2, 5),
    (4, 8, 3),
    (4, 10, 5),
    (4, 1, 5),
    (4, 2, 5),
    (4, 8, 4),
    (4, 10, 3),
    (4, 1, 5),
    (4, 2, 5),
    (4, 8, 4),
    (4, 10, 4),
    (5, 5, 3),
    (5, 4, 5),
    (5, 2, 5),
    (5, 6, 4),
    (5, 7, 4),
    (5, 5, 5),
    (5, 4, 4),
    (5, 2, 4),
    (5, 6, 5),
    (5, 7, 5),
    (5, 5, 3),
    (5, 4, 5),
    (5, 2, 5),
    (5, 6, 5),
    (5, 7, 5);