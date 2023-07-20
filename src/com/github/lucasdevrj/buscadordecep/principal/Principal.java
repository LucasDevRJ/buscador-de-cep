package com.github.lucasdevrj.buscadordecep.principal;

import com.github.lucasdevrj.buscadordecep.CriaConexaoHttp;
import com.github.lucasdevrj.buscadordecep.GravacaoArquivo;
import com.github.lucasdevrj.buscadordecep.Serializacao;

import java.io.IOException;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner entrada = new Scanner(System.in);

        System.out.print("Digite o CEP desejado ou 0 para sair: ");
        String cepDigitado = entrada.next();

        CriaConexaoHttp conexaoHttp = new CriaConexaoHttp();
        String json = conexaoHttp.cria(cepDigitado);

        Serializacao serializacao = new Serializacao();
        serializacao.serializar(json);
    }
}
