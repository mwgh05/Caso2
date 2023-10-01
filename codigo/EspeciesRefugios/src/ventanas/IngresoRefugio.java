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
    
    private List<Provincia> provincias;
    private List<String> cantones;

    public IngresoRefugio(List<Provincia> provincias, List<String> cantones) {
        this.provincias = provincias;
        this.cantones = cantones;
        
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
        }
        panel.add(provinciaComboBox, constraints);

        constraints.gridx = 2;
        panel.add(new JLabel("Seleccione un Cantón:"), constraints);

        constraints.gridx = 3;
        cantonComboBox = new JComboBox<>();
        for (String canton : cantones) {
            cantonComboBox.addItem(canton);
        }
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

                // Resto del código para agregar el refugio

                dispose();
            }
        });

        add(panel);

        setVisible(true);
    }

    

    

    public static void main(String[] args) {
    	List<Provincia> provincias = obtenerListaDeProvincias();
    	List<String> cantones = obtenerListaDeCantones();
        SwingUtilities.invokeLater(() -> new IngresoRefugio(provincias,cantones));
    }
    private static List<Provincia> obtenerListaDeProvincias() {
        List<Provincia> provincias = new ArrayList<>();
        provincias.add(new Provincia("Provincia 1"));
        provincias.add(new Provincia("Provincia 2"));
        provincias.add(new Provincia("Provincia 3"));
        return provincias;
    }
    private static List<String> obtenerListaDeCantones() {
        List<String> cantones = new ArrayList<>();
        cantones.add("Canton A");
        cantones.add("Canton B");
        cantones.add("Canton C");
        return cantones;
    }
}


