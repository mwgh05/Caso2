package elementos;
import java.util.List;
import java.util.ArrayList;

public class Provincia {
	private String nombre;
	private List<String> cantones;
	
	public Provincia(String nombre) {
		this.nombre=nombre;
		this.cantones=new ArrayList<>();
	}
	
	public String getNombre() {
        return nombre;
    }

    public List<String> getCantones() {
        return cantones;
    }
    
    public void agregarCanton(String canton) {
    	cantones.add(canton);
    }

}
