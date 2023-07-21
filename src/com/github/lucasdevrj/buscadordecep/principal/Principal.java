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
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Principal {
    public static List<Endereco> enderecos = new ArrayList<>();
    public static Scanner entrada = new Scanner(System.in);

    public static Arquivo arquivo = new Arquivo();
    public static Gson gson = ImportaGson.importar();
    public static void main(String[] args) throws IOException, InterruptedException {
        exibeMenu();
    }

    public static void exibeMenu() {
       try {
           String menu = """
                   1 - Acessar CEP
                   2 - Listar CEP's
                   Qualquer Tecla - Sair
                   """;
           System.out.println(menu);
           System.out.print("Digite a opção desejada: ");
           int opcao = Integer.parseInt(entrada.next());

           switch (opcao) {
               case 1:
                   acessarCep();
                   break;

               case 2:
                   listarCeps();
                   break;
           }
       } catch (InputMismatchException | NumberFormatException e) {
           System.out.println("Programa finalizado.");
       }
    }

    public static void acessarCep() {
        try {
            System.out.print("Digite o CEP desejado: ");
            String cepDigitado = entrada.next();

            String urlViaCep = "https://viacep.com.br/ws/" + cepDigitado + "/json/";

            ConexaoHttp conexaoHttp = new ConexaoHttp();
            String json = conexaoHttp.cria(urlViaCep);

            EnderecoViaCepApi viaCep = gson.fromJson(json, EnderecoViaCepApi.class);

            Endereco endereco = new Endereco(viaCep);
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

        System.out.println("1 - Acessar CEP");
        System.out.println("2 - Listar CEP's");
        System.out.println("Qualquer Tecla - Sair");
        System.out.print("Digite a opção desejada: ");
        int opcao = Integer.parseInt(entrada.next());
    }

    public static void listarCeps() {
        arquivo.ler();
    }
}
