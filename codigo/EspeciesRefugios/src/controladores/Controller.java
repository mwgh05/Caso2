package controladores;
import java.io.FileInputStream;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import javax.swing.JOptionPane;

import elementos.*;
import ventanas.IngresoEspecie;
import exception.EspecieDuplicadaException;

public class Controller{
	
	String archivo = "C:\\Users\\Melanie\\OneDrive - Estudiantes ITCR\\POO\\Caso 2\\Caso2\\codigo\\EspeciesRefugios\\src\\files\\Datos.dat";

	public void guardarRefugiosSerializados(List<Refugio> refugios) {
        try (FileOutputStream fos = new FileOutputStream(archivo);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            oos.writeObject(refugios);
            
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al guardar la especie.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public  List<Refugio> cargarRefugiosSerializados() {
    	List<Refugio> refugios=new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(archivo);
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            Object obj = ois.readObject();
            if (obj instanceof List) {
                refugios=(List<Refugio>) obj;
                return refugios;
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
		return refugios;
    }
    
    public void agregarEspecie(List<Refugio> refugios, String nombreRefugio, String nombreEspecie, Date fecha, String razon, String estado, String comentarios, String ppeso) {
    	Refugio refugioSeleccionado = null;
    	double peso=0;
    	for (Refugio refugio : refugios) {
            if (refugio.getNombre().equals(nombreRefugio)) {
                refugioSeleccionado = refugio;
                break;
            }
        }
        
        if (refugioSeleccionado != null) {
            Especie especieExistente = null;
            for (Especie especie : refugioSeleccionado.getEspecies()) {
                if (especie.getNombre().equals(nombreEspecie)) {
                    especieExistente = especie;
                    break;
                }
            }
        	
            try {
                peso = Double.parseDouble(ppeso);
            
            
            	Individuo nuevoIndividuo = new Individuo(fecha, razon, estado, peso, comentarios);
                especieExistente.agregarIndividuo(nuevoIndividuo);
                guardarRefugiosSerializados(refugios);
                JOptionPane.showMessageDialog(null, "Individuo agregado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                
         
            } catch (NumberFormatException ex) {
            	IngresoEspecie.pesoInvalido();
            }
        }else {
            JOptionPane.showMessageDialog(null, "El refugio seleccionado no existe.", "Error", JOptionPane.ERROR_MESSAGE);
        }   

            
         
    }
    public void crearEspecie(List<Refugio> refugios, String nombreRefugio, String nombreEspecie, Date fecha, String razon, String estado, String comentarios, String ppeso) {
    	double peso=0;

        Refugio refugioSeleccionado = null;
        for (Refugio refugio : refugios) {
            if (refugio.getNombre().equals(nombreRefugio)) {
                refugioSeleccionado = refugio;
                break;
            }
        }

        if (refugioSeleccionado != null) {
            
            try {
                peso = Double.parseDouble(ppeso);                                  
            
                    Especie nuevaEspecie = new Especie(nombreEspecie);
                    Individuo nuevoIndividuo = new Individuo(fecha, razon, estado, peso, comentarios);
                    nuevaEspecie.agregarIndividuo(nuevoIndividuo);
                    try {
                    	refugioSeleccionado.agregarEspecie(nuevaEspecie);
                    	guardarRefugiosSerializados(refugios);
                    	JOptionPane.showMessageDialog(null, "Especie e individuo agregados correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        
                    }catch (EspecieDuplicadaException ex) {
                    	JOptionPane.showMessageDialog(null, "No se puede ingresar especies ya existentes.", "Error", JOptionPane.ERROR_MESSAGE);
					}
                    
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "El valor de peso debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
            } 

            
        } else {
            JOptionPane.showMessageDialog(null, "El refugio seleccionado no existe.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    	
    }
    public void agregarRefugio(Refugio nuevoRefugio, List<Refugio> refugios) {
    	try {
			nuevoRefugio.agregarEspecie(new Especie("Other"));
		} catch (EspecieDuplicadaException e1) {
			e1.printStackTrace();
		}
        refugios.add(nuevoRefugio);
        guardarRefugiosSerializados(refugios);
        JOptionPane.showMessageDialog(null, "Refugio agregado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        
    }
    public JsonArray cargarProvincias(){
    	String path="C:\\Users\\Melanie\\OneDrive - Estudiantes ITCR\\POO\\Caso 2\\Caso2\\codigo\\EspeciesRefugios\\src\\files\\Provincias.json";
    	JsonArray provincias=new JsonArray();
    	try {
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(new FileReader(path), JsonObject.class);
            provincias=jsonObject.getAsJsonArray("provincias");
            return provincias;
        } catch (Exception e) {
            e.printStackTrace();
        }
    	return provincias;
    }

    
}
