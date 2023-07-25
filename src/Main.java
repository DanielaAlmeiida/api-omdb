import br.com.alura.screenmatch.modelos.Titulo;
import br.com.alura.screenmatch.services.JsonWriter;
import br.com.alura.screenmatch.services.MovieSearcher;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner read = new Scanner(System.in);
        String search = "";
        List<Titulo> titulos = new ArrayList<>();

        MovieSearcher movieSearcher = new MovieSearcher();
        JsonWriter jsonWriter = new JsonWriter();

        while (!search.equalsIgnoreCase("exit")) {
            System.out.println("Enter a movie to search: ");
            search = read.nextLine();

            if(search.equalsIgnoreCase("exit")) {
                break;
            }

            Titulo titulo = movieSearcher.SearchMovie(search);
            titulos.add(titulo);
        }

        jsonWriter.convert(titulos);

    }
}

