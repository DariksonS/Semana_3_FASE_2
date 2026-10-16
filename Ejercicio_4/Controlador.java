package Ejercicio_4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

//Controlador: coordina la vista, el modelo y el archivo de texto.
public class Controlador
{
    private static final String RUTA = "src/Ejercicio_4/habitaciones.txt";

    private Vista vista;

    public Controlador(Vista vista)
    {
        this.vista = vista;
    }

    public void iniciar()
    {
        vista.mostrarMensaje("Habitaciones almacenadas en el archivo:");
        vista.mostrarHabitaciones(leerRegistros());

        int opcion;
        do
        {
            opcion = vista.mostrarMenu();
            switch (opcion)
            {
                case 1:
                    vista.mostrarHabitaciones(leerRegistros());
                    break;
                case 2:
                    agregarRegistro();
                    break;
                case 3:
                    buscarRegistro();
                    break;
                case 4:
                    eliminarRegistro();
                    break;
                case 5:
                    vista.mostrarMensaje("Saliendo del programa. ¡Hasta luego!");
                    break;
                default:
                    vista.mostrarMensaje("Opción no válida. Elige un número del 1 al 5.");
            }
        }
        while (opcion != 5);
    }

    public ArrayList<Habitacion> leerRegistros()
    {
        ArrayList<Habitacion> lista = new ArrayList<>();
        File archivo = new File(RUTA);

        if (!archivo.exists())
        {
            return lista;
        }

        try (BufferedReader entrada = new BufferedReader(new FileReader(archivo)))
        {
            String linea;
            int numeroLinea = 0;
            while ((linea = entrada.readLine()) != null)
            {
                numeroLinea++;
                if (linea.trim().isEmpty())
                {
                    continue;
                }
                try
                {
                    String[] partes = linea.split(";");
                    int id = Integer.parseInt(partes[0].trim());
                    String descripcion = partes[1].trim();
                    double precio = Double.parseDouble(partes[2].trim());
                    lista.add(new Habitacion(id, descripcion, precio));
                }
                catch (NumberFormatException | ArrayIndexOutOfBoundsException e)
                {
                    vista.mostrarMensaje("Aviso: la línea " + numeroLinea + " del archivo tiene un formato incorrecto y se omitió.");
                }
            }
        }
        catch (FileNotFoundException e)
        {
            vista.mostrarMensaje("Error: no se encontró el archivo " + RUTA);
        }
        catch (IOException e)
        {
            vista.mostrarMensaje("Error al leer el archivo: " + e.getMessage());
        }
        return lista;
    }

    public void agregarRegistro()
    {
        ArrayList<Habitacion> lista = leerRegistros();
        int id = vista.leerEnteroPositivo("ID de la habitación: ");

        if (buscarPorId(lista, id) != null)
        {
            vista.mostrarMensaje("Error: ya existe una habitación con el ID " + id + ".");
            return;
        }

        String descripcion = vista.leerTexto("Descripción (ej. Suite matrimonial): ");
        double precio = vista.leerPrecio("Precio por noche (S/): ");

        //true = añade al final del archivo sin borrar lo anterior.
        try (PrintWriter salida = new PrintWriter(new FileWriter(RUTA, true)))
        {
            salida.println(id + ";" + descripcion + ";" + precio);
            vista.mostrarMensaje("Habitación agregada correctamente.");
        }
        catch (IOException e)
        {
            vista.mostrarMensaje("Error al guardar la habitación: " + e.getMessage());
        }
    }

    public void buscarRegistro()
    {
        int id = vista.leerEnteroPositivo("ID de la habitación a buscar: ");
        Habitacion h = buscarPorId(leerRegistros(), id);

        if (h != null)
        {
            vista.mostrarMensaje("Encontrada -> " + h);
        }
        else
        {
            vista.mostrarMensaje("No existe una habitación con el ID " + id + ".");
        }
    }

    public void eliminarRegistro()
    {
        ArrayList<Habitacion> lista = leerRegistros();
        int id = vista.leerEnteroPositivo("ID de la habitación a eliminar: ");
        Habitacion h = buscarPorId(lista, id);

        if (h == null)
        {
            vista.mostrarMensaje("No existe una habitación con el ID " + id + ".");
            return;
        }

        lista.remove(h);
        if (guardarTodos(lista))
        {
            vista.mostrarMensaje("Habitación con ID " + id + " eliminada correctamente.");
        }
    }

    private boolean guardarTodos(ArrayList<Habitacion> lista)
    {
        try (PrintWriter salida = new PrintWriter(new FileWriter(RUTA)))
        {
            for (Habitacion h : lista)
            {
                salida.println(h.getId() + ";" + h.getDescripcion() + ";" + h.getPrecio());
            }
            return true;
        }
        catch (IOException e)
        {
            vista.mostrarMensaje("Error al actualizar el archivo: " + e.getMessage());
            return false;
        }
    }

    private Habitacion buscarPorId(ArrayList<Habitacion> lista, int id)
    {
        for (Habitacion h : lista)
        {
            if (h.getId() == id)
            {
                return h;
            }
        }
        return null;
    }
}