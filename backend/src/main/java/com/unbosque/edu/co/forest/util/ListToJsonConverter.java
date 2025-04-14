package com.unbosque.edu.co.forest.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Converter
public class ListToJsonConverter implements AttributeConverter<List<String>, String> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(List<String> list) {
        try {
            return objectMapper.writeValueAsString(list);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting list to JSON", e);
        }
    }

    @Override
    public List<String> convertToEntityAttribute(String json) {
        try {
            if (json == null || json.trim().isEmpty()) {
                return Collections.emptyList(); // <-- Aquí está el fix
            }
            return objectMapper.readValue(json, List.class);
        } catch (IOException e) {
            throw new RuntimeException("Error converting JSON to list", e);
        }
    }
}
