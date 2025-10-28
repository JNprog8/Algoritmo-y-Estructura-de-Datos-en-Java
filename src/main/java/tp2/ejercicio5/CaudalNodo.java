package tp2.ejercicio5;

public class CaudalNodo {
    private String nombre;
    private double caudal;

    public CaudalNodo(String nombre, double caudal) {
        this.nombre = nombre;
        this.caudal = caudal;
    }

    public double getCaudal() {
        return caudal;
    }

    public void setCaudal(double caudal) {
        this.caudal = caudal;
    }
}

