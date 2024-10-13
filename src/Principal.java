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