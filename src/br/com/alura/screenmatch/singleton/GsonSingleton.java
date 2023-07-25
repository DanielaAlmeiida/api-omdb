package br.com.alura.screenmatch.singleton;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonSingleton {

    private static Gson instance;

    private GsonSingleton() {

    }

    public static Gson getInstance() {

        Gson instance = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setPrettyPrinting()
                .create();

        return instance;
    }
}
