import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {
    public static void main(String[]args){
        Scanner scanner = new Scanner(System.in);

        //Variables migradas a BigDecimal para tener mejor precisión de datos.
        BigDecimal ingresos = BigDecimal.ZERO;
        BigDecimal acumuladorIngresos = BigDecimal.ZERO;
        BigDecimal gastos = BigDecimal.ZERO;
        BigDecimal acumuladorGastos = BigDecimal.ZERO;

        int entrada = 0;
        while (entrada != 4){
            System.out.println("\n╔════ GESTOR DE GASTOS ═════");
            System.out.println("║");
            System.out.println("║ 1. Introducir ingresos");
            System.out.println("║ 2. Introducir gastos");
            System.out.println("║ 3. Resumen cuenta");
            System.out.println("║ 4. Salir");
            System.out.println("║");
            System.out.print("╚═>> Seleccione una opción: ");

            //verificarEntrada() -> método para verificar las entradas del menú
            entrada = verificarEntrada(scanner);

            switch (entrada){
                case 1:
                    //leerImporte() -> método para controlar valores ingresados por el usuario, controlar excepciones y
                    //mostrar por pantalla instrucciones o advertencias.
                    ingresos = leerImporte(scanner, "\n>> Ingresos mensuales: ");
                    acumuladorIngresos = acumuladorIngresos.add(ingresos);
                    break;
                case 2:
                    gastos = leerImporte(scanner,"\n>> Gastos mensuales: ");
                    acumuladorGastos = acumuladorGastos.add(gastos);
                    break;
                case 3:
                    System.out.println("\n>>Resumen cuenta:\n");
                    //mostrarResumenCuenta() -> método para calcular saldo y desplegar una tabla con toda la info de la cuenta.
                    mostrarResumenCuenta(acumuladorIngresos,acumuladorGastos);
                    break;
                case 4:
                    System.out.println("\n>>Saliendo del programa...");
                    break;
                //Default eliminado, verificarEntrada() controla la entrada de valores fuera del rango.
            }
        }
        System.out.println("Programa finalizado.");
        scanner.close();
    }
    public static BigDecimal calcularSaldo(BigDecimal ingresos, BigDecimal gastos){
        // 1. Recibe dos parámetros BigDecimal y devuelve un BigDecimal.
        // 2. Resta los gastos a los ingresos mediante subtract()
        // 3. Redondea el resultado a dos decimales mediante setScale().
        return ingresos.subtract(gastos).setScale(2, RoundingMode.HALF_UP);
    }

    public static String clasificarBalance(BigDecimal saldo){
        // 1. Clasifica el balance en positivo, equilibrado o negativo, a partir del saldo.
        // 2. Aplica switch combinando .signum() que capta el signo de un número (-1, 0, 1).
        switch (saldo.signum()){
            case -1:
                return "Balance negativo.";
            case 0:
                return "Balance equilibrado.";
            case 1:
                return "Balance positivo.";
            default:
                return "Valor inesperado.";
        }
    }

    public static void mostrarResumenCuenta(BigDecimal ingresos, BigDecimal gastos){
        // 1. Recibe ingresos y gastos y hace uso de calcularSaldo().
        // 2. Clasifica el balance con clasificarBalance().
        // 3. Imprime una tabla con los datos de la cuenta, void,
        BigDecimal saldo = calcularSaldo(ingresos,gastos);
        System.out.println("╔════ ESTADO DE LA CUENTA ═════");
        System.out.println("║");
        System.out.printf("║ %-12s %10.2f €%n", "Ingresos:", ingresos);
        System.out.printf("║ %-12s %10.2f €%n", "Gastos:",gastos);
        System.out.println("║-------------------------");
        System.out.printf("║ %-12s %10.2f €%n", "Saldo",saldo);
        System.out.println("║");
        System.out.println("║ "+clasificarBalance(saldo));
        System.out.println("║");
        System.out.println("╚══════════════════════════════");
        }

    public static BigDecimal leerImporte(Scanner scanner, String mostrarMensaje) {
        // 1. Recibe un Scanner para poder solicitar al usuario valores desde el mismo método.
        // 2. Recibe un mensaje para personalizar la función desde donde se llame al método.
        // 3. Declara importe fuera del bucle para poder acceder a la variable desde el try y desde el return.
        // 4. Declara un boolean inicializado como importe NO válido, para controlar el bucle do-while.
        // 5. Controla importes negativos y excepción.
        // 6. Sale del bucle al cambiar el importe como válido y ejecuta el return.
        BigDecimal importe = BigDecimal.ZERO;
        boolean importeValido = false;
        do {
            System.out.print(mostrarMensaje);
            try {
                importe = scanner.nextBigDecimal();
                if (importe.signum() < 0) {
                    System.out.println("El importe no puede ser negativo.");
                }
                else {
                    importeValido = true;
                }
                //Importamos la clase para controlar la excepción.
            } catch (InputMismatchException e) {
                System.out.println(
                        "ERROR: No se aceptan alfanuméricos.\n¡Introduzca solo números!");
                //Consume la entrada alfanumérica (String) que queda pendiente.
                scanner.next();
            }
        } while (!importeValido);
        return importe.setScale(2, RoundingMode.HALF_UP);
    }

    public static int verificarEntrada(Scanner scanner){
        // Verifica que la entrada al menú se encuentre entre 1 y 4, y controla excepciones alfanuméricas.
        // Utiliza una estructura similar a la de leerImporte.
        int opcion = 0;
        boolean opcionValida = false;
        do{
            try {
                opcion = scanner.nextInt();
                if(opcion < 1 || opcion > 4){
                    System.out.print("\n>> ERROR: Opciones entre 1 y 4: ");
                }else {
                    opcionValida = true;
                }
            } catch (InputMismatchException e){
                System.out.print("\n>> ERROR: No se aceptan alfanuméricos.\n>> Por favor seleccione una opción entre 1 y 4: ");
                scanner.next();
            }
        } while (!opcionValida);
        return opcion;
    }

}
