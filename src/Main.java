import java.util.Scanner;

public class Main {
    public static void main(String[]args){
        Scanner scanner = new Scanner(System.in);

        double ingresos = 0;
        double acumuladorIngresos = 0;
        double gastos = 0;
        double acumuladorGastos = 0;
        double saldo = 0;
        /*---------------Primera Versión Ejecutable-------------------
        El programa deberá:
        1. Solicitar al usuario sus ingresos mensuales.
        2. Solicitar sus gastos mensuales.
        3. Calcular el saldo.
        4. Mostrar ingresos, gastos y saldo.
        5. Indicar si el balance es positivo, equilibrado o negativo.
         */

        //---------------MENÚ---------------
        //Repetir el menú hasta que el usuario decida salir.
        int opcion = 0;
        while (opcion != 4){
            System.out.println("\n===GESTOR DE GASTOS===\n");
            System.out.println("1. Introducir ingresos");
            System.out.println("2. Introducir gastos");
            System.out.println("3. Consultar saldo");
            System.out.println("4. Salir");
            System.out.print("\nSelecciones una opción: ");
            opcion = scanner.nextInt();

            switch (opcion){
                case 1:
                    System.out.print("\n>> Ingresos mensuales: ");
                    ingresos = scanner.nextDouble();
                    //Control de ingresos negativos
                    while (ingresos<0){
                        System.out.println("Los ingresos no pueden ser negativos.");
                        System.out.print("Por favor, inténtelo nuevamente: ");
                        ingresos = scanner.nextDouble();
                    }
                    acumuladorIngresos += ingresos;
                    break;
                case 2:
                    System.out.print("\n>> Gastos mensuales: ");
                    gastos = scanner.nextDouble();

                    //Control de números negativos
                    while (gastos<0){
                        System.out.println("Los gastos no pueden ser negativos.");
                        System.out.print("Por favor, inténtelo nuevamente: ");
                        gastos = scanner.nextDouble();
                    }
                    //REVISAR!!!!!
                    acumuladorGastos += gastos;
                    break;
                case 3:
                    saldo = Math.round((acumuladorIngresos - acumuladorGastos)* 100.0) / 100.0;
                    System.out.println("\n>>Saldo disponible: "+saldo);
                    break;
                case 4:
                    System.out.println("\n>>Saliendo del programa...");
                    break;
                default:
                    System.out.println("ERROR: La opción no está entre 1 y 4.");
            }
        }
        System.out.println("Programa finalizado");


        scanner.close();
    }
}
