package Ejercicio_2;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
public class Gestor {
	private ArrayList<Personaje> listaPersonajes;
    private final String NOMBRE_ARCHIVO = "src/Ejercicio_2/personajes.txt";

    public Gestor() {
        listaPersonajes = new ArrayList<>();
        cargarDesdeArchivo();
    }
    public boolean agregarPersonaje(Personaje p) {
        for (Personaje existente : listaPersonajes) {
            if (existente.getNombre().equalsIgnoreCase(p.getNombre())) {
                return false;
            }
        }
        listaPersonajes.add(p);
        guardarEnArchivo();
        return true;
    }
    public void mostrarPersonajes() {
        if (listaPersonajes.isEmpty()) {
            System.out.println("No hay personajes registrados.");
            return;
        }
        System.out.println("\n--- LISTA DE PERSONAJES ---");
        for (Personaje p : listaPersonajes) {
            System.out.println(p.mostrarInfo());
        }
    }
    public boolean modificarPersonaje(String nombre, int nuevaVida, int nuevoAtaque, int nuevaDefensa, int nuevoAlcance) {
        for (Personaje p : listaPersonajes) {
            if (p.getNombre().equalsIgnoreCase(nombre)) {
                p.setVida(nuevaVida);
                p.setAtaque(nuevoAtaque);
                p.setDefensa(nuevaDefensa);
                p.setAlcance(nuevoAlcance);
                guardarEnArchivo();
                return true;
            }
        }
        return false;
    }
    public boolean borrarPersonaje(String nombre) {
        for (int i = 0; i < listaPersonajes.size(); i++) {
            if (listaPersonajes.get(i).getNombre().equalsIgnoreCase(nombre)) {
                listaPersonajes.remove(i);
                guardarEnArchivo();
                return true;
            }
        }
        return false;
    }

    public void mostrarEstadisticas() {
        if (listaPersonajes.isEmpty()) {
            System.out.println("No hay personajes registrados.");
            return;
        }
        int sumaVida = 0, sumaAtaque = 0, sumaDefensa = 0, sumaAlcance = 0;
        for (Personaje p : listaPersonajes) {
            sumaVida += p.getVida();
            sumaAtaque += p.getAtaque();
            sumaDefensa += p.getDefensa();
            sumaAlcance += p.getAlcance();
        }
        int total = listaPersonajes.size();
        System.out.println("\n--- ESTADÍSTICAS ---");
        System.out.println("Total de personajes: " + total);
        System.out.printf("Promedio de vida: %.2f%n", (double) sumaVida / total);
        System.out.printf("Promedio de ataque: %.2f%n", (double) sumaAtaque / total);
        System.out.printf("Promedio de defensa: %.2f%n", (double) sumaDefensa / total);
        System.out.printf("Promedio de alcance: %.2f%n", (double) sumaAlcance / total);
    }

    public boolean actualizarAtributo(String nombre, int atributo, int nuevoValor) {
        for (Personaje p : listaPersonajes) {
            if (p.getNombre().equalsIgnoreCase(nombre)) {
                switch (atributo) {
                    case 1:
                        p.setVida(nuevoValor);
                        break;
                    case 2:
                        p.setAtaque(nuevoValor);
                        break;
                    case 3:
                        p.setDefensa(nuevoValor);
                        break;
                    case 4:
                        p.setAlcance(nuevoValor);
                        break;
                }
                guardarEnArchivo();
                return true;
            }
        }
        return false;
    }

    private void guardarEnArchivo() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(NOMBRE_ARCHIVO))) {
            for (Personaje p : listaPersonajes) {
                writer.println(p.toString());
            }
        } catch (IOException e) {
            System.out.println("Error al guardar en el archivo: " + e.getMessage());
        }
    }
        private void cargarDesdeArchivo() {
            File archivo = new File(NOMBRE_ARCHIVO);
            if (!archivo.exists()) {
                return;
            }
            try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
                String linea;
                while ((linea = reader.readLine()) != null) {
                    String[] partes = linea.split(",");
                    if (partes.length == 5) {
                        String nombre = partes[0];
                        int vida = Integer.parseInt(partes[1]);
                        int ataque = Integer.parseInt(partes[2]);
                        int defensa = Integer.parseInt(partes[3]);
                        int alcance = Integer.parseInt(partes[4]);
                        listaPersonajes.add(new Personaje(nombre, vida, ataque, defensa, alcance));
                    }
                }
            } catch (IOException | NumberFormatException e) {
                System.out.println("Error al leer el archivo de personajes: " + e.getMessage());
            }
    }
}
