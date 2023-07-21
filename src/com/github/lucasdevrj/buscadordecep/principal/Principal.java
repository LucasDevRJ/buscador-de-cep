package com.github.lucasdevrj.buscadordecep.principal;

import com.github.lucasdevrj.buscadordecep.modelos.Endereco;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) throws IOException, InterruptedException {
        exibeMenu();
    }
    public static void exibeMenu() {
       try {
           Scanner entrada = new Scanner(System.in);
           String menu = """
                   1 - Acessar CEP
                   2 - Listar CEP's
                   Qualquer Tecla - Sair
                   """;
           System.out.println(menu);
           System.out.print("Digite a opção desejada: ");
           int opcao = entrada.nextInt();
           switch (opcao) {
                    case 1:
                        Endereco.acessarEndereco();
                    break;

                    case 2:
                        Endereco.listarEnderecos();
                    break;
           }
       } catch (InputMismatchException | NumberFormatException e) {
           System.out.println("Programa finalizado.");
       }
    }
}
