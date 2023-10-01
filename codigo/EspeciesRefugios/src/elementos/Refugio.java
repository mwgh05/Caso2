package elementos;
import java.util.ArrayList;
import java.util.List;

public class Refugio {
    private String nombre;
    private String provincia;
    private String canton;
    private String senas;
    private List<Especie> especies;

    public Refugio(String nombre, String provincia, String canton, String senas) {
        this.nombre = nombre;
        this.provincia = provincia;
        this.canton = canton;
        this.senas = senas;
        this.especies = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public List<Especie> getEspecies() {
        return especies;
    }

    public void agregarEspecie(Especie especie) {
        especies.add(especie);
    }
}

/*import java.util.ArrayList;
import java.util.List;

public class Refugio {
    private String nombre;
    private String ubicacion;
    private String area;
    private List<Especie> especies; 

    public Refugio(String nombre, String ubicacion, String area) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.area = area;
        this.especies = new ArrayList<>(); 
    }

    public String getNombre() {
        return nombre;
    }

    public void agregarEspecie(Especie especie) {
        especies.add(especie);
    }

    public List<Especie> getEspecies() {
        return especies;
    }
}*/

