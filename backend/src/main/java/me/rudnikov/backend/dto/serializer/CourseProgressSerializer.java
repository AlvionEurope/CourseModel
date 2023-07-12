package me.rudnikov.backend.dto.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import me.rudnikov.backend.dto.read.CourseProgressDto;

import java.io.IOException;

public class CourseProgressSerializer extends StdSerializer<CourseProgressDto> {

    public CourseProgressSerializer() {
        this(null);
    }

    protected CourseProgressSerializer(Class<CourseProgressDto> t) {
        super(t);
    }

    @Override
    public void serialize(CourseProgressDto value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        {
            gen.writeNumberField("id", value.getId());
            gen.writeFieldName("student");
            {
                gen.writeStartObject();
                {
                    gen.writeNumberField("id", value.getStudentId());
                    gen.writeStringField("fullName", value.getStudentFullName());
                    gen.writeNumberField("recordBook", value.getStudentRecordBook());
                }
                gen.writeEndObject();
            }
            gen.writeFieldName("course");
            {
                gen.writeStartObject();
                {
                    gen.writeNumberField("id", value.getCourseId());
                    gen.writeStringField("name", value.getCourseName());
                }
                gen.writeEndObject();
            }
            gen.writeFieldName("grades");
            gen.writeObject(value.getGrades());
        }
        gen.writeEndObject();
    }

}