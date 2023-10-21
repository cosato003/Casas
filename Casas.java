import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Casas {

    static String getNombreDeCasa() {
        Random random = new Random();
        String[] prefijos = { "la", "una" };
        String[] mid = { "cueva", "casa", "mansión" };
        String[] suf = {
            "de la montaña",
            "del bosque",
            "del pantano",
            "del desierto",
            "de Drácula",
            "de la bruja",
            "del vampiro",
        };
        int randPref = random.nextInt(prefijos.length);
        int randMid = random.nextInt(mid.length);
        int randSuf = random.nextInt(suf.length);
        return prefijos[randPref] + " " + mid[randMid] + " " + suf[randSuf];
    }

    static int getPrecio() {
        Random random = new Random();
        return random.nextInt(1000000);
    }

    public static void main(String[] args) {
        String[] nombreBarrios = {
            "Floresta",
            "Aranjuez",
            "Manrique",
            "Carlos E",
            "Robledo",
        };

        HashMap<String, Integer> ventas = new HashMap<String, Integer>();
        HashMap<String, Integer> casasVendidas = new HashMap<String, Integer>();

        for (String barrio : nombreBarrios) {
            ventas.put(barrio, 0);
            casasVendidas.put(barrio, 0);
        }

        int userEntry = 0;
        Random rnd = new Random();
        Scanner scanner = new Scanner(System.in);

        System.out.println("¡Hola! Te doy la bienvenida a Tinder House!");
        System.out.println("El juego consiste en hacer match con cada casa que te guste");
        System.out.println("Presiona 0 para descartar, 1 para comprar y 2 para terminar el juego");

        int maxBarrioMaxVenta = 0;
        int maxBarrioMinVenta = 0;
        int maxBarrioMaxCasasVendidas = 0;

        do {
            String nombreCasa = getNombreDeCasa();
            int randBarrio = rnd.nextInt(nombreBarrios.length);
            int precio = getPrecio();

            int precioConPeso = precio * (randBarrio + 1); // Multiplicamos por el peso del barrio

            System.out.println("------*-------");
            System.out.println(nombreCasa);
            System.out.println(nombreBarrios[randBarrio]);
            System.out.println("Precio original: " + precio + " Bolívares");
            System.out.println("Precio con peso: " + precioConPeso + " Bolívares");
            System.out.println("------*-------");

            System.out.print("¿Te gusta esta casa? (0 para descartar, 1 para comprar, 2 para terminar): ");
            userEntry = scanner.nextInt();

            if (userEntry == 1) {
                int actualVenta = ventas.get(nombreBarrios[randBarrio]);
                int newVenta = actualVenta + precioConPeso;
                ventas.replace(nombreBarrios[randBarrio], newVenta);
                int casasVendidasEnBarrio = casasVendidas.get(nombreBarrios[randBarrio]) + 1;
                casasVendidas.replace(nombreBarrios[randBarrio], casasVendidasEnBarrio);
            } else if (userEntry == 0) {
                // El usuario rechaza la casa, se genera otra al final del bucle.
            }
        } while (userEntry != 2);

        scanner.close();

        System.out.println("Reporte de ventas:");

        int maxVenta = Integer.MIN_VALUE;
        int minVenta = Integer.MAX_VALUE;
        int maxCasasVendidas = Integer.MIN_VALUE;
        String barrioMaxVenta = "";
        String barrioMinVenta = "";
        String barrioMaxCasasVendidas = "";

        for (String barrio : ventas.keySet()) {
            int venta = ventas.get(barrio);
            int casasVendidasEnBarrio = casasVendidas.get(barrio);

            if (venta > maxVenta || (venta == maxVenta && casasVendidasEnBarrio > maxBarrioMaxVenta)) {
                maxVenta = venta;
                barrioMaxVenta = barrio;
                maxBarrioMaxVenta = casasVendidasEnBarrio;
            }

            if (venta < minVenta || (venta == minVenta && casasVendidasEnBarrio < maxBarrioMinVenta)) {
                minVenta = venta;
                barrioMinVenta = barrio;
                maxBarrioMinVenta = casasVendidasEnBarrio;
            }

            if (casasVendidasEnBarrio > maxCasasVendidas
                    || (casasVendidasEnBarrio == maxCasasVendidas && casasVendidasEnBarrio > maxBarrioMaxCasasVendidas)) {
                maxCasasVendidas = casasVendidasEnBarrio;
                barrioMaxCasasVendidas = barrio;
                maxBarrioMaxCasasVendidas = casasVendidasEnBarrio;
            }

            System.out.println(barrio + " vendió " + venta);
        }

        System.out.println("Barrio que más dinero ganó: " + barrioMaxVenta);
        System.out.println("Barrio que menos dinero ganó: " + barrioMinVenta);
        System.out.println("Barrio que más casas vendió: " + barrioMaxCasasVendidas);
    }
   
}
