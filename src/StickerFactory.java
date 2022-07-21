import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.awt.Graphics2D;
import javax.imageio.ImageIO;
import java.net.URL;
import java.awt.Color;
import java.awt.Font;

public class StickerFactory {

  public void generate(InputStream inputStream, String nomeArquivo) throws Exception {

    //leitura da imagem
    
    //de imagem
    //InputStream inputStream = new FileInputStream(new File("input_pictures/filme.jpg"));
    
    //de uma URL
    //InputStream inputStream = new URL(spec:"https://url");
    BufferedImage imagemOriginal =  ImageIO.read(inputStream);

    //cria nova imagem e carrega em memoria e editar transparencia(alfa)
    //a largura da nova imagem vai ser igual a da original e a altura vai ser reajustada em 200 px
    int largura = imagemOriginal.getWidth();
    int altura = imagemOriginal.getHeight();
    int alturaRedefinida = altura + 200;

    //cria o objeto com base numa BufferedImage e aplica uma propriedade de transparência
    BufferedImage novaImagem = new BufferedImage(largura, alturaRedefinida, BufferedImage.TRANSLUCENT);

    //copiar imagem original para nova imagem
    Graphics2D graphics = (Graphics2D)novaImagem.getGraphics();
    graphics.drawImage(imagemOriginal, 0, 0, null);

    //configurar fonte do texto 
    var fonte = new Font(Font.SANS_SERIF, Font.BOLD, 50);
    graphics.setFont(fonte);
    graphics.setColor(Color.WHITE);

    //definir rotulo - frase nas coordenadas passadas como parâmetros
    graphics.drawString("Top", largura/2, alturaRedefinida - 100);

    //gerar arquivo
    ImageIO.write(novaImagem, "png", new File("output/"+nomeArquivo));

  }  
}
