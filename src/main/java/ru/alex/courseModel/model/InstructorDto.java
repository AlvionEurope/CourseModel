package ru.alex.courseModel.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.alex.courseModel.entity.Course;
import ru.alex.courseModel.entity.Instructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
public class InstructorDto {

    private long id;
    private String name;
    private String address;
    private String phone;
    private float payment;
//    private List<CourseDto> courseDtoList;

    public InstructorDto(Instructor instructor) {
        this.id = instructor.getId();
        this.name = instructor.getName();
        this.phone = instructor.getPhone();
        this.address = instructor.getAddress();
        this.payment = instructor.getPayment();
    }

    public List<InstructorDto> getInstructorDtoList(List<Instructor> instructors){
        List<InstructorDto> instructorDtoList = new ArrayList<>();

        for(Instructor instructor : instructors){
//            courseDtoList = new ArrayList<>();
            InstructorDto instructorDto = new InstructorDto(instructor);
//            for(Course course : instructor.getCourses()){
//                CourseDto courseDto = new CourseDto(course);
//                courseDtoList.add(courseDto);
//            }
//            instructorDto.setCourseDtoList(courseDtoList);
            instructorDtoList.add(instructorDto);
        }
        return instructorDtoList;
    }
}
