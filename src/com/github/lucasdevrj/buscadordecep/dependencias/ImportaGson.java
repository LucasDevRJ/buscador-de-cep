package com.github.lucasdevrj.buscadordecep.dependencias;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ImportaGson {
    public static Gson criar() {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        return gson;
    }
}
