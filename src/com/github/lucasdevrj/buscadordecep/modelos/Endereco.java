package com.github.lucasdevrj.buscadordecep.modelos;

import com.github.lucasdevrj.buscadordecep.aquivos.Arquivo;
import com.github.lucasdevrj.buscadordecep.conexoes.ConexaoHttp;
import com.github.lucasdevrj.buscadordecep.dependencias.ImportaGson;
import com.github.lucasdevrj.buscadordecep.principal.Principal;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
    private static List<Endereco> enderecos = new ArrayList<>();

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

    public Endereco() {

    }

    static Scanner entrada = new Scanner(System.in);
    static Arquivo arquivo = new Arquivo();
    static Gson gson = ImportaGson.criar();

    public static void acessarEndereco() {
        try {
            System.out.print("Digite o CEP desejado: ");
            String cepDigitado = entrada.next();

            String url = "https://viacep.com.br/ws/" + cepDigitado + "/json/";

            ConexaoHttp conexaoHttp = new ConexaoHttp();
            String json = conexaoHttp.cria(url);

            EnderecoViaCepApi urlViaCep = gson.fromJson(json, EnderecoViaCepApi.class);

            Endereco endereco = new Endereco(urlViaCep);
            System.out.println();
            System.out.println(endereco);
            enderecos.add(endereco);
            arquivo.gravar(enderecos);

        } catch (IllegalStateException e) {
            System.err.println("Erro: operação inválida!");
        } catch (JsonSyntaxException e) {
            System.err.println("Erro: JSON com formato inválido!");
        } catch (IllegalArgumentException e) {
            System.err.println("Erro: caracter inválido!");
        }
        Principal.exibeMenu();
    }

    public static void listarEnderecos() {
        arquivo.ler();
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
