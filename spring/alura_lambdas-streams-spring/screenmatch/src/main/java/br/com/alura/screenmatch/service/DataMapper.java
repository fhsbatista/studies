package br.com.alura.screenmatch.service;

public interface DataMapper {
    <T> T map(String json, Class<T> klass);
}