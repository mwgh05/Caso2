package elementos;

public class Individuo {
    private String fechaIngreso;
    private String razonIngreso;
    private String estado;
    private double peso;
    private String comentarios;

    public Individuo(String fechaIngreso, String razonIngreso, String estado, double peso, String comentarios) {
        this.fechaIngreso = fechaIngreso;
        this.razonIngreso = razonIngreso;
        this.estado = estado;
        this.peso = peso;
        this.comentarios = comentarios;
    }
}