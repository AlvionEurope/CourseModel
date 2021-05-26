package ru.alex.courseModel.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.alex.courseModel.entity.Instructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class InstructorDto {

    private long id;
    private String name;
    private String address;
    private String phone;
    private float payment;

    public InstructorDto(Instructor instructor) {
        this.id = instructor.getId();
        this.name = instructor.getName();
        this.phone = instructor.getPhone();
        this.address = instructor.getAddress();
        this.payment = instructor.getPayment();
    }

    public static List<InstructorDto> getInstructorDtoList(List<Instructor> instructors){
        if (instructors == null){
            return new ArrayList<>();
        }
        List<InstructorDto> instructorDtoList = new ArrayList<>();
        for(Instructor instructor : instructors){
            instructorDtoList.add(new InstructorDto(instructor));
        }
        return instructorDtoList;
    }
}
