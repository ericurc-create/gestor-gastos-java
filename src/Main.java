import java.util.Scanner;

public class Main {
    public static void main(String[]args){
        Scanner scanner = new Scanner(System.in);

        double ingresos;
        double gastos;
        double saldo;
        /*---------------Primera Versión Ejecutable-------------------
        El programa deberá:
        1. Solicitar al usuario sus ingresos mensuales.
        2. Solicitar sus gastos mensuales.
        3. Calcular el saldo.
        4. Mostrar ingresos, gastos y saldo.
        5. Indicar si el balance es positivo, equilibrado o negativo.
         */

        System.out.println("---------GESTOR DE GASTOS---------");
        System.out.print("Ingresos mensuales: ");
        ingresos = scanner.nextDouble();
        while (ingresos<0){
            System.out.println("Los ingresos no pueden ser negativos.");
            System.out.print("Por favor, inténtelo nuevamente: ");
            ingresos = scanner.nextDouble();
        }
        System.out.print("Gastos mensuales: ");
        gastos = scanner.nextDouble();
        while (gastos<0){
            System.out.println("Los gastos no pueden ser negativos.");
            System.out.print("Por favor, inténtelo nuevamente: ");
            gastos = scanner.nextDouble();
        }

        saldo = Math.round((ingresos - gastos)* 100.0) / 100.0;

        //Mostrar ingresos, gastos y saldo por pantalla
        System.out.println("\n---------DATOS DE LA CUENTA---------");
        System.out.println("Ingresos: "+ingresos+"€");
        System.out.println("Gastos: "+gastos+"€");
        System.out.println("Saldo: "+saldo+"€");

        //Calcular el balance de la cuenta y mostrar por pantalla
        if (saldo > 0){
            System.out.println("Balance positivo.");
        } else if (saldo == 0){
            System.out.println("Balance equilibrado.");
        } else {
            System.out.println("Balance negativo.");
        }




        scanner.close();
    }
}
