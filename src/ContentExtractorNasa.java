import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ContentExtractorNasa implements ContentExtractor{

  public List<Content> extract(String json) {

    var parser = new JsonParser();
    List<Map<String, String>> listadeAtributos = parser.parse(json);

    List<Content> conteudos = new ArrayList<>();

    for (Map<String, String> atributos : listadeAtributos) {
      String titulo = atributos.get("title");
      String urlImagem = atributos.get("url");
      Content conteudo = new Content(titulo, urlImagem);

      conteudos.add(conteudo);
    }
    return conteudos;
  }
}
