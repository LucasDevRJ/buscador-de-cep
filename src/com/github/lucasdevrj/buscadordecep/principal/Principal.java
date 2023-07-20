package com.github.lucasdevrj.buscadordecep.principal;

import com.github.lucasdevrj.buscadordecep.conexoes.ConexaoHttp;
import com.github.lucasdevrj.buscadordecep.aquivos.Arquivo;
import com.github.lucasdevrj.buscadordecep.dependencias.ImportaGson;
import com.github.lucasdevrj.buscadordecep.modelos.Cep;
import com.github.lucasdevrj.buscadordecep.modelos.ViaCep;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Principal {
    private static List<Cep> ceps = new ArrayList<>();
    public static void main(String[] args) throws IOException, InterruptedException {
        Gson gson = ImportaGson.importar();

        Scanner entrada = new Scanner(System.in);

        System.out.println("1 - Acessar CEP");
        System.out.println("Qualquer Tecla - Sair");
        System.out.print("Digite a opção desejada: ");
        int opcao = 0;
        try {
            opcao = entrada.nextInt();
        } catch (InputMismatchException e) {
            e.getMessage();
        }

        while (opcao == 1) {
            try {
                System.out.print("Digite o CEP desejado: ");
                String cepDigitado = entrada.next();

                String urlViaCep = "https://viacep.com.br/ws/" + cepDigitado + "/json/";

                ConexaoHttp conexaoHttp = new ConexaoHttp();
                String json = conexaoHttp.cria(urlViaCep);

                ViaCep viaCep = gson.fromJson(json, ViaCep.class);

                Cep cep = new Cep(viaCep);
                ceps.add(cep);

                Arquivo arquivo = new Arquivo();
                arquivo.gravar(ceps);
                arquivo.ler();
            } catch (IllegalStateException e) {
                System.err.println("Erro: operação inválida");
            } catch (JsonSyntaxException e) {
                System.err.println("Erro: JSON com formato inválido!");
            } catch (IllegalArgumentException e) {
                System.err.println("Erro: caracter inválido");
            }

            System.out.println("1 - Acessar CEP");
            System.out.println("Qualquer Tecla - Sair");
            System.out.print("Digite a opção desejada: ");
            opcao = entrada.nextInt();
        }

        System.out.println("Programa finalizado.");
    }
}
