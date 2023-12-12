package com.courseModel;

import com.courseModel.entity.Course;
import com.courseModel.entity.Student;

import com.courseModel.repository.CourseRepository;
import com.courseModel.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@SpringBootApplication
@ComponentScan(basePackages = "com.courseModel.*")
@EntityScan("com.courseModel.*")

public class CourseModelApplication implements CommandLineRunner {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    CourseRepository courseRepository;
//    @Autowired
//    ProfessorRepository professorRepository;
//    @Autowired
//    CourseRepository courseRepository;

    public static void main(String[] args) {
        SpringApplication.run(CourseModelApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("http://localhost:8080/swagger-ui.html#");
        System.out.println("http://localhost:8080/h2-console");
//        Student student1 = new Student("Дима", "Рязань", "8-920-888-40-45", "dima@mail.com", 14563, 4.5F);
//        Student student2 = new Student("Саша", "Рязань", "8-920-777-47-58", "sasha@mail.com", 14546, 3.5F);
//        Student student3 = new Student("Рома", "Владивосток", "8-920-555-36-66", "roma@mail.com", 13211, 3.8F);
//        Student student4 = new Student("Вова", "Рязань", "8-920-444-44-81", "vova@mail.com", 4581, 4.4F);
//        Student student5 = new Student("Денис", "Москва", "8-920-111-45-68", "denis@mail.com", 4858, 4.2F);
//        Student student1 = new Student()
//                .setName("Petr")
//                .setAddress("Ryazany")
//                .setPhone("14563");
//
//        studentRepository.save(student1);
//        List<Student> all = studentRepository.findAll();
//        System.out.println();
//        Professor professor1 = new Professor("Дмитрий", "Рязань", "8-920-888-40-45", 452.23F);
//        Professor professor2 = new Professor("Всеволод", "Рязань", "8-920-888-40-46", 562.91F);
//        Professor professor3 = new Professor("Юрий", "Рязань", "8-920-888-40-49", 488.55F);
//
//        Course course1 = new Course()
//                .setCourseName("Java")
//                .setCost(120.0F);
//        courseRepository.save(course1);
//        Course course2 = new Course()
//                .setCourseName("PHP")
//                .setCost(90.54F);
//        courseRepository.save(course2);
//        Course course2 = new Course("PHP", 10, 80.0F);
//        Course course3 = new Course("JS", 11, 133.0F);
//        Course course4 = new Course("Python", 7, 115.5F);
    }
}
