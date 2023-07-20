package com.github.lucasdevrj.buscadordecep.modelos;

public class Cep {
    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;
    private String ibge;
    private String gia;
    private String ddd;
    private String siafi;

    public Cep(ViaCep viaCep) {
        this.cep = viaCep.cep();
        this.logradouro = viaCep.logradouro();
        this.complemento = viaCep.complemento();
        this.bairro = viaCep.bairro();
        this.localidade = viaCep.localidade();
        this.uf = viaCep.uf();
        this.ibge = viaCep.ibge();
        this.gia = viaCep.gia();
        this.ddd = viaCep.ddd();
        this.siafi = viaCep.siafi();
    }

    @Override
    public String toString() {
        return "Cep{" +
                "cep='" + cep + '\'' +
                ", logradouro='" + logradouro + '\'' +
                ", complemento='" + complemento + '\'' +
                ", bairro='" + bairro + '\'' +
                ", localidade='" + localidade + '\'' +
                ", uf='" + uf + '\'' +
                ", ibge='" + ibge + '\'' +
                ", gia='" + gia + '\'' +
                ", ddd='" + ddd + '\'' +
                ", siafi='" + siafi + '\'' +
                '}';
    }
}
