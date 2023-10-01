package ventanas;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import elementos.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class VerEspecie extends JFrame {
    private JComboBox<String> refugioComboBox;
    private JTextArea especiesTextArea;
    private JButton verEspeciesButton;

    private List<Refugio> refugios; 
    private List<Especie> especies;

    public VerEspecie(List<Refugio> refugios, List<Especie> especies) {
    	this.refugios = refugios;
        this.especies = especies;
        
        setTitle("Ver Especies por Refugio");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);


        JPanel panel = new JPanel(new BorderLayout());

        JPanel comboBoxPanel = new JPanel();
        refugioComboBox = new JComboBox<>();
        for (Refugio refugio : refugios) {
            refugioComboBox.addItem(refugio.getNombre());
        }
        comboBoxPanel.add(new JLabel("Seleccione un Refugio:"));
        comboBoxPanel.add(refugioComboBox);
        panel.add(comboBoxPanel, BorderLayout.NORTH);

        especiesTextArea = new JTextArea();
        especiesTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(especiesTextArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        verEspeciesButton = new JButton("Ver Especies");
        panel.add(verEspeciesButton, BorderLayout.SOUTH);

        verEspeciesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombreRefugio = (String) refugioComboBox.getSelectedItem();

                List<Especie> especiesDelRefugio = obtenerListaDeEspecies();//obtenerEspeciesPorRefugio(nombreRefugio);

                StringBuilder especiesTexto = new StringBuilder();
                for (Especie especie : especiesDelRefugio) {
                    especiesTexto.append("Nombre: ").append(especie.getNombre()).append("\n");
                    /*especiesTexto.append("Descripción: ").append(especie.getDescripcion()).append("\n");
                    especiesTexto.append("Cantidad: ").append(especie.getCantidad()).append("\n");
                    especiesTexto.append("Hábitat: ").append(especie.getHabitat()).append("\n");*/
                    especiesTexto.append("\n");
                }
                especiesTextArea.setText(especiesTexto.toString());
            }
        });

        add(panel);

        setVisible(true);
    }

    private List<Especie> obtenerEspeciesPorRefugio(String nombreRefugio) {
        return new ArrayList<>(); 
    }

    public static void main(String[] args) {
    	List<Refugio> refugios = obtenerListaDeRefugios();
    	List<Especie> especies = obtenerListaDeEspecies();
    	SwingUtilities.invokeLater(() -> new VerEspecie(refugios,especies));
    }
    private static List<Refugio> obtenerListaDeRefugios() {
        List<Refugio> refugios = new ArrayList<>();
        refugios.add(new Refugio("Refugio 1", "Ubicacion 1", "Area 1", "XS", "y", "z"));
        refugios.add(new Refugio("Refugio 2", "Ubicacion 2", "Area 2", "XS", "y", "z"));
        refugios.add(new Refugio("Refugio 3", "Ubicacion 3", "Area 3", "XS", "y", "z"));
        return refugios;
    }
    private static List<Especie> obtenerListaDeEspecies() {
        List<Especie> especies = new ArrayList<>();
        especies.add(new Especie("Especie 1"));
        especies.add(new Especie("Especie 2"));
        especies.add(new Especie("Especie 3"));
        return especies;
    }
}

/*
public class VerEspecie extends JFrame {
    private JComboBox<String> refugioComboBox;
    private JTextArea especiesTextArea;
    private JButton verEspeciesButton;

    private List<Refugio> refugios; 

    public VerEspecie() {
        setTitle("Ver Especies por Refugio");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        refugios = new ArrayList<>(); 

        JPanel panel = new JPanel(new GridLayout(3, 1));

        refugioComboBox = new JComboBox<>();
        for (Refugio refugio : refugios) {
            refugioComboBox.addItem(refugio.getNombre());
        }
        panel.add(refugioComboBox);

        especiesTextArea = new JTextArea();
        especiesTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(especiesTextArea);
        panel.add(scrollPane);

        verEspeciesButton = new JButton("Ver Especies");
        panel.add(verEspeciesButton);

        verEspeciesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombreRefugio = (String) refugioComboBox.getSelectedItem();

                List<Especie> especiesDelRefugio = obtenerEspeciesPorRefugio(nombreRefugio);

                StringBuilder especiesTexto = new StringBuilder();
                for (Especie especie : especiesDelRefugio) {
                    especiesTexto.append("Nombre: ").append(especie.getNombre()).append("\n");
                    /*especiesTexto.append("Descripción: ").append(especie.getDescripcion()).append("\n");
                    especiesTexto.append("Cantidad: ").append(especie.getCantidad()).append("\n");
                    especiesTexto.append("Hábitat: ").append(especie.getHabitat()).append("\n");
                    especiesTexto.append("\n");
                }
                especiesTextArea.setText(especiesTexto.toString());
            }
        });

        add(panel);

        setVisible(true);
    }

    private List<Especie> obtenerEspeciesPorRefugio(String nombreRefugio) {
        return new ArrayList<>(); 
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VerEspecie());
    }
}
*/
