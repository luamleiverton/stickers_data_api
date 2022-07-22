import java.util.List;
import java.io.InputStream;
import java.net.URL;

public class RefactoredApp {
  public static void main(String[] args) throws Exception {

    //String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java/api/TopMovies.json";
    //ContentExtractor extrator = new ContentExtractorIMDB();
      
    String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java/api/NASA-APOD.json";
    ContentExtractor extrator = new ContentExtractorNasa();
    
    var http = new ClientHttp();
    String json = http.buscaDados(url);

    List<Content> conteudos = extrator.extract(json);

    StickerFactory factory = new StickerFactory();
    
    for (int i=0; i<3; i++) {
      Content conteudo = conteudos.get(i);
      InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();
      String nomeArquivo = "saida_"+conteudo.getTitulo();
      
      //gerando o sticker a partir da classe factory passando a url da imagem e o nome do arquivo como parâmetros
      factory.generate(inputStream, nomeArquivo);    
      
      //acompanhando no terminal
      System.out.println(conteudo.getTitulo());
      System.out.println();
    }
  }
}
