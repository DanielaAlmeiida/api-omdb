package br.com.alura.screenmatch.services;

import br.com.alura.screenmatch.modelos.Titulo;
import br.com.alura.screenmatch.singleton.GsonSingleton;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class JsonWriter {

    public void convert(List<Titulo> titulos) throws IOException {

        FileWriter writer = new FileWriter("movies.json");

        Gson gson = GsonSingleton.getInstance();

        writer.write(gson.toJson(titulos));
        writer.close();
    }
}
