package com.example.demo.converter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.List;

@Converter
public class ListToIntegerConverter implements AttributeConverter<List<Integer>, String> {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(List<Integer> list) {
        try {
            return mapper.writeValueAsString(list);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Integer> convertToEntityAttribute(String json) {
        try {
            return mapper.readValue(json, new TypeReference<List<Integer>>() {});
        } catch (Exception e) {
            return List.of();
        }
    }
}
