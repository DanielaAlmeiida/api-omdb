package br.com.alura.screenmatch.services;

import br.com.alura.screenmatch.exception.YearConversionErrorException;
import br.com.alura.screenmatch.modelos.Titulo;
import br.com.alura.screenmatch.modelos.TituloOmdb;
import br.com.alura.screenmatch.singleton.GsonSingleton;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class MovieSearcher {

    public Titulo SearchMovie(String search) {

        String address = "http://www.omdbapi.com/?t=" + search.replace(" ", "+") + "&apikey=7a4a0d89";

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(address))
                    .build();
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            String json = response.body();

            Gson gson = GsonSingleton.getInstance();
            TituloOmdb meuTituloOmdb = gson.fromJson(json, TituloOmdb.class);
            Titulo meuTitulo = new Titulo(meuTituloOmdb);

            return meuTitulo;

        } catch (NumberFormatException e) {
            System.out.println("Aconteceu um erro: ");
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Aconteceu um erro:" + e.getMessage());
        } catch (YearConversionErrorException e) {
            System.out.println("Aconteceu um erro:" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Aconteceu um erro não específico: :" + e.getMessage());
        }

        return null;
    }
}
