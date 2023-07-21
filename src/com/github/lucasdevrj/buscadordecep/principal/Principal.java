package com.github.lucasdevrj.buscadordecep.principal;

import com.github.lucasdevrj.buscadordecep.conexoes.ConexaoHttp;
import com.github.lucasdevrj.buscadordecep.aquivos.Arquivo;
import com.github.lucasdevrj.buscadordecep.dependencias.ImportaGson;
import com.github.lucasdevrj.buscadordecep.modelos.Endereco;
import com.github.lucasdevrj.buscadordecep.modelos.EnderecoViaCepApi;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
    private static List<Endereco> enderecos = new ArrayList<>();
    public static void main(String[] args) throws IOException, InterruptedException {
        Gson gson = ImportaGson.importar();

        Scanner entrada = new Scanner(System.in);

        System.out.println("1 - Acessar CEP");
        System.out.println("Qualquer Tecla - Sair");
        System.out.print("Digite a opção desejada: ");
        String opcao = entrada.next();

        while (opcao.equals("1")) {
            try {
                System.out.print("Digite o CEP desejado: ");
                String cepDigitado = entrada.next();

                String urlViaCep = "https://viacep.com.br/ws/" + cepDigitado + "/json/";

                ConexaoHttp conexaoHttp = new ConexaoHttp();
                String json = conexaoHttp.cria(urlViaCep);

                EnderecoViaCepApi viaCep = gson.fromJson(json, EnderecoViaCepApi.class);

                Endereco cep = new Endereco(viaCep);
                enderecos.add(cep);

                Arquivo arquivo = new Arquivo();
                arquivo.gravar(enderecos);
                arquivo.ler();
            } catch (IllegalStateException e) {
                System.err.println("Erro: operação inválida!");
            } catch (JsonSyntaxException e) {
                System.err.println("Erro: JSON com formato inválido!");
            } catch (IllegalArgumentException e) {
                System.err.println("Erro: caracter inválido!");
            }

            System.out.println("1 - Acessar CEP");
            System.out.println("Qualquer Tecla - Sair");
            System.out.print("Digite a opção desejada: ");
            opcao = entrada.next();
        }

        System.out.println("Programa finalizado.");
    }
}
