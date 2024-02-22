package com.example.course.service;

import com.example.course.model.Course;
import com.example.course.model.Professor;
import com.example.course.repository.CourseRepository;
import com.example.course.repository.ProfessorRepository;
import com.example.course.util.ExcelUtil;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ProfessorServiceImpl implements ProfessorService {

    static ProfessorRepository repository;

    final CourseRepository courseRepository;

    public ProfessorServiceImpl(ProfessorRepository repository, CourseRepository courseRepository) {
        this.repository = repository;
        this.courseRepository = courseRepository;
    }

    @Override
    public Professor postProfessor(Professor professor) {

        return repository.save(professor);
    }

    @Override
    public Professor getProfessor(Long professorId) {
        return repository.getReferenceById(professorId);
    }

    @Override
    public void deleteProfessor(Long professorId) {
        repository.deleteById(professorId);
    }

    @Override
    public Professor putProfessor(Long professorId, Professor professor) throws Exception {

        if (repository.existsById(professorId)) {
            Professor professor1 = repository.getReferenceById(professorId);
            professor1.setName(professor.getName());
            professor1.setAddress(professor.getAddress());
            professor1.setTel(professor.getTel());
            professor1.setPayment(paymentCourses(professorId));
            return repository.save(professor1);
        } else {
            throw new Exception("Not found Professor");
        }

    }

    @Override
    public void addProfessorCourse(Long professorId, Long courseId) {
        repository.addProfessorCourse(professorId, courseId);
        Professor professor = repository.getReferenceById(professorId);
        professor.setPayment(paymentCourses(professorId));
        repository.save(professor);
    }

    @Override
    public ByteArrayInputStream getDataDownloaded() throws IOException {
        List<Professor> professorList = repository.findAll();
        return ExcelUtil.dataToExcel(professorList);
    }

    //Оплата профессору
    public Float paymentCourses(Long professorId) {
        List<Long> listCoursesId = repository.getListCourseId(professorId);
        Float payment = 0.0F;
        for (Course course : courseRepository.findAllById(listCoursesId)) {
            payment = course.getPrice() + payment;
        }
        return payment;
    }

    //Суммарное кол-во студентов у профессора
    public static Integer getAllNumberOfStudent(Long professorId) {
        List<Long> listCoursesId = repository.getListCourseId(professorId);
        Integer students = 0;
        for (Long courseId : listCoursesId) {
            students = repository.getNumberOfStudent(courseId) + students;
        }
        return students;
    }

    //Средняя успеваемость студентов по всем курсам
    public static Float getAVGStudentPerformance(Long professorId) {
        List<Long> listCoursesId = repository.getListCourseId(professorId);
        Float AVGPoints = 0.0F;
        for (Long courseId : listCoursesId) {
            AVGPoints = repository.getAvgPointAllStudent(courseId) + AVGPoints;
        }
        return AVGPoints;
    }

}
