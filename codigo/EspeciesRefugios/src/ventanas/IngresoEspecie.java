package ventanas;
import elementos.*;
import exception.EspecieDuplicadaException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;

public class IngresoEspecie extends JFrame {
    private JComboBox<String> refugioComboBox;
    private JComboBox<String> especieComboBox;
    private JTextField nombreField, fechaField, razonField, estadoField, pesoField, comentariosField;
    private JButton agregarButton, crearButton;

    private List<Refugio> refugios=new ArrayList<>();;
    private List<Especie> especies;

    public IngresoEspecie() {
    	
    	Especie uno=new Especie("Especie 1");
        Especie dos=new Especie("Especie 2");
        Especie tres=new Especie("Especie 3");
        Especie other=new Especie("Other");
        Refugio Uno=new Refugio("Refugio 1", "Ubicacion 1", "Area 1", "XS", "y", "z");
        Refugio Dos=new Refugio("Refugio 2", "Ubicacion 2", "Area 2", "XS", "y", "z");
        Refugio Tres=new Refugio("Refugio 3", "Ubicacion 3", "Area 3", "XS", "y", "z");
        try{
        	Uno.agregarEspecie(uno);
        	Uno.agregarEspecie(dos);
        	Dos.agregarEspecie(dos);
        	Dos.agregarEspecie(tres);
        	Tres.agregarEspecie(tres);
        	Tres.agregarEspecie(uno);
        	Uno.agregarEspecie(other);
        	Dos.agregarEspecie(other);
        	Tres.agregarEspecie(other);
        }catch(EspecieDuplicadaException ex) {
        	JOptionPane.showMessageDialog(null, "No se puede ingresar especies ya existentes.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        this.refugios.add(Uno);
        this.refugios.add(Dos);
        this.refugios.add(Tres);
        
    
        

        setTitle("Ingreso de Especie");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(9, 2, 10, 10)); 
        panel.add(new JLabel("Seleccione un Refugio:"));
        refugioComboBox = new JComboBox<>();
        for (Refugio refugio : refugios) {
            refugioComboBox.addItem(refugio.getNombre());
        }
        refugioComboBox.addActionListener(new ActionListener() {           
            public void actionPerformed(ActionEvent e) {
                String refugioSeleccionado = (String) refugioComboBox.getSelectedItem();

                especieComboBox.removeAllItems();

                for (Refugio refugio : refugios) {
                    if (refugio.getNombre().equals(refugioSeleccionado)) {
                        for (Especie especie : refugio.getEspecies()) {
                            especieComboBox.addItem(especie.getNombre());
                        }
                        break; 
                    }
                }
            }
        });

        panel.add(refugioComboBox);
        
        JLabel selecLabel=new JLabel("Seleccione una Especie:");
        panel.add(selecLabel);
        especieComboBox = new JComboBox<>();
        
        panel.add(especieComboBox);
        
        JLabel nombreLabel = new JLabel("Nombre de la Especie:");
        nombreLabel.setVisible(false);
        nombreField = new JTextField();
        nombreField.setVisible(false);
        panel.add(nombreLabel);
        panel.add(nombreField);

        panel.add(new JLabel("Fecha de Ingreso:"));
        fechaField = new JTextField();
        panel.add(fechaField);

        panel.add(new JLabel("Razón de Ingreso:"));
        razonField = new JTextField();
        panel.add(razonField);

        panel.add(new JLabel("Estado:"));
        estadoField = new JTextField();
        panel.add(estadoField);

        panel.add(new JLabel("Peso (en gramos):"));
        pesoField = new JTextField();
        panel.add(pesoField);

        panel.add(new JLabel("Comentarios:"));
        comentariosField = new JTextField();
        panel.add(comentariosField);

        agregarButton = new JButton("Agregar");
        crearButton = new JButton("Crear Nuevo");
        crearButton.setVisible(false);
        

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(agregarButton);
        buttonPanel.add(crearButton);
        

        panel.add(new JLabel(""));
        panel.add(buttonPanel);
        
        
        
        

        agregarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                double peso=0;
            	String nombreEspecie = (String) especieComboBox.getSelectedItem();
                String fecha = fechaField.getText();
                String razon = razonField.getText();
                String estado = estadoField.getText();
                String comentarios = comentariosField.getText();
                String nombreRefugio = (String) refugioComboBox.getSelectedItem();
                Refugio refugioSeleccionado = null;
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
                        peso = Double.parseDouble(pesoField.getText());
                    
                    if (nombreEspecie == "Other") {
                    	int opcion = JOptionPane.showConfirmDialog(null, "La especie seleccionada no existe en el refugio. ¿Desea agregar una nueva especie?", "Especie no encontrada", JOptionPane.YES_NO_OPTION);
                        if (opcion == JOptionPane.YES_OPTION) {
                        	nombreLabel.setVisible(true);
                        	nombreField.setVisible(true);
                        	crearButton.setVisible(true);
                        	agregarButton.setVisible(false);
                        	especieComboBox.setVisible(false);
                        	selecLabel.setVisible(false);
                        	
                           }else {
                        	   dispose();
                           }
                    	
                    } else {
                    	Individuo nuevoIndividuo = new Individuo(fecha, razon, estado, peso, comentarios);
                        especieExistente.agregarIndividuo(nuevoIndividuo);
                        JOptionPane.showMessageDialog(null, "Individuo agregado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                    }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "El valor de peso debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }else {
                    JOptionPane.showMessageDialog(null, "El refugio seleccionado no existe.", "Error", JOptionPane.ERROR_MESSAGE);
                }   

                    
                 
            }
        });
        crearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            	String nombreEspecie = nombreField.getText();
                
                String fecha = fechaField.getText();
                String razon = razonField.getText();
                String estado = estadoField.getText();
                String comentarios = comentariosField.getText();
                String nombreRefugio = (String) refugioComboBox.getSelectedItem();
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
                        peso = Double.parseDouble(pesoField.getText());                                  
                    
                            Especie nuevaEspecie = new Especie(nombreEspecie);
                            Individuo nuevoIndividuo = new Individuo(fecha, razon, estado, peso, comentarios);
                            nuevaEspecie.agregarIndividuo(nuevoIndividuo);
                            try {
                            	refugioSeleccionado.agregarEspecie(nuevaEspecie);
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
             
                dispose();
            }
        });

        

        add(panel);

        setVisible(true);
    }
    public static void main(String[] args) {
    	SwingUtilities.invokeLater(() -> new IngresoEspecie());
    }
}
    