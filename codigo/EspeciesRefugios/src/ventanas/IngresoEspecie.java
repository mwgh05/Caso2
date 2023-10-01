package ventanas;
import elementos.*;
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

    private List<Refugio> refugios;
    private List<Especie> especies;

    public IngresoEspecie(List<Refugio> refugios, List<Especie> especies) {
        this.refugios = refugios;
        this.especies = especies;

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
        panel.add(refugioComboBox);

        panel.add(new JLabel("Seleccione una Especie:"));
        especieComboBox = new JComboBox<>();
        for (Especie especie : especies) {
            especieComboBox.addItem(especie.getNombre());
        }
        panel.add(especieComboBox);
        
        panel.add(new JLabel("Nombre de la Especie:"));
        nombreField = new JTextField();
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

        panel.add(new JLabel("Peso:"));
        pesoField = new JTextField();
        panel.add(pesoField);

        panel.add(new JLabel("Comentarios:"));
        comentariosField = new JTextField();
        panel.add(comentariosField);

        agregarButton = new JButton("Agregar");
        crearButton = new JButton("Crear Nuevo");

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(agregarButton);
        buttonPanel.add(crearButton);

        panel.add(new JLabel(""));
        panel.add(buttonPanel);

        agregarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Resto del código para agregar la especie
            }
        });

        crearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	String nombreIndividuo = nombreField.getText();
                String nombreEspecie = nombreField.getText();
                String fecha = fechaField.getText();
                String razon = razonField.getText();
                String estado = estadoField.getText();
                double peso = Double.parseDouble(pesoField.getText());
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

                    if (especieExistente != null) {
                        Individuo nuevoIndividuo = new Individuo(nombreIndividuo, fecha, razon, estado, peso, comentarios);
                        especieExistente.agregarIndividuo(nuevoIndividuo);
                        JOptionPane.showMessageDialog(null, "Individuo agregado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        int opcion = JOptionPane.showConfirmDialog(null, "La especie seleccionada no existe en el refugio. ¿Desea agregarla como nueva especie?", "Especie no encontrada", JOptionPane.YES_NO_OPTION);
                        if (opcion == JOptionPane.YES_OPTION) {
                            Especie nuevaEspecie = new Especie(nombreEspecie);
                            Individuo nuevoIndividuo = new Individuo(nombreIndividuo, fecha, razon, estado, peso, comentarios);
                            nuevaEspecie.agregarIndividuo(nuevoIndividuo);
                            refugioSeleccionado.agregarEspecie(nuevaEspecie);
                            JOptionPane.showMessageDialog(null, "Especie y individuo agregados correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }

                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "El refugio seleccionado no existe.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        add(panel);

        setVisible(true);
    }
    public static void main(String[] args) {
    	List<Refugio> refugios = obtenerListaDeRefugios();
    	List<Especie> especies = obtenerListaDeEspecies();
    	refugios.add(new Refugio("Refugio A", "Ubicacion A", "Area A", "x"));
        refugios.add(new Refugio("Refugio B", "Ubicacion B", "Area B", "X"));
        SwingUtilities.invokeLater(() -> new IngresoEspecie(refugios,especies));
    }
    private static List<Refugio> obtenerListaDeRefugios() {
        List<Refugio> refugios = new ArrayList<>();
        refugios.add(new Refugio("Refugio 1", "Ubicacion 1", "Area 1", "XS"));
        refugios.add(new Refugio("Refugio 2", "Ubicacion 2", "Area 2", "XS"));
        refugios.add(new Refugio("Refugio 3", "Ubicacion 3", "Area 3", "XS"));
        return refugios;
    }
    private static List<Especie> obtenerListaDeEspecies() {
        List<Especie> especies = new ArrayList<>();
        especies.add(new Especie("Especie 1"));
        especies.add(new Especie("Especie 2"));
        especies.add(new Especie("Especie 3"));
        especies.add(new Especie("Other"));
        return especies;
    }
}

