package controladores;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import javax.swing.JOptionPane;

import elementos.*;
import exception.EspecieDuplicadaException;

public class IngresoEspecieController{
	//private List<Refugio> refugios=new ArrayList<>();
	
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
                JOptionPane.showMessageDialog(null, "El valor de peso debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
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
    

    //public static void main(String[] args) {
    	//SwingUtilities.invokeLater(() -> new IngresoEspecie());
    //}
}
/*
public class IngresoEspecieController {
    private IngresoEspecie ingresoEspecie;
    private List<Refugio> refugios = new ArrayList<>();
    private String archivo = "C:\\Users\\Melanie\\OneDrive - Estudiantes ITCR\\POO\\Caso 2\\Caso2\\codigo\\EspeciesRefugios\\src\\files\\Datos.dat";

    public void setIngresoEspecie(IngresoEspecie ingresoEspecie) {
        this.ingresoEspecie = ingresoEspecie;
        cargarRefugiosSerializados();
        cargarRefugioComboBox();
    }

    private void cargarRefugioComboBox() {
        JComboBox<String> refugioComboBox = this.ingresoEspecie.getRefugioComboBox();
        //refugioComboBox.removeAllItems();
        for (Refugio refugio : refugios) {
            refugioComboBox.addItem(refugio.getNombre());
        }
    }

    private void cargarRefugiosSerializados() {
        try (FileInputStream fis = new FileInputStream(archivo);
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            Object obj = ois.readObject();
            if (obj instanceof List) {
                refugios = (List<Refugio>) obj;
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    private void cargarRefugios() {
        DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();
        for (Refugio refugio : refugios) {
            comboBoxModel.addElement(refugio.getNombre());
        }
        ingresoEspecie.setRefugioComboBoxModel(comboBoxModel);
    }
    //public JComboBox<String> getRefugioComboBox() {
      //  return ingresoEspecie.refugioComboBox;
    //}

    public void agregarIndividuo(String nombreRefugio, String nombreEspecie, Date fecha, String razon, String estado, double peso, String comentarios) {
        // Implementa la lógica para agregar un individuo a la especie en el refugio seleccionado.
        // Debes encontrar el refugio y la especie correctos en la lista de refugios y realizar la operación.

        // Ejemplo ficticio:
        // Refugio refugioSeleccionado = buscarRefugioPorNombre(nombreRefugio);
        // Especie especieExistente = buscarEspeciePorNombre(refugioSeleccionado, nombreEspecie);
        // Individuo nuevoIndividuo = new Individuo(fecha, razon, estado, peso, comentarios);
        // especieExistente.agregarIndividuo(nuevoIndividuo);
        // ...

        // Una vez realizada la operación, guarda los refugios serializados nuevamente:
        guardarRefugiosSerializados();
    }

    public void crearEspecieConIndividuo(String nombreRefugio, String nombreEspecie, Date fecha, String razon, String estado, double peso, String comentarios) {
        // Implementa la lógica para crear una nueva especie con un individuo en el refugio seleccionado.
        // Debes encontrar el refugio correcto en la lista de refugios y realizar la operación.

        // Ejemplo ficticio:
        // Refugio refugioSeleccionado = buscarRefugioPorNombre(nombreRefugio);
        // Especie nuevaEspecie = new Especie(nombreEspecie);
        // Individuo nuevoIndividuo = new Individuo(fecha, razon, estado, peso, comentarios);
        // nuevaEspecie.agregarIndividuo(nuevoIndividuo);
        // refugioSeleccionado.agregarEspecie(nuevaEspecie);
        // ...

        // Una vez realizada la operación, guarda los refugios serializados nuevamente:
        guardarRefugiosSerializados();
    }

    private void guardarRefugiosSerializados() {
        try (FileOutputStream fos = new FileOutputStream(archivo);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            oos.writeObject(refugios);
            
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al guardar la especie.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}*/