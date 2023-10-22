package ventanas;
import javax.swing.*;

import controladores.Controller;
import elementos.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;




public class IngresoRefugio extends JFrame {
	private Controller controller= new Controller();
    private JTextField nombreField, areaField, horarioField, senasField;
    private JComboBox<String> provinciaComboBox;
    private JComboBox<String> cantonComboBox;
    private JButton agregarButton;
    
    private List<Refugio> refugios = new ArrayList<>();
    private JsonArray provincias=new JsonArray();

    public IngresoRefugio() {
    	this.refugios=controller.cargarRefugiosSerializados();
    	this.provincias=controller.cargarProvincias();
    	
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
        for (int i = 0; i < provincias.size(); i++) {
            JsonObject provinciaObject = provincias.get(i).getAsJsonObject();
            String nombreProvincia = provinciaObject.get("provincia").getAsString();
            
            provinciaComboBox.addItem(nombreProvincia);
        }
        provinciaComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String provinciaSeleccionada = (String) provinciaComboBox.getSelectedItem();

                for (JsonElement elemento : provincias) {
                    JsonObject provinciaObjeto = elemento.getAsJsonObject();
                    String nombreProvincia = provinciaObjeto.get("provincia").getAsString();

                    if (nombreProvincia.equals(provinciaSeleccionada)) {
                        JsonArray cantones = provinciaObjeto.getAsJsonArray("cantones");
                        cantonComboBox.removeAllItems(); 
                        
                        for (JsonElement canton : cantones) {
                            cantonComboBox.addItem(canton.getAsString());
                        }

                        break;
                    }
                }
            }
        });
        
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
                controller.agregarRefugio(nuevoRefugio, refugios);
                
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

