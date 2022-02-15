package example;

import example.entity.Course;
import example.entity.Student;
import example.entity.Teacher;
import example.service.CourseService;
import example.service.EnrollmentService;
import example.service.StudentService;
import example.service.TeacherService;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }


    @Bean
    CommandLineRunner dbInitializer(TeacherService teacherService,
                                    StudentService studentService,
                                    CourseService courseService,
                                    EnrollmentService enrollmentService) {
        return args -> {
            if (studentService.getStudents().isEmpty()) {
                studentService.saveStudent(new Student(null, "Tom", "Tom Address",
                        "1123", "tom@test.com", 153, null, null));
                studentService.saveStudent(new Student(null, "Sam", "Sam Address",
                        "2123", "sam@test.com", 274, null, null));
            }
            if (teacherService.getTeachers().isEmpty()) {
                teacherService.saveTeacher(new Teacher(null, "Bob", "bob@test.com",
                        "8123", 100, null));
                teacherService.saveTeacher(new Teacher(null, "Alice", "alice@test.com",
                        "7456", 150, null));
                teacherService.saveTeacher(new Teacher(null, "Jack", "jack@test.com",
                        "2222", 100, null));
            }
            if (courseService.getCourses().isEmpty()) {
                courseService.saveCourse(new Course(null, "CS", 72, 2000));
                courseService.saveCourse(new Course(null, "Design", 51, 1500));
                courseService.saveCourse(new Course(null, "Java", 33, 1000));

            }

            if (enrollmentService.getGrades().isEmpty()) {
                Course cs = courseService.getCourseByNum(72).orElseThrow();
                Course design = courseService.getCourseByNum(51).orElseThrow();
                Course java = courseService.getCourseByNum(33).orElseThrow();

                Student tom = studentService.getStudentByEmail("tom@test.com").orElseThrow();
                Student sam = studentService.getStudentByEmail("sam@test.com").orElseThrow();

                Teacher alice = teacherService.getTeacherByPhone("7456").orElseThrow();
                Teacher bob = teacherService.getTeacherByPhone("8123").orElseThrow();

                courseService.addStudent(cs.getId(), tom.getId());

                courseService.addStudent(design.getId(), tom.getId());
                courseService.addStudent(design.getId(), sam.getId());

                courseService.addStudent(java.getId(), sam.getId());

                courseService.addTeacher(cs.getId(), alice.getId());
                courseService.addTeacher(cs.getId(), bob.getId());
                courseService.addTeacher(design.getId(), bob.getId());

                enrollmentService.addGrade(tom.getId(), cs.getId(), 5);
                enrollmentService.addGrade(tom.getId(), cs.getId(), 4);
                enrollmentService.addGrade(tom.getId(), cs.getId(), 5);
                enrollmentService.addGrade(tom.getId(), design.getId(), 5);
                enrollmentService.addGrade(tom.getId(), design.getId(), 5);

                enrollmentService.addGrade(sam.getId(), design.getId(), 4);
                enrollmentService.addGrade(sam.getId(), design.getId(), 3);
                enrollmentService.addGrade(sam.getId(), design.getId(), 3);
                enrollmentService.addGrade(sam.getId(), java.getId(), 3);
                enrollmentService.addGrade(sam.getId(), java.getId(), 2);
            }
        };
    }


    @Bean
    ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();

        return mapper;
    }
}
