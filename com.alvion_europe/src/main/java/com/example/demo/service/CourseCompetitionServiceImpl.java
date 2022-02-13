package com.example.demo.service;

import com.example.demo.entity.CourseCompletion;
import com.example.demo.exeption.ExeptionMessage;
import com.example.demo.repository.CourseCompetitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseCompetitionServiceImpl implements CourseCompetitionService {

    @Autowired
    private CourseCompetitionRepository courseCompetitionRepository;

     // Записываем студента на курс и проверяем был ли он ранее записан на тот же курс, если был то запись запрещена!
    public void create(CourseCompletion completion) {
        boolean res = courseCompetitionRepository.canAddStudentToCourse(completion.getStudentName(),completion.getCourseName());
        if (res) {
            courseCompetitionRepository.create(completion);
        } else {
            throw new ExeptionMessage("студент уже записан на данный курс!");
        }
    }

    // найти все курсы на которые студент записан
    public List<String> getCurrentStudentCourses(String studentName) {
        List<String> courses = courseCompetitionRepository.getCurrentStudentCourses(studentName);
        return courses;
    }

    // получить средний балл по предмету
    public Float getAwerageGrade(String studentName) {
      return courseCompetitionRepository.getAwerageGrade(studentName);
    }

    // получить финальную оценку
    public int getFinalGrade(String studentName) {
        Float average = courseCompetitionRepository.getFinalGrade(studentName);
        int res = Math.round(average);
        return res;
    }

    // удалить студента с курса и проверяем если данного студента нет на данном курсе то удалять запрещаем!
    public void removeStudentToCourse(String studentName, String courseName) {
        CourseCompletion completion = courseCompetitionRepository.findByStudentAndCourse(studentName, courseName);
        if (completion != null) {
            courseCompetitionRepository.deleteBy(studentName, courseName);
        }else {
            throw new ExeptionMessage("Данного студента нет на этом курсе или он уже был удален ранее!");
        }
    }
}
