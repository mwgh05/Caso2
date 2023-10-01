package main;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import elementos.*;
import ventanas.*;

public class Main extends JFrame {
    private JButton ingresarEspecieButton, ingresarRefugioButton, verEspeciesButton;
    private List<Refugio> refugios;
    private List<Especie> especies;
    private List<Provincia> provincias;
    private List<String> cantones;

    public Main() {
        setTitle("Gestión de Refugios y Especies");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        refugios = new ArrayList<>();
        especies = new ArrayList<>();
        provincias = new ArrayList<>();
        cantones = new ArrayList<>();
        
        ingresarEspecieButton = new JButton("Ingresar Especie");
        ingresarRefugioButton = new JButton("Ingresar Refugio");
        verEspeciesButton = new JButton("Ver Especies");

        ingresarEspecieButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirVentanaIngresarEspecie();
            }
        });

        ingresarRefugioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirVentanaIngresarRefugio();
            }
        });

        verEspeciesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirVentanaSeleccionarRefugio();
            }
        });

        JPanel panel = new JPanel(new GridLayout(3, 1));
        panel.add(ingresarEspecieButton);
        panel.add(ingresarRefugioButton);
        panel.add(verEspeciesButton);

        add(panel);

        setVisible(true);
    }

    private void abrirVentanaIngresarEspecie() {
    	//new IngresoEspecie();
        
    }

    private void abrirVentanaIngresarRefugio() {
    	//new IngresoRefugio();
        
    }

    private void abrirVentanaSeleccionarRefugio() {
    	//new VerEspecie();
            
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main());
    }
}



