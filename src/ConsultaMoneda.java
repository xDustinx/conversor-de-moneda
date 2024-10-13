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