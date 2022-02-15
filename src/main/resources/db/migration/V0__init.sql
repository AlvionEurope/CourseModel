create table student
(
    id      serial primary key,
    name    varchar(255) not null,
    address varchar(255) not null,
    phone   varchar(255) not null unique,
    email   varchar(255) not null unique,
    book_id integer      not null unique
);


create table teacher
(
    id      serial primary key,
    name    varchar(255) not null,
    address varchar(255) not null,
    phone   varchar(255) not null unique,
    payment float        not null
);


create table course
(
    id    serial primary key,
    title varchar(255) not null,
    num   integer      not null unique,
    cost  float        not null
);


create table teacher_courses
(
    teacher_id integer not null,
    course_id  integer not null,

    foreign key (teacher_id) references teacher (id),
    foreign key (course_id) references course (id),
    primary key (teacher_id, course_id)
);


create table student_courses
(
    student_id integer not null,
    course_id  integer not null,

    foreign key (student_id) references student (id),
    foreign key (course_id) references course (id),
    primary key (student_id, course_id)
);


create table course_enrollment
(
    id         bigserial primary key,
    student_id integer not null,
    course_id  integer not null,
    grade      float   not null,

    foreign key (student_id) references student (id),
    foreign key (course_id) references course (id)
);
