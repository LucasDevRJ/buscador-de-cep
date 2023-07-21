package com.github.lucasdevrj.buscadordecep.conexoes;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConexaoHttp {
    public String cria(String url) {
        HttpClient cliente = null;
        String json = "";
        try {
            cliente = HttpClient.newHttpClient();

            HttpRequest requisicao = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

            HttpResponse<String> resposta = cliente
                    .send(requisicao, HttpResponse.BodyHandlers.ofString());
            json = resposta.body();
        } catch (IOException e) {
            System.err.println("Erro: não foi possível criar conexão HTTP!");
        } catch (InterruptedException e) {
            System.err.println("Erro: conexão interrompida!");
        }
        return json;
    }
}
