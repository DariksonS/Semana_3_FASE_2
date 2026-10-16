package Ejercicio_4;

//Modelo: representa una habitación del hotel.
public class Habitacion
{
    private int id;
    private String descripcion;
    private double precio;

    public Habitacion()
    {
    }

    public Habitacion(int id, String descripcion, double precio)
    {
        this.id = id;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getDescripcion()
    {
        return descripcion;
    }

    public void setDescripcion(String descripcion)
    {
        this.descripcion = descripcion;
    }

    public double getPrecio()
    {
        return precio;
    }

    public void setPrecio(double precio)
    {
        this.precio = precio;
    }

    @Override
    public String toString()
    {
        return "ID: " + id + " | " + descripcion + " | S/ " + String.format("%.2f", precio) + " por noche";
    }
}