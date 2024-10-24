import java.io.IOException;
import java.util.Scanner;

public class Conversor {
  public static void main(String[] args) throws IOException {
    Scanner sc = new Scanner(System.in);
    HttpClient client;
    String moedaBase, moedaAlvo;
    double valor=0;
    int result=1;

    System.out.println("=".repeat(47));
    System.out.println("\tSeja bem-vindo/a ao Conversor de Moedas");

    while (result != 0) {
      System.out.print(Conversor.menuPrincipal());
      result = recebeMoedasEValor(sc);
    }
  }
  public static String menuPrincipal() {
    return """
        ===============================================
        
        \t[1]  Dólar          [6]  Peso argentino
        \t[2]  Real           [7]  Peso colombiano
        \t[3]  Euro           [8]  Libra esterlina
        \t[4]  Iene           [9]  Dólar canadense
        \t[5]  Yuan           [10] Dólar australiano

        \t[0] Sair
        -----------------------------------------------
        """;
  }

  public static int recebeMoedasEValor(Scanner sc) throws IOException {

    String moedaBase = "";
    do {
      System.out.println("Digite o número correspondente as moedas ou 0 para sair:");
      System.out.print("Moeda base ==> ");
      moedaBase = sc.nextLine();
    } while (Integer.parseInt(moedaBase) < 0 || Integer.parseInt(moedaBase) > 10);

    if (moedaBase.equals("0")) return 0;

    String moedaAlvo = "";
    do {
      System.out.print("Moeda alvo ==> ");
      moedaAlvo = sc.nextLine();
    } while (Integer.parseInt(moedaAlvo) < 1 || Integer.parseInt(moedaAlvo) > 10);

    double valor = 0;
    do {
      System.out.print("Digite o valor à converter: ");
      valor = sc.nextDouble();
      System.out.println();

      if (valor <= 0) System.out.println("O valor a ser convertido deve ser maior que zero.");
    } while (valor < 1);
    sc.nextLine();

    HttpClient client = new HttpClient();
    client.conversor(decodificaMoeda(moedaBase), decodificaMoeda(moedaAlvo), valor);
    return 1;
  }

  public static String decodificaMoeda(String moeda) {
    switch (moeda) {
      case "1" -> moeda = "USD";
      case "2" -> moeda = "BRL";
      case "3" -> moeda = "EUR";
      case "4" -> moeda = "JPY";
      case "5" -> moeda = "CNH";
      case "6" -> moeda = "ARS";
      case "7" -> moeda = "COP";
      case "8" -> moeda = "GBP";
      case "9" -> moeda = "CAD";
      case "10" -> moeda = "AUD";
    }
    return moeda;
  }
}
