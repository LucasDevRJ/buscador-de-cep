package com.github.lucasdevrj.buscadordecep.modelos;

public record EnderecoViaCepApi(String cep, String logradouro, String complemento,
                                String bairro, String localidade, String uf, String ibge,
                                String gia, String ddd, String siafi) {
}
