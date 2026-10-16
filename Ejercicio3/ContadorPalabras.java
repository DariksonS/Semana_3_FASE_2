package Ejercicio3;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
public class ContadorPalabras {
	public static void main(String[] args) {
        File archivo = new File("lear.txt");
        if (!archivo.exists()) {
            System.out.println("El archivo 'lear.txt' no se encuentra en la carpeta del proyecto.");
            return;
        }
        int totalLineas = 0;
        int totalPalabras = 0;
        long totalCaracteres = 0;
        ArrayList<String> listaPalabras = new ArrayList<>();
        ArrayList<Integer> listaFrecuencias = new ArrayList<>();
        try (BufferedReader lector = new BufferedReader(new FileReader(archivo))) {
            String linea;

            while ((linea = lector.readLine()) != null) {
                totalLineas++;
                totalCaracteres += linea.length();

                String palabraActual = "";
                
                for (int i = 0; i < linea.length(); i++) {
                    char c = linea.charAt(i);
                    if (Character.isLetterOrDigit(c)) {
                        palabraActual += Character.toLowerCase(c);
                    } else {
                        if (!palabraActual.isEmpty()) {
                            totalPalabras++;
                            agregarOContarPalabra(palabraActual, listaPalabras, listaFrecuencias);
                            palabraActual = "";
                        }
                    }
                }

                if (!palabraActual.isEmpty()) {
                    totalPalabras++;
                    agregarOContarPalabra(palabraActual, listaPalabras, listaFrecuencias);
                }
            }

        } catch (IOException e) {
            System.out.println("Ocurrió un error al leer el archivo: " + e.getMessage());
            return;
        }

        double promedioPalabras = 0;
        if (totalLineas > 0) {
            promedioPalabras = (double) totalPalabras / totalLineas;
        }
        System.out.println("CONTADOR DE PALABRAS");
        System.out.println("Total de líneas: " + totalLineas);
        System.out.println("Total de palabras: " + totalPalabras);
        System.out.println("Total de caracteres (sin saltos de línea): " + totalCaracteres);
        System.out.printf("Promedio de palabras por línea: %.2f\n", promedioPalabras);

        mostrarMasFrecuentes(listaPalabras, listaFrecuencias);
    }

    private static void agregarOContarPalabra(String palabra, ArrayList<String> palabras, ArrayList<Integer> frecuencias) {
        int indice = palabras.indexOf(palabra);
        if (indice != -1) {
            int frecuenciaActual = frecuencias.get(indice);
            frecuencias.set(indice, frecuenciaActual + 1);
        } else {
            palabras.add(palabra);
            frecuencias.add(1);
        }
    }

    private static void mostrarMasFrecuentes(ArrayList<String> palabras, ArrayList<Integer> frecuencias) {
        if (palabras.isEmpty()) {
            System.out.println("No se encontraron palabras en el archivo.");
            return;
        }

        int maxFrecuencia = 0;
        for (int i = 0; i < frecuencias.size(); i++) {
            if (frecuencias.get(i) > maxFrecuencia) {
                maxFrecuencia = frecuencias.get(i);
            }
        }

        System.out.println("Palabras más frecuentes (Aparecen " + maxFrecuencia + " veces):");
        for (int i = 0; i < palabras.size(); i++) {
            if (frecuencias.get(i) == maxFrecuencia) {
                System.out.println(" - \"" + palabras.get(i) + "\": " + maxFrecuencia + " veces");
            }
        }
    }
}
