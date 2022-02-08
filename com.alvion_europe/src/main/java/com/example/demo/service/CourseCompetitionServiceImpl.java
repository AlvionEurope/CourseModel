package com.example.demo.service;

import com.example.demo.entity.Course;
import com.example.demo.entity.CourseCompletion;
import com.example.demo.repository.CourseCompetitionRepository;
import com.example.demo.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseCompetitionServiceImpl implements CourseCompetitionService {

    @Autowired
    private CourseCompetitionRepository courseCompetitionRepository;
    @Autowired
    private CourseRepository courseRepository;

    // может записаться (записан ли студент на конкретный курс)
    public boolean canAddStudentToCourse(String studentName, String courseName) {
        List<CourseCompletion> currentCompetition = courseCompetitionRepository.findByStudentAndCourse(studentName, courseName);
        if (currentCompetition != null) {
            return false;
        } else {
            return true;
        }
    }

    // найти все курсы на которые студент записан
    public List<Course> getCurrentStudentCourses(String studentName) {
        List<CourseCompletion> competitions = courseCompetitionRepository.findAllByStudent(studentName);
        List<Course> courses = competitions
                .stream()
                .map(competition -> courseRepository.findByName(competition.getCourseName()))
                .collect(Collectors.toList());

        return courses;
    }

    // получить средний балл по предмету
    public Float getAwerageGrade(String studentName, String courseName) {
        List<CourseCompletion> completion = courseCompetitionRepository.findAllByStudent(studentName);
        int sum = 0;
        List<Integer> grades = new ArrayList<>();
        for (CourseCompletion comp : completion) {
             grades = comp.getGrades();
            for (Integer grade : grades) {
                sum += grade;
            }
        }

        float average = sum / grades.size();
        return average;
    }

    // получить финальную оценку
    public int getFinalGrade(String studentName, String courseName) {
        Float average = getAwerageGrade(studentName, courseName);
        int res = Math.round(average);
        return res;
    }

    // добавить студента на курс
    public void addStudentToCourse(String studentName, String courseName) {
        CourseCompletion completion = new CourseCompletion(studentName, courseName, new ArrayList<>());
        courseCompetitionRepository.create(completion);
    }

    // удалить студента с курса
    public void removeStudentToCourse(String studentName, String courseName) {
        courseCompetitionRepository.deleteBy(studentName, courseName);
    }
}
