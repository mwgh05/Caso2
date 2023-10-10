package ventanas;
import javax.swing.*;

import elementos.*;
import exception.EspecieDuplicadaException;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.List;
import java.util.ArrayList;



public class IngresoRefugio extends JFrame {
    private JTextField nombreField, areaField, horarioField, senasField;
    private JComboBox<String> provinciaComboBox;
    private JComboBox<String> cantonComboBox;
    private JButton agregarButton;
    
    private List<Refugio> refugios = new ArrayList<>();
    private List<Provincia> provincias=new ArrayList<>();
    private List<String> cantones;

    public IngresoRefugio() {
    	cargarRefugiosSerializados();
    	Provincia Prov1=new Provincia("Guanacaste");
    	Provincia Prov2=new Provincia("Puntarenas");
    	Provincia Prov3=new Provincia("Limon");
    	Provincia Prov4=new Provincia("Alajuela");
    	Provincia Prov5=new Provincia("San Jose");
    	Provincia Prov6=new Provincia("Cartago");
    	Provincia Prov7=new Provincia("Heredia");
    	String cant1="Canton A";
    	String cant2="Canton B";
    	String cant3="Canton C";
    	Prov1.agregarCanton(cant1);
    	Prov1.agregarCanton(cant2);
    	Prov2.agregarCanton(cant2);
    	Prov2.agregarCanton(cant3);
    	Prov3.agregarCanton(cant3);
    	Prov3.agregarCanton(cant1);
    	provincias.add(Prov1);
        provincias.add(Prov2);
        provincias.add(Prov3);
        
        setTitle("Ingreso de Refugio");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout()); 
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(5, 5, 5, 5);

        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(new JLabel("Nombre del Refugio:"), constraints);

        constraints.gridx = 1;
        constraints.gridwidth = 3;
        nombreField = new JTextField();
        panel.add(nombreField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        panel.add(new JLabel("Área:"), constraints);

        constraints.gridx = 1;
        areaField = new JTextField();
        panel.add(areaField, constraints);

        constraints.gridx = 2;
        panel.add(new JLabel("Horario:"), constraints);

        constraints.gridx = 3;
        horarioField = new JTextField();
        panel.add(horarioField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        panel.add(new JLabel("Ubicación:"), constraints);

        constraints.gridy = 3;
        constraints.gridwidth = 1;
        panel.add(new JLabel("Seleccione una Provincia:"), constraints);

        constraints.gridx = 1;
        provinciaComboBox = new JComboBox<>();
        for (Provincia provincia : provincias) {
            provinciaComboBox.addItem(provincia.getNombre());
            provinciaComboBox.addActionListener(new ActionListener() {           
                public void actionPerformed(ActionEvent e) {
                    String provinciaSeleccionada = (String) provinciaComboBox.getSelectedItem();

                    cantonComboBox.removeAllItems();

                    for (Provincia provincia : provincias) {
                        if (provincia.getNombre().equals(provinciaSeleccionada)) {
                            for (String canton : provincia.getCantones()) {
                                cantonComboBox.addItem(canton);
                            }
                            break; 
                        }
                    }
                }
            });
        }
        panel.add(provinciaComboBox, constraints);

        constraints.gridx = 2;
        panel.add(new JLabel("Seleccione un Cantón:"), constraints);

        constraints.gridx = 3;
        cantonComboBox = new JComboBox<>();
        
        panel.add(cantonComboBox, constraints);

        constraints.gridy = 4;
        constraints.gridx = 0;
        constraints.gridwidth = 1;
        panel.add(new JLabel("Señas:"), constraints);

        constraints.gridx = 1;
        constraints.gridwidth = 3;
        senasField = new JTextField();
        panel.add(senasField, constraints);

        constraints.gridy = 5;
        constraints.gridx = 0;
        constraints.gridwidth = 4;
        agregarButton = new JButton("Agregar");
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(agregarButton);
        
        panel.add(new JLabel(""));
        panel.add(buttonPanel, constraints);

        agregarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombre = nombreField.getText();
                String area = areaField.getText();
                String horario = horarioField.getText();
                String provincia = (String) provinciaComboBox.getSelectedItem();
                String canton = (String) cantonComboBox.getSelectedItem();
                String senas = senasField.getText();

                Refugio nuevoRefugio=new Refugio(nombre,provincia,canton,senas,area,horario);
                
                try {
					nuevoRefugio.agregarEspecie(new Especie("Other"));
				} catch (EspecieDuplicadaException e1) {
					e1.printStackTrace();
				}
                refugios.add(nuevoRefugio);
                guardarRefugiosSerializados();
                //JOptionPane.showMessageDialog(null, "Refugio agregado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                
                dispose();
            }
        });

        add(panel);

        setVisible(true);
    }
    String archivo = "C:\\Users\\Melanie\\OneDrive - Estudiantes ITCR\\POO\\Caso 2\\Caso2\\codigo\\EspeciesRefugios\\src\\files\\Datos.dat";

    private void guardarRefugiosSerializados() {
        try (FileOutputStream fos = new FileOutputStream(archivo);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            oos.writeObject(refugios);
            JOptionPane.showMessageDialog(null, "Refugios guardados correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al guardar los refugios.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
    	SwingUtilities.invokeLater(() -> new IngresoRefugio());
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
}

