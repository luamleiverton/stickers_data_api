import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsonParser {

  //estabelece os padrões do texto para encontrar chave e valor
  //valores a extrair dentro dos colchetes da String do body[]
  private static final Pattern REGEX_ITEMS = Pattern.compile(".*\\[(.+)\\].*");
  //expressão para o padrão de separador dos atributos em cada item
  private static final Pattern REGEX_ATRIBUTOS_JSON = Pattern.compile("\"(.+?)\":\"(.*?)\"");

  //início do método de parseamento que vai receber um json
  public List<Map<String,String>> parse(String json) {
    //percorre o json atrás dos valores descritos no padrão
    Matcher matcher = REGEX_ITEMS.matcher(json);
    //se o padrão não for encontrado lança a exceção
    if (!matcher.find()) {
      throw new IllegalArgumentException("Não encontrou items.");
    }

    //divide o array separando os itens
    String[] items = matcher.group(1).split("\\},\\{");

    //cria um dicionário para armazenar os valores
    List<Map<String, String>> dados = new ArrayList<>();

    //percorre o array de items e armazena cada atributo, sua chave e valor que foi separado por grupos
    for (String item : items) {
      Map<String, String> atributosItem = new HashMap<>();

      Matcher matcherAtributosJson = REGEX_ATRIBUTOS_JSON.matcher(item);

      while (matcherAtributosJson.find()) {
        String atributo = matcherAtributosJson.group(1);
        String valor = matcherAtributosJson.group(2);
        atributosItem.put(atributo, valor);
      }

      dados.add(atributosItem);

    }

    return dados;

  }
}
