package elementos;

public class Especie {
	String nombre;
	String descripcion;
	int cantidad;
	String habitat;
	public Especie(String nombre, String descripcion, int cantidad, String habitat) {
		this.nombre=nombre;
		this.descripcion=descripcion;
		this.cantidad=cantidad;
		this.habitat=habitat;
	}
	public String getNombre() {
        return nombre;
    }
	public String getDescripcion() {
        return descripcion;
    }
	public int getCantidad() {
        return cantidad;
    }
	public String getHabitat() {
        return habitat;
    }

}
