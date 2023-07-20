package com.github.lucasdevrj.buscadordecep.modelos;

public record ViaCep(String cep, String logradouro, String complemento,
                     String bairro, String localidade, String uf, String ddd) {
}
