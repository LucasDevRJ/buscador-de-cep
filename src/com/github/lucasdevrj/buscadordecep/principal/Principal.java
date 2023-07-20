package com.github.lucasdevrj.buscadordecep.principal;

import com.github.lucasdevrj.buscadordecep.ConexaoHttp;
import com.github.lucasdevrj.buscadordecep.modelos.Cep;
import com.github.lucasdevrj.buscadordecep.modelos.ViaCep;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) throws IOException, InterruptedException {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        Scanner entrada = new Scanner(System.in);

        System.out.print("Digite o CEP desejado ou 0 para sair: ");
        String cepDigitado = entrada.next();

        String urlViaCep = "https://viacep.com.br/ws/" + cepDigitado + "/json/";

        ConexaoHttp conexaoHttp = new ConexaoHttp();
        String json = conexaoHttp.cria(urlViaCep);

        ViaCep viaCep = gson.fromJson(json, ViaCep.class);

        Cep cep = new Cep(viaCep);

        FileWriter escrita = new FileWriter("cep.json");
        escrita.write(gson.toJson(cep));
        escrita.close();

        File arquivo = new File("cep.json");
        Scanner scanner = new Scanner(arquivo);

        while (scanner.hasNextLine()) {
            String linha = scanner.nextLine();
            System.out.println(linha);
        }

        scanner.close();
    }
}
