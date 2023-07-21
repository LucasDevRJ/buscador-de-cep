package com.github.lucasdevrj.buscadordecep.modelos;

public class Endereco {
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

    public Endereco(EnderecoViaCepApi viaCep) {
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
        return "Endereço: {" +
                "\n\tCEP: " + this.cep +
                "\n\tLogradouro: " + this.logradouro +
                "\n\tComplemento: " + this.complemento +
                "\n\tBairro: " + this.bairro +
                "\n\tLocalidade: " + this.localidade +
                "\n\tUF: " + this.uf +
                "\n\tIBGE: " + this.ibge +
                "\n\tGIA: " + this.gia +
                "\n\tDDD: " + this.ddd +
                "\n\tSIAF: " + this.siafi;
    }
}
