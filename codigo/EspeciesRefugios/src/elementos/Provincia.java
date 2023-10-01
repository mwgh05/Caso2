package elementos;
import java.util.List;

public class Provincia {
	private String nombre;
	private List<String> cantones;
	
	public Provincia(String nombre) {
		this.nombre=nombre;
	}
	
	public String getNombre() {
        return nombre;
    }

    public List<String> getCantones() {
        return cantones;
    }

}
