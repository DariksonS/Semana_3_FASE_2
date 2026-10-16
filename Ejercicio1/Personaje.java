package Ejercicio1;

public class Personaje {
	private String nombre;
    private int vida;
    private int ataque;
    private int defensa;
    private int alcance;
    public Personaje(String nombre, int vida, int ataque, int defensa, int alcance) {
        this.nombre = nombre;
        setVida(vida);
        setAtaque(ataque);
        setDefensa(defensa);
        setAlcance(alcance);
    }
    public String getNombre() { return nombre; }
    public int getVida() { return vida; }
    public int getAtaque() { return ataque; }
    public int getDefensa() { return defensa; }
    public int getAlcance() { return alcance; }
    public void setVida(int vida) {
        this.vida = (vida > 0) ? vida : 1;
    }
    public void setAtaque(int ataque) {
        this.ataque = (ataque > 0) ? ataque : 1;
    }
    public void setDefensa(int defensa) {
        this.defensa = (defensa > 0) ? defensa : 1;
    }
    public void setAlcance(int alcance) {
        this.alcance = (alcance > 0) ? alcance : 1;
    }

    @Override
    public String toString() {
        return nombre + "," + vida + "," + ataque + "," + defensa + "," + alcance;
    }
    public String mostrarInfo() {
        return "Personaje: " + nombre + " | Vida: " + vida + " | Ataque: " + ataque + 
               " | Defensa: " + defensa + " | Alcance: " + alcance;
    }
}
