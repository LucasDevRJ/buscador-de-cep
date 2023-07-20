package com.github.lucasdevrj.buscadordecep;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CriaConexaoHttp {
    public String cria(String cepDigitado) throws IOException, InterruptedException {
        String urlViaCep = "https://viacep.com.br/ws/" + cepDigitado + "/json/";

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(urlViaCep))
                .build();

        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();

        return json;
    }
}
