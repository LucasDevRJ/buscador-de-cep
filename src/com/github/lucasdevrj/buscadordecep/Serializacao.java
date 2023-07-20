package com.github.lucasdevrj.buscadordecep;

import com.github.lucasdevrj.buscadordecep.modelos.Cep;
import com.github.lucasdevrj.buscadordecep.modelos.ViaCep;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Serializacao {
    Gson gson = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
            .setPrettyPrinting()
            .create();
    public void serializar(String json) {
        ViaCep viaCep = gson.fromJson(json, ViaCep.class);
        Cep cep = new Cep(viaCep);
        System.out.println(cep);
    }
}
