package elementos;

public class Individuo {
    private String nombre;
    private String fechaIngreso;
    private String razonIngreso;
    private String estado;
    private double peso;
    private String comentarios;

    public Individuo(String nombre, String fechaIngreso, String razonIngreso, String estado, double peso, String comentarios) {
        this.nombre = nombre;
        this.fechaIngreso = fechaIngreso;
        this.razonIngreso = razonIngreso;
        this.estado = estado;
        this.peso = peso;
        this.comentarios = comentarios;
    }
}