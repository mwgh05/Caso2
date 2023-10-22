package elementos;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

public class Especie implements Serializable{
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
