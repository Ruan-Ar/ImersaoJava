import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class GeradoraStickers {
    
    public void cria(InputStream inputStream, String nomeArquivo) throws Exception{

        //leitura da imagem
        /*
        InputStream inputStream = new FileInputStream(new File("imagens/MostPopularTVs_1.jpg")); 
        InputStream inputStream = new URL("https://imersao-java-apis.s3.amazonaws.com/MostPopularTVs_2.jpg").openStream();
        */

         BufferedImage imagemOriginal = ImageIO.read(inputStream);

        //cria nova imagem em memoria com transparencia e com tamanho novo
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getWidth();
        int novaAltura = altura + 500;
        BufferedImage novaImagem = new BufferedImage(largura, novaAltura,BufferedImage.TRANSLUCENT);

        //copiar a imagem original para nova imagem (em memoria)
        Graphics2D graphics = (Graphics2D)novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal, 0, 0, null);

        //confuigura a fonte
        var fonte = new Font(Font.SANS_SERIF, Font.BOLD, 64);
        graphics.setColor(Color.YELLOW);
        graphics.setFont(fonte);

        //escrever frase na nova imagem
        graphics.drawString("MELHOR QUE TEM", 100, novaAltura - 100);
        

        //escrever a nova imagem em um arquivo
        ImageIO.write(novaImagem, "png", new File(nomeArquivo));


    }

    
}
