package Actividad3;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
public class Binario2 {
	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int filas = 0;
        int columnas = 0;

        do {
            System.out.print("Número de filas: ");
            if (sc.hasNextInt()) {
                filas = sc.nextInt();
            } else {
                sc.next(); 
            }
        } while (filas <= 0);

        do {
            System.out.print("Número de columnas: ");
            if (sc.hasNextInt()) {
                columnas = sc.nextInt();
            } else {
                sc.next();
            }
        } while (columnas <= 0);

        double[][] matriz = new double[filas][columnas]; 

        System.out.println("\nIntroduce los valores de la matriz:");
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                System.out.print("matriz[" + i + "][" + j + "]: ");
                matriz[i][j] = sc.nextDouble();
            }
        }

        sc.close(); 

        try (DataOutputStream salida = new DataOutputStream(new FileOutputStream("matriz.dat"))) {
            
            salida.writeInt(filas);
            salida.writeInt(columnas);

            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < columnas; j++) {
                    salida.writeDouble(matriz[i][j]);
                }
            }

            System.out.println("\n¡Datos guardados exitosamente en el archivo 'matriz.dat'!");

        } catch (FileNotFoundException e) {
            System.out.println("Error: No se pudo encontrar o crear el archivo. " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error de entrada/salida: " + e.getMessage());
        }
    }
}
