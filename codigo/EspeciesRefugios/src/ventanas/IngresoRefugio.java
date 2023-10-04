package ventanas;
import javax.swing.*;

import elementos.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;



public class IngresoRefugio extends JFrame {
    private JTextField nombreField, areaField, horarioField, senasField;
    private JComboBox<String> provinciaComboBox;
    private JComboBox<String> cantonComboBox;
    private JButton agregarButton;
    
    private List<Provincia> provincias=new ArrayList<>();
    private List<String> cantones;

    public IngresoRefugio() {
    	Provincia Prov1=new Provincia("Provincia 1");
    	Provincia Prov2=new Provincia("Provincia 2");
    	Provincia Prov3=new Provincia("Provincia 3");
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
                //agregar al json
                JOptionPane.showMessageDialog(null, "Refugio agregado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

                dispose();
            }
        });

        add(panel);

        setVisible(true);
    }

    public static void main(String[] args) {
    	SwingUtilities.invokeLater(() -> new IngresoRefugio());
    }
}

