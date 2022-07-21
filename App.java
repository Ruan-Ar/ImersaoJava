import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class App extends Config{
    public static void main(String[] args) throws Exception {

        String url = "https://api.mocki.io/v2/549a5d8b/MostPopularTVs";
        ExtratorDeConteudo extrator = new ExtratorDeConteudoImdb();

        //String url = "https://api.mocki.io/v2/549a5d8b/NASA-APOD-JamesWebbSpaceTelescope";
        //ExtratorDeConteudo extrator = new ExtratorDeConteudoNasa();

          var http = new ClienteHttp();
          String json = http.buscaDados(url);

        //extrair só os dados que interessam(titulo, poster, classificação)

        List<Conteudo> conteudos = extrator.extraiConteudos(json);

        var geradora = new GeradoraStickers();

        for (int i = 0; i < 3; i++){
       Conteudo conteudo = conteudos.get(i);


            InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();
            String nomeArquivo = "saida/" + conteudo.getTitulo() + ".png";

            geradora.cria(inputStream, nomeArquivo);

            System.out.println("\u001b[37m \u001b[44m"+(conteudo.getTitulo()) +"\u001b[m");
            System.out.println();
            
        }

        


        
    }
}
