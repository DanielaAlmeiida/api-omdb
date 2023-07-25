import br.com.alura.screenmatch.exception.YearConversionErrorException;
import br.com.alura.screenmatch.modelos.Titulo;
import br.com.alura.screenmatch.modelos.TituloOmdb;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner read = new Scanner(System.in);
        String search = "";
        List<Titulo> titulos = new ArrayList<>();

        Gson gson = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
            .setPrettyPrinting()
            .create();

        while (!search.equalsIgnoreCase("exit")) {
                System.out.println("Digite um filme para busca: ");
                search = read.nextLine();

                if(search.equalsIgnoreCase("exit")) {
                    break;
                }

                String address = "http://www.omdbapi.com/?t=" + search.replace(" ", "+") + "&apikey=7a4a0d89";


                try {
                    HttpClient client = HttpClient.newHttpClient();
                    HttpRequest request = HttpRequest.newBuilder()
                            .uri(URI.create(address))
                            .build();

                    HttpResponse<String> response = client
                            .send(request, HttpResponse.BodyHandlers.ofString());

                    String json = response.body();
                    System.out.println(json);


                    TituloOmdb meuTituloOmdb = gson.fromJson(json, TituloOmdb.class);


                    System.out.println(meuTituloOmdb);


                    Titulo meuTitulo = new Titulo(meuTituloOmdb);
                    System.out.println("Titulo já convertido");
                    System.out.println(meuTitulo);

                   titulos.add(meuTitulo);
                } catch (NumberFormatException e) {
                    System.out.println("Aconteceu um erro: ");
                    System.out.println(e.getMessage());
                } catch (IllegalArgumentException e) {
                    System.out.println("Aconteceu um erro: :" + e.getMessage());

                } catch (YearConversionErrorException e) {
                    System.out.println("Aconteceu um erro: :" + e.getMessage());
                } catch (Exception e) {
                    System.out.println("Aconteceu um erro: :" + e.getMessage());
                }
        }

        System.out.println("Titles List --> " + titulos);

        FileWriter writer = new FileWriter("movies.json");
        writer.write(gson.toJson(titulos));
        writer.close();

        System.out.println("O programa finalizou corretamente!");
    }
}

