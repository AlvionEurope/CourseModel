package me.rudnikov.backend.dto.serializer;

import me.rudnikov.backend.dto.read.CourseProgressDto;
import me.rudnikov.backend.dto.read.StudentDto;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class StudentSerializer extends StdSerializer<StudentDto> {

    public StudentSerializer() {
        this(null);
    }

    protected StudentSerializer(Class<StudentDto> t) {
        super(t);
    }

    @Override
    public void serialize(StudentDto value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        {
            gen.writeNumberField("id", value.getId());
            gen.writeStringField("fullName", value.getFullName());
            gen.writeStringField("address", value.getAddress());
            gen.writeStringField("phoneNumber", value.getPhoneNumber());
            gen.writeStringField("email", value.getEmail());
            gen.writeNumberField("recordBook", value.getRecordBook());
            if (value.getAveragePerformance() != null) {
                gen.writeNumberField("averagePerformance", value.getAveragePerformance());
            }

            gen.writeArrayFieldStart("courseProgress");
            {
                for (CourseProgressDto courseProgress : value.getCourseProgressList()) {
                    gen.writeStartObject();
                    gen.writeNumberField("id", courseProgress.getId());
                    gen.writeFieldName("course");
                    {
                        gen.writeStartObject();
                        {
                            gen.writeNumberField("id", courseProgress.getCourseId());
                            gen.writeStringField("name", courseProgress.getCourseName());
                        }
                        gen.writeEndObject();
                        gen.writeFieldName("grades");
                        gen.writeObject(courseProgress.getGrades());
                    }
                    gen.writeEndObject();
                }
            }
            gen.writeEndArray();

        }
        gen.writeEndObject();
    }

}