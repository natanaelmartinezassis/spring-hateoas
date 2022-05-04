package com.example.springhateoas.commons;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Component
public class ReadFile<T> {

    public List<T> fromJson(File jsonFile, Class<T> clazz) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        TypeFactory typeFactory = mapper.getTypeFactory();
        return mapper.readValue(jsonFile, typeFactory.constructCollectionType(List.class, clazz));
    }

}
