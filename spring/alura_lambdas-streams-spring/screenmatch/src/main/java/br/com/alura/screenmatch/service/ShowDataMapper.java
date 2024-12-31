package br.com.alura.screenmatch.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ShowDataMapper implements DataMapper {
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T map(String json, Class<T> klass) {
        try {
            return mapper.readValue(json, klass);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}

