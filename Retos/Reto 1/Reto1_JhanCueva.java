
package Miproyecto;
import java.util.Scanner;
public class Reto1_JhanCueva {
     public static String nivelRiesgo(double irca) {

        if (irca >= 0 && irca <= 5) {
            return "SIN RIESGO";
        } else if (irca > 5 && irca <= 14) {
            return "BAJO";
        } else if (irca > 14 && irca <= 35) {
            return "MEDIO";
        } else if (irca > 35 && irca <= 80) {
            return "ALTO";
        } else if (irca > 80 && irca <= 100) {
            return "INVIABLE SANITARIAMENTE";
        } else {
            return "VALOR INVALIDO";
        }
    }

    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);

        System.out.print("Ingrese la cantidad de elementos: ");
        int n = entrada.nextInt();

        double[] vector = new double[n];

        double suma = 0;
        double mayor = 0;
        double menor = 100;

        // Leer los datos
        for (int i = 0; i < n; i++) {

            System.out.print("Ingrese el IRCA " + (i + 1) + ": ");
            vector[i] = entrada.nextDouble();

            suma += vector[i];

            if (vector[i] > mayor) {
                mayor = vector[i];
            }

            if (vector[i] < menor) {
                menor = vector[i];
            }
        }

        // Calcular promedio
        double promedio = suma / n;

        // Mostrar resultados
        System.out.println("\n--- RESULTADOS ---");

        System.out.println("Promedio: " + promedio);
        System.out.println("Nivel de riesgo promedio: " + nivelRiesgo(promedio));

        System.out.println("Mayor IRCA: " + mayor);
        System.out.println("Nivel de riesgo más alto: " + nivelRiesgo(mayor));

        System.out.println("Menor IRCA: " + menor);
        System.out.println("Nivel de riesgo más bajo: " + nivelRiesgo(menor));

        entrada.close();
    }
    
}
