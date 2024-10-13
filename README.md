<h1> HOLA MUNDO</h1>
![Maradona](https://github.com/user-attachments/assets/cb2b15dd-6983-449b-a228-89cc73ad363a)

## Clase Principal.

	import java.util.Scanner;
	    public class Principal {
	       public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);
	        boolean continuar = true;
	
	        while (continuar) {
	            System.out.println("\nAsegúrese de usar la nomenclatura correcta para el nombre de las monedas. " +
	                    "\nEs posible hacer uso de monedas que no se encuentren en la lista de sugerencias descritas " +
	                    "\nmas abajo, siempre y cuando esten soportadas por la API y, la abreviatura sea correcta." + "\n"
	                    + "\nPeso argentimo     = ARS | peso boliviano = BOB | peso chileno      = CLP | peso colombiano = COP | real brazilero  = BRL"
	                    + "\npeso uruguayo      = UYU | peso mexicano  = MXN | nuevo sol peruano = PEN | Yuan chino      = CNY | Peso filipino   = PHP"
	                    + "\ndólar de Hong Kong = HKD | won surcoreano = KRV | rupia india       = INR | rublo ruso      = RUB | libra esterlina = GBP" + "\n");
	
	            System.out.println("Escribe la moneda para convertir, ejemplo: USD = dólar americano");
	            String monedaParaConvertir = scanner.nextLine().toUpperCase();
	
	            System.out.println("Escribe la moneda que deseas optener, ejemplo: EUR = euro");
	            String monedaConvertida = scanner.nextLine().toUpperCase();
	
	            System.out.println("Que cantidad deseas convertir?");
	            double cantidad = scanner.nextDouble();
	
	            Moneda moneda = new Moneda(monedaParaConvertir, monedaConvertida, cantidad);
	
	            ConsultaMoneda consultaMoneda = new ConsultaMoneda();
	            try {
	                double resultado = consultaMoneda.convertirMoneda(moneda);
	                System.out.println("\n" + moneda.cantidad() + " " + moneda.monedaParaConvertir() + " " + "Son: " + resultado + " " + moneda.monedaConvertida());
	            } catch (RuntimeException e) {
	                System.out.println(e.getMessage());
	            }
	
	            System.out.println("\nNecesitas seguir usando el conversor? Elige una opción:");
	            System.out.println("1) Realizar otra converción");
	            System.out.println("0) Finaliar ");
	
	            int opcion = scanner.nextInt();
	            scanner.nextLine();
	            if (opcion == 0) {
	                continuar = false;
	                System.out.println("El programa ha finalizado");
	            }
	        }
	    }
	}
## Clase Moneda.
	public record Moneda(String monedaParaConvertir,
                     String monedaConvertida,
                     double cantidad) {
	}
 ## Clase ConsultaMoneda.
	 import java.net.URI;
	import java.net.http.HttpClient;
	import java.net.http.HttpRequest;
	import java.net.http.HttpResponse;
	import com.google.gson.Gson;
	
	public class ConsultaMoneda {


    static class ConversionResponse {

        public double conversion_rate;
    }

    //  consulta y conversión
    public double convertirMoneda(Moneda moneda) {
        String apiKey = "146c12dd684d94a3e717e463";
        String url = "https://v6.exchangerate-api.com/v6/" + apiKey + "/pair/" + moneda.monedaParaConvertir() + "/" + moneda.monedaConvertida();

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            ConversionResponse conversionResponse = new Gson().fromJson(response.body(), ConversionResponse.class);

            double tasaCambio = conversionResponse.conversion_rate;
            if (tasaCambio == 0) {
                throw new RuntimeException("No se encontró una tasa de cambio válida.");
            }

            // cambio de noneda
            return moneda.cantidad() * tasaCambio;

        } catch (Exception e) {
            throw new RuntimeException("No se pudo completar la conversión: " + e.getMessage());
            }
 	   }
	}
## Demostración:
![demo_1](https://github.com/user-attachments/assets/2194e0ee-df77-47bd-85ab-e86997ba583c)
![demo_2](https://github.com/user-attachments/assets/650e9ac5-a3d2-4f45-a6c3-cb5fe4a23852)
![demo_3](https://github.com/user-attachments/assets/296b9be1-12bb-41a6-b967-00db69a8d27b)


