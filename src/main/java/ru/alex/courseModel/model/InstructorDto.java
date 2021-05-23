package ru.alex.courseModel.model;

import lombok.Data;
import ru.alex.courseModel.entity.Course;
import ru.alex.courseModel.entity.Instructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class InstructorDto {

    private long id;
    private String name;
    private String address;
    private String phone;
    private float payment;
    private List<CourseDto> courseDtoList;

    public List<InstructorDto> getInstructorDtoList(List<Instructor> instructors){
        List<InstructorDto> instructorDtoList = new ArrayList<>();

        for(Instructor instructor : instructors){
            courseDtoList = new ArrayList<>();
            InstructorDto instructorDto = new InstructorDto();
            instructorDto.setId(instructor.getId());
            instructorDto.setName(instructor.getName());
            instructorDto.setAddress(instructor.getAddress());
            instructorDto.setPhone(instructor.getPhone());
            instructorDto.setPayment(instructor.getPayment());
            for(Course course : instructor.getCourses()){
                CourseDto courseDto = new CourseDto();
                courseDto.setId(course.getId());
                courseDto.setName(course.getName());
                courseDto.setCost(course.getCost());
                courseDtoList.add(courseDto);
            }
            instructorDto.setCourseDtoList(courseDtoList);
            instructorDtoList.add(instructorDto);
        }
        return instructorDtoList;
    }
}
