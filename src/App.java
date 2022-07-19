import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;



public class App extends Config{
    public static void main(String[] args) throws Exception {
          
        //- fazer conexão HTTP e buscar os top 250 filmes do IMDb - Via HTTP RESPONSE
        String url = "https://api.mocki.io/v2/549a5d8b/MostPopularTVs";
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
        var geradora = new GeradoraStickers();
        for (Map<String,String> filme : listaDeFilmes) {
            String urlImagem = filme.get("image");
            String titulo = filme.get("title");

            InputStream inputStream = new URL(urlImagem).openStream();
            String nomeArquivo = titulo + ".png";

            
            geradora.cria(inputStream, nomeArquivo);

            System.out.println("\u001b[37m \u001b[44m"+(filme.get("title")) +"\u001b[m"); 
            System.out.println();
            
        }
        


        
    }
}
