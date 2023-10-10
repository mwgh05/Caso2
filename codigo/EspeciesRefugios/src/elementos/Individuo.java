package elementos;

import java.util.Date;

public class Individuo {
    private Date fechaIngreso;
    private String razonIngreso;
    private String estado;
    private double peso;
    private String comentarios;

    public Individuo(Date fechaIngreso, String razonIngreso, String estado, double peso, String comentarios) {
        this.fechaIngreso = fechaIngreso;
        this.razonIngreso = razonIngreso;
        this.estado = estado;
        this.peso = peso;
        this.comentarios = comentarios;
    }
}