import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class HttpClient {
  private String apiKey = "6ddd700efbfed74cbbf1296c";

  public void conversor(String moedaBase, String moedaAlvo, double valor) throws IOException {
    String key = apiKey;
    String link = "https://v6.exchangerate-api.com/v6/" + key + "/pair/" + moedaBase + "/" + moedaAlvo + "/" + valor;

    URL url = new URL(link);
    //responsavel pela requisição no endpoint no servidor
    HttpURLConnection request = (HttpURLConnection) url.openConnection();
    request.connect();//send do postman

    //json vai trabalhar com limpeza do corpo/dados que vem da api
    JsonParser jp = new JsonParser();

    //CONVERSOR
    //JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
    JsonElement root = JsonParser.parseReader(new InputStreamReader((InputStream) request.getContent()));
    JsonObject jsonObj = root.getAsJsonObject();
    //DEPOIS COLOCAR EM UM TRY CATCH
    String requestResult = jsonObj.get("conversion_result").getAsString();

    String result = "O valor convertido de " +valor+ " " +moedaBase+ " para " +moedaAlvo+ " é: " + requestResult;
    System.out.println(result);
  }
}
