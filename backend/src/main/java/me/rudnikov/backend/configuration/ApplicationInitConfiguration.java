package me.rudnikov.backend.configuration;

import lombok.AllArgsConstructor;
import me.rudnikov.backend.entity.Course;
import me.rudnikov.backend.entity.CourseProgress;
import me.rudnikov.backend.entity.Professor;
import me.rudnikov.backend.entity.Student;
import me.rudnikov.backend.repository.CourseProgressRepository;
import me.rudnikov.backend.repository.CourseRepository;
import me.rudnikov.backend.repository.ProfessorRepository;
import me.rudnikov.backend.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@AllArgsConstructor
public class ApplicationInitConfiguration {

    private final StudentRepository studentRepository;
    private final ProfessorRepository professorRepository;
    private final CourseRepository courseRepository;
    private final CourseProgressRepository courseProgressRepository;

    @Bean
    public CommandLineRunner onStartupInit() {
        return (args) -> {

            Student firstStudent = Student.builder()
                    .fullName("Рудников Кирилл Глебович")
                    .address("пр. Гагарина 47, кв. 6476")
                    .email("kirill.rudnikov@gmail.com")
                    .phoneNumber("+76731651539")
                    .recordBook(543_455)
                    .build();

            Student secondStudent = Student.builder()
                    .fullName("Боканов Александр Дмитриевич")
                    .address("ул. Юмашева 8, кв. 12")
                    .email("alex.bokanov@gmail.com")
                    .phoneNumber("+71882195713")
                    .recordBook(112_455)
                    .build();

            Professor firstProfessor = Professor.builder()
                    .fullName("Никитин Олег Викторович")
                    .address("ул. Университетская 13, кв. 8")
                    .phoneNumber("+79856402345")
                    .payment(40000F)
                    .courses(null)
                    .build();

            Professor secondProfessor = Professor.builder()
                    .fullName("Виноградов Евгений Александрович")
                    .address("ул. Ерошенко 2, кв. 31")
                    .phoneNumber("+79856467805")
                    .payment(61000F)
                    .courses(null)
                    .build();

            Course firstCourse = Course
                    .builder()
                    .name("Программирование на Java 11")
                    .number(1)
                    .price(1000F)
                    .students(List.of(firstStudent, secondStudent))
                    .professor(firstProfessor)
                    .build();

            Course secondCourse = Course
                    .builder()
                    .name("WEB-программирование. Основы. (HTML, CSS)")
                    .number(2)
                    .price(5000F)
                    .students(List.of(secondStudent))
                    .professor(secondProfessor)
                    .build();

            Course thirdCourse = Course
                    .builder()
                    .name("Angular + Java Spring")
                    .number(3)
                    .price(15000F)
                    .students(List.of(firstStudent))
                    .professor(null)
                    .build();

            Course fourthCourse = Course
                    .builder()
                    .name("React")
                    .number(3)
                    .price(5000F)
                    .students(List.of(firstStudent, secondStudent))
                    .professor(firstProfessor)
                    .build();

            CourseProgress firstCourseProgress = CourseProgress.builder()
                    .student(firstStudent)
                    .course(firstCourse)
                    .grades(List.of(50F, 60F, 70F))
                    .build();

            CourseProgress secondCourseProgress = CourseProgress.builder()
                    .student(secondStudent)
                    .course(secondCourse)
                    .grades(List.of(75F, 60F, 70F))
                    .build();

            CourseProgress thirdCourseProgress = CourseProgress.builder()
                    .student(firstStudent)
                    .course(thirdCourse)
                    .grades(List.of(75F, 80F, 70F))
                    .build();

            CourseProgress fourthCourseProgress = CourseProgress.builder()
                    .student(firstStudent)
                    .course(fourthCourse)
                    .grades(List.of(80F, 80F, 75F))
                    .build();

            studentRepository.saveAll(List.of(firstStudent, secondStudent));
            professorRepository.saveAll(List.of(firstProfessor, secondProfessor));
            courseRepository.saveAll(List.of(firstCourse, secondCourse, thirdCourse, fourthCourse));
            courseProgressRepository.saveAll(List.of(firstCourseProgress, secondCourseProgress, thirdCourseProgress, fourthCourseProgress));
        };
    }

}