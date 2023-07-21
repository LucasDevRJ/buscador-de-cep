package com.github.lucasdevrj.buscadordecep.aquivos;

import com.github.lucasdevrj.buscadordecep.dependencias.ImportaGson;
import com.github.lucasdevrj.buscadordecep.modelos.Endereco;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Arquivo {
    Gson gson = ImportaGson.importar();
    public FileWriter gravar(List<Endereco> enderecos) {
        FileWriter escrita = null;
        try {
            escrita = new FileWriter("");
            escrita.write(gson.toJson(enderecos));
            escrita.close();
        } catch (FileNotFoundException e) {
            System.err.println("Erro: não foi possivel criar o arquivo!");
        } catch (IOException e) {
            System.err.println("Erro: não foi possivel gravar no arquivo!");
        }
        return escrita;
    }

    public void ler() {
        try {
            File arquivo = new File("enderecos.json");
            Scanner scanner = new Scanner(arquivo);

            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine();
                System.out.println(linha);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("Erro: arquivo não encontrado!");
        }
    }
}
