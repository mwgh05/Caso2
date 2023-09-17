package elementos;

import java.util.ArrayList;
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
}

