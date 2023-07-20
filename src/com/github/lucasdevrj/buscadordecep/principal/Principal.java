package com.github.lucasdevrj.buscadordecep.principal;

import com.github.lucasdevrj.buscadordecep.modelos.Cep;
import com.github.lucasdevrj.buscadordecep.modelos.ViaCep;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(urlViaCep))
                .build();

        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        System.out.println(json);

        ViaCep viaCep = gson.fromJson(json, ViaCep.class);

        Cep cep = new Cep(viaCep);
        System.out.println(cep);
    }
}
