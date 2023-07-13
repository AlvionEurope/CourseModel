package me.rudnikov.backend.configuration;

import me.rudnikov.backend.entity.Course;
import me.rudnikov.backend.entity.CourseProgress;
import me.rudnikov.backend.entity.Professor;
import me.rudnikov.backend.entity.Student;
import me.rudnikov.backend.repository.CourseProgressRepository;
import me.rudnikov.backend.repository.CourseRepository;
import me.rudnikov.backend.repository.ProfessorRepository;
import me.rudnikov.backend.repository.StudentRepository;
import me.rudnikov.backend.service.SubscriptionService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Configuration
@AllArgsConstructor
public class ApplicationInitConfiguration {
    private final StudentRepository studentRepository;
    private final ProfessorRepository professorRepository;
    private final CourseRepository courseRepository;
    private final CourseProgressRepository courseProgressRepository;

    private final SubscriptionService subscriptionService;

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
                    .professor(firstProfessor)
                    .build();

            Course secondCourse = Course
                    .builder()
                    .name("WEB-программирование. Основы. (HTML, CSS)")
                    .number(2)
                    .price(5000F)
                    .professor(firstProfessor)
                    .build();

            Course thirdCourse = Course
                    .builder()
                    .name("Angular + Java Spring")
                    .number(3)
                    .price(15000F)
                    .professor(secondProfessor)
                    .build();

            Course fourthCourse = Course
                    .builder()
                    .name("React")
                    .number(3)
                    .price(25000F)
                    .professor(null)
                    .build();

            studentRepository.saveAll(List.of(firstStudent, secondStudent));
            professorRepository.saveAll(List.of(firstProfessor, secondProfessor));
            courseRepository.saveAll(List.of(firstCourse, secondCourse, thirdCourse, fourthCourse));

            subscriptionService.subscribeStudentToCourse(1L, 1L);
            subscriptionService.subscribeStudentToCourse(1L, 2L);
            subscriptionService.subscribeStudentToCourse(2L, 1L);
            subscriptionService.subscribeStudentToCourse(1L, 3L);
            subscriptionService.subscribeStudentToCourse(2L, 4L);

            List<CourseProgress> courseProgressList = courseProgressRepository.findAll();
            courseProgressList.forEach(courseProgress -> courseProgress.setGrades(getRandomGradeList()));
            courseProgressRepository.saveAll(courseProgressList);
        };
    }

    private List<Float> getRandomGradeList() {
        List<Float> randomGradeList = new ArrayList<>();
        Random random = new Random();

        int numberOfElements = random.nextInt(3) + 3;

        for (int i = 0; i < numberOfElements; i++) {
            float value = random.nextFloat() * 40 + 60;
            randomGradeList.add(value);
        }

        return randomGradeList;
    }
}