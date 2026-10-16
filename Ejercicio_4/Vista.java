package Ejercicio_4;

import java.util.ArrayList;
import java.util.Scanner;

//Vista: muestra el menú, pide los datos y presenta los resultados.
public class Vista
{
    private Scanner sc = new Scanner(System.in);

    public int mostrarMenu()
    {
        System.out.println("\n------- HOTEL - GESTIÓN DE HABITACIONES -------");
        System.out.println("1. Listar todas las habitaciones");
        System.out.println("2. Agregar una nueva habitación");
        System.out.println("3. Buscar habitación por ID");
        System.out.println("4. Eliminar habitación por ID");
        System.out.println("5. Salir");
        return leerEntero("Elige una opción: ");
    }

    public int leerEntero(String mensaje)
    {
        while (true)
        {
            System.out.print(mensaje);
            try
            {
                return Integer.parseInt(sc.nextLine().trim());
            }
            catch (NumberFormatException e)
            {
                System.out.println("Entrada inválida: debes escribir un número entero.");
            }
        }
    }

    public int leerEnteroPositivo(String mensaje)
    {
        int numero = leerEntero(mensaje);
        while (numero <= 0)
        {
            System.out.println("El valor debe ser mayor que cero.");
            numero = leerEntero(mensaje);
        }
        return numero;
    }

    public double leerPrecio(String mensaje)
    {
        while (true)
        {
            System.out.print(mensaje);
            try
            {
                double valor = Double.parseDouble(sc.nextLine().trim());
                if (valor > 0)
                {
                    return valor;
                }
                System.out.println("El precio debe ser mayor que cero.");
            }
            catch (NumberFormatException e)
            {
                System.out.println("Entrada inválida: escribe un número usando punto (ejemplo: 150.50).");
            }
        }
    }

    public String leerTexto(String mensaje)
    {
        while (true)
        {
            System.out.print(mensaje);
            String texto = sc.nextLine().trim();
            if (texto.isEmpty())
            {
                System.out.println("El texto no puede estar vacío.");
            }
            else if (texto.contains(";"))
            {
                System.out.println("El texto no puede contener el carácter ';'.");
            }
            else
            {
                return texto;
            }
        }
    }

    public void mostrarHabitaciones(ArrayList<Habitacion> lista)
    {
        if (lista.isEmpty())
        {
            System.out.println("No hay habitaciones registradas.");
            return;
        }
        System.out.println("---------------------------------------------");
        for (Habitacion h : lista)
        {
            System.out.println(h);
        }
        System.out.println("---------------------------------------------");
        System.out.println("Total: " + lista.size() + " habitación(es)");
    }

    public void mostrarMensaje(String mensaje)
    {
        System.out.println(mensaje);
    }
}