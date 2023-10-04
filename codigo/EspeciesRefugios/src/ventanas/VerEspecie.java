package ventanas;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import elementos.*;
import exception.EspecieDuplicadaException;

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

    private List<Refugio> refugios=new ArrayList<>();; 
    private List<Especie> especies;

    public VerEspecie() {//List<Refugio> refugios, List<Especie> especies) {
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
                Refugio refugioSeleccionado = null;
                for (Refugio refugio : refugios) {
                    if (refugio.getNombre().equals(nombreRefugio)) {
                        refugioSeleccionado = refugio;
                        break;
                    }
                }

                StringBuilder especiesTexto = new StringBuilder();
                for (Especie especie : refugioSeleccionado.getEspecies()) {
                	if(especie.getNombre()!="Other") {
                    especiesTexto.append("Nombre: ").append(especie.getNombre()).append("\n");
                    especiesTexto.append("\n");
                	}
                }
                especiesTextArea.setText(especiesTexto.toString());
            }
        });

        add(panel);

        setVisible(true);
    }

    public static void main(String[] args) {
    	SwingUtilities.invokeLater(() -> new VerEspecie());
    }
}
