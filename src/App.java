import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;



public class App extends Config{
    public static void main(String[] args) throws Exception {
          
        //- fazer conexão HTTP e buscar os top 250 filmes do IMDb - Via HTTP RESPONSE
        String url = "https://imdb-api.com/en/API/MostPopularTVs/" + getApiKey();
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        var response = client.send(request, BodyHandlers.ofString());
        String body = response.body();
        System.out.println(body);

        //extrair só os dados que interessam(titulo, poster, classificação)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);

        //exibir e manipular os dados
        for (Map<String,String> filme : listaDeFilmes) {
            System.out.println("\u001b[37m \u001b[44m"+(filme.get("title")) +"\u001b[m"); 
            System.out.println(filme.get("image")); 
            System.out.println("Avaliacao da Serie eh: " + filme.get("imDbRating")); 
            
        }
        


        
    }
}
