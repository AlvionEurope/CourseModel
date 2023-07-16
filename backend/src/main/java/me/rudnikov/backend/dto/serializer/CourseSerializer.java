package me.rudnikov.backend.dto.serializer;

import me.rudnikov.backend.dto.read.CourseDto;
import me.rudnikov.backend.dto.read.StudentDto;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class CourseSerializer extends StdSerializer<CourseDto> {

    public CourseSerializer() {
        this(null);
    }

    protected CourseSerializer(Class<CourseDto> t) {
        super(t);
    }

    @Override
    public void serialize(CourseDto value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        {
            gen.writeNumberField("id", value.getId());
            gen.writeStringField("name", value.getName());
            gen.writeNumberField("number", value.getNumber());
            gen.writeNumberField("price", value.getPrice());

            if (value.getProfessor() != null) {
                gen.writeObjectFieldStart("professor");
                {
                    gen.writeNumberField("id", value.getProfessor().getId());
                    gen.writeStringField("fullName", value.getProfessor().getFullName());
                }
                gen.writeEndObject();
            }
            gen.writeArrayFieldStart("students");
            {
                for (StudentDto student : value.getStudents()) {
                    gen.writeStartObject();
                    {
                        gen.writeNumberField("id", student.getId());
                        gen.writeStringField("fullName", student.getFullName());
                    }
                    gen.writeEndObject();
                }
            }
            gen.writeEndArray();
        }
        gen.writeEndObject();
    }

}