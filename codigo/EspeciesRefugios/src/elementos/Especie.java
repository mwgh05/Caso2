package elementos;
import java.util.ArrayList;
import java.util.List;

public class Especie {
    private String nombre;
    private List<Individuo> individuos;

    public Especie(String nombre) {
        this.nombre = nombre;
        this.individuos = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public List<Individuo> getIndividuos() {
        return individuos;
    }

    public void agregarIndividuo(Individuo individuo) {
        individuos.add(individuo);
    }
}
/*import java.util.List;
import java.util.ArrayList;

public class Especie {
	String nombre;
	String descripcion;
	int cantidad;
	String habitat;
	private List <Especie> individuos;
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
*/