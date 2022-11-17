package ru.wxw.coursemodel.wxw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.wxw.coursemodel.wxw.entity.CourseStudent;
import ru.wxw.coursemodel.wxw.repository.CourseStudentRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class CourseStudentService {
    @Autowired
    private final CourseStudentRepository courseStudentRepository;

    public CourseStudentService(CourseStudentRepository courseStudentRepository) {
        this.courseStudentRepository = courseStudentRepository;
    }
    public List<CourseStudent> findAll(){
        return  courseStudentRepository.findAll();
    }
}
