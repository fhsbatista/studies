package br.com.alura.screenmatch.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataMapper {
    public static <T> T map(String json, Class<T> klass) {
        var mapper = new ObjectMapper();

        try {
            return mapper.readValue(json, klass);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}

