package elementos;
import java.util.ArrayList;
import exception.*;
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

    public void agregarEspecie(Especie especie) throws EspecieDuplicadaException {
        if (especies.contains(especie)) {
            throw new EspecieDuplicadaException("La especie ya existe en este refugio.");
        }
        especies.add(especie);
    }
}



