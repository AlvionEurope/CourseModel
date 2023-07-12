package me.rudnikov.backend.dto.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import me.rudnikov.backend.dto.read.CourseDto;
import me.rudnikov.backend.dto.read.ProfessorDto;

import java.io.IOException;

public class ProfessorSerializer extends StdSerializer<ProfessorDto> {

    public ProfessorSerializer() {
        this(null);
    }

    public ProfessorSerializer(Class<ProfessorDto> t) {
        super(t);
    }

    @Override
    public void serialize(ProfessorDto value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        {
            gen.writeNumberField("id", value.getId());
            gen.writeStringField("fullName", value.getFullName());
            gen.writeStringField("address", value.getAddress());
            gen.writeStringField("fullName", value.getPhoneNumber());
            gen.writeNumberField("payment", value.getPayment());
            gen.writeArrayFieldStart("courses");
            {
                for (CourseDto course : value.getCourses()) {
                    gen.writeStartObject();
                    {
                        gen.writeNumberField("id", course.getId());
                        gen.writeStringField("name", course.getName());
                    }
                    gen.writeEndObject();
                }
            }
            gen.writeEndArray();
        }
        gen.writeEndObject();
    }

}