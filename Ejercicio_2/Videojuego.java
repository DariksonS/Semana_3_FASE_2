package Ejercicio_2;
import java.util.Scanner;
public class Videojuego {
	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Gestor gestor = new Gestor();
        int opcion = 0;

        do {
            System.out.println("GESTOR DE PERSONAJES DE VIDEOJUEGO");
            System.out.println("1. Añadir personaje");
            System.out.println("2. Mostrar personajes");
            System.out.println("3. Modificar personaje");
            System.out.println("4. Borrar personaje");
            System.out.println("5. Mostrar estadísticas");
            System.out.println("6. Actualizar un solo atributo");
            System.out.println("7. Salir");
            System.out.print("Elija una opción: ");

            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
                scanner.nextLine(); 

                switch (opcion) {
                    case 1:
                        System.out.print("Nombre del personaje: ");
                        String nombre = scanner.nextLine();
                        System.out.print("Vida (>0): ");
                        int vida = scanner.nextInt();
                        System.out.print("Ataque (>0): ");
                        int ataque = scanner.nextInt();
                        System.out.print("Defensa (>0): ");
                        int defensa = scanner.nextInt();
                        System.out.print("Alcance (>0): ");
                        int alcance = scanner.nextInt();

                        Personaje nuevo = new Personaje(nombre, vida, ataque, defensa, alcance);
                        if (gestor.agregarPersonaje(nuevo)) {
                            System.out.println("¡Personaje añadido y guardado con éxito!");
                        } else {
                            System.out.println("Error: Ya existe un personaje con ese nombre.");
                        }
                        break;

                    case 2:
                        gestor.mostrarPersonajes();
                        break;

                    case 3:
                        System.out.print("Ingrese el nombre del personaje a modificar: ");
                        String nomMod = scanner.nextLine();
                        System.out.print("Nueva Vida (>0): ");
                        int nVida = scanner.nextInt();
                        System.out.print("Nuevo Ataque (>0): ");
                        int nAtq = scanner.nextInt();
                        System.out.print("Nueva Defensa (>0): ");
                        int nDef = scanner.nextInt();
                        System.out.print("Nuevo Alcance (>0): ");
                        int nAlc = scanner.nextInt();

                        if (gestor.modificarPersonaje(nomMod, nVida, nAtq, nDef, nAlc)) {
                            System.out.println("¡Personaje modificado y actualizado en el archivo!");
                        } else {
                            System.out.println("No se encontró un personaje con ese nombre.");
                        }
                        break;

                    case 4:
                        System.out.print("Ingrese el nombre del personaje a borrar: ");
                        String nomDel = scanner.nextLine();
                        if (gestor.borrarPersonaje(nomDel)) {
                            System.out.println("¡Personaje eliminado con éxito!");
                        } else {
                            System.out.println("No se encontró un personaje con ese nombre.");
                        }
                        break;

                    case 5:
                        gestor.mostrarEstadisticas();
                        break;

                    case 6:
                        System.out.print("Ingrese el nombre del personaje: ");
                        String nomAtr = scanner.nextLine();
                        System.out.println("¿Qué atributo desea actualizar?");
                        System.out.println("1. Vida  2. Ataque  3. Defensa  4. Alcance");
                        System.out.print("Elija una opción: ");
                        int atributo = scanner.nextInt();
                        if (atributo < 1 || atributo > 4) {
                            System.out.println("Atributo inválido.");
                            break;
                        }
                        System.out.print("Nuevo valor (>0): ");
                        int valor = scanner.nextInt();

                        if (gestor.actualizarAtributo(nomAtr, atributo, valor)) {
                            System.out.println("¡Atributo actualizado y guardado en el archivo!");
                        } else {
                            System.out.println("No se encontró un personaje con ese nombre.");
                        }
                        break;

                    case 7:
                        System.out.println("Saliendo del programa. ¡Hasta luego!");
                        break;

                    default:
                        System.out.println("Opción inválida.");
                }
            } else {
                System.out.println("Por favor, ingrese un número válido.");
                scanner.next(); 
            }

        } while (opcion != 7);

        scanner.close();
    }
}
