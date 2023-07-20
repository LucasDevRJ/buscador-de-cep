package com.github.lucasdevrj.buscadordecep.principal;

import com.github.lucasdevrj.buscadordecep.CriaConexaoHttp;

import java.io.IOException;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner entrada = new Scanner(System.in);

        System.out.print("Digite o CEP desejado ou 0 para sair: ");
        String cepDigitado = entrada.next();

        CriaConexaoHttp conexaoHttp = new CriaConexaoHttp();
        conexaoHttp.cria(cepDigitado);
    }
}
