package ventanas;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;
import elementos.*;

public class IngresoEspecie extends JFrame {
    private JTextField nombreField, descripcionField, cantidadField, habitatField;
    private JComboBox<String> refugioComboBox;
    private JButton agregarButton;

    private List<Refugio> refugios; 

    public IngresoEspecie(List<Refugio> refugios) {
        this.refugios = refugios;

        setTitle("Ingreso de Especie");
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(6, 2));
        panel.add(new JLabel("Nombre:"));
        nombreField = new JTextField();
        panel.add(nombreField);
        panel.add(new JLabel("Descripción:"));
        descripcionField = new JTextField();
        panel.add(descripcionField);
        panel.add(new JLabel("Cantidad:"));
        cantidadField = new JTextField();
        panel.add(cantidadField);
        panel.add(new JLabel("Hábitat:"));
        habitatField = new JTextField();
        panel.add(habitatField);

        panel.add(new JLabel("Seleccione un Refugio:"));
        refugioComboBox = new JComboBox<>();
        for (Refugio refugio : refugios) {
            refugioComboBox.addItem(refugio.getNombre());
        }
        panel.add(refugioComboBox);

        agregarButton = new JButton("Agregar");
        panel.add(agregarButton);

        agregarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombre = nombreField.getText();
                String descripcion = descripcionField.getText();
                int cantidad = Integer.parseInt(cantidadField.getText());
                String habitat = habitatField.getText();
                String nombreRefugio = (String) refugioComboBox.getSelectedItem();

                Especie nuevaEspecie = new Especie(nombre, descripcion, cantidad, habitat);

                for (Refugio refugio : refugios) {
                    if (refugio.getNombre().equals(nombreRefugio)) {
                        refugio.agregarEspecie(nuevaEspecie);
                        break; 
                    }
                }

                dispose();
            }
        });

        add(panel);

        setVisible(true);
    }

    public static void main(String[] args) {
    	List<Refugio> refugios = obtenerListaDeRefugios();
    	refugios.add(new Refugio("Refugio A", "Ubicación A", "Área A"));
        refugios.add(new Refugio("Refugio B", "Ubicación B", "Área B"));
        SwingUtilities.invokeLater(() -> new IngresoEspecie(refugios));
    }
    private static List<Refugio> obtenerListaDeRefugios() {
        List<Refugio> refugios = new ArrayList<>();
        refugios.add(new Refugio("Refugio 1", "Ubicación 1", "Área 1"));
        refugios.add(new Refugio("Refugio 2", "Ubicación 2", "Área 2"));
        refugios.add(new Refugio("Refugio 3", "Ubicación 3", "Área 3"));
        return refugios;
    }
}
