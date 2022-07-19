//import do pacote java.net para manipular as requisições para a APO
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        
        //Conexão e Requisição de dados à API
        //Leitura de token do arquivo
        readFile file = new readFile();
        String token = file.obterToken();
        //armazena o endereço a ser acessado contendo o token de acesso a API
        String url = "https://imdb-api.com/en/API/Top250Movies/"+token;
        //converte a string em um objeto URI 
        URI uri = URI.create(url);
        //instanciar um objeto HttpClient e armazenar na variável client
        var client = HttpClient.newHttpClient();
        //faz um build da variável uri e passa para requisição solicitando via GET
        var request = HttpRequest.newBuilder(uri).GET().build();
        //Define o formato da resposta e armazena numa variável de resposta da requisição enviada pelo cliente
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        //armazena o corpo da resposta numa string
        String body = response.body();
        //impressão no terminal

        //Extração de dados - parser

        //instancia a classe de parseamento
        var parser = new JsonParser();
        
        //criando a estrutura para armazenar a lista vindo do body
        List<Map<String, String>> listaDeFilmes = parser.parse(body);
        
        //Exibição e manipulação dos dados

        //tamanho da lista de filmes
        System.out.println(listaDeFilmes.size());
        //primeiro item da lista de filmes
        System.out.println(listaDeFilmes.get(0));

        //percorre a lista retornando os valores de cada atributo por item
        for (Map<String,String> filme : listaDeFilmes) {
            System.out.println(filme.get("title"));
            System.out.println(filme.get("image"));
            System.out.println(filme.get("imDbRating"));
            System.out.println();
        }
    }
}
