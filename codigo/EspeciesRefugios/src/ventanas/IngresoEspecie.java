package ventanas;
import elementos.*;
//import exception.EspecieDuplicadaException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;
import java.util.Properties;
import org.jdatepicker.impl.*;
import java.util.Date;
import files.DateLabelFormatter;
//import java.io.*;
import controladores.*;
public class IngresoEspecie extends JFrame {
	private Controller controller= new Controller();
    private JComboBox<String> refugioComboBox;
    private JComboBox<String> especieComboBox;
    private JTextField nombreField, razonField, estadoField, pesoField, comentariosField;
    private JButton agregarButton, crearButton;
    private JLabel selecLabel, nombreLabel;

    private List<Refugio> refugios=new ArrayList<>();;

    String archivo = "C:\\Users\\Melanie\\OneDrive - Estudiantes ITCR\\POO\\Caso 2\\Caso2\\codigo\\EspeciesRefugios\\src\\files\\Datos.dat";

    public IngresoEspecie() {
    	this.refugios=controller.cargarRefugiosSerializados();
    	
    
        

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
        
        selecLabel=new JLabel("Seleccione una Especie:");
        panel.add(selecLabel);
        
        especieComboBox = new JComboBox<>();
        especieComboBox.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e) {
        		String especieSeleccionada = (String) especieComboBox.getSelectedItem();
        		if(especieSeleccionada.equals("Other")) {
        			int opcion = JOptionPane.showConfirmDialog(null, "¿Desea agregar una nueva especie?", "Especie no encontrada", JOptionPane.YES_NO_OPTION);
                    if (opcion == JOptionPane.YES_OPTION) {
                    	nombreLabel.setVisible(true);
                    	nombreField.setVisible(true);
                    	crearButton.setVisible(true);
                    	agregarButton.setVisible(false);
                    	especieComboBox.setVisible(false);
                    	selecLabel.setVisible(false);
                    	
                       }else {
                    	   especieComboBox.removeAllItems();
                       }
        			
        		}
        	}
        	
        });
        
        
        panel.add(especieComboBox);
        
        nombreLabel = new JLabel("Nombre de la Especie:");
        nombreLabel.setVisible(false);
        nombreField = new JTextField();
        nombreField.setVisible(false);
        panel.add(nombreLabel);
        panel.add(nombreField);


        panel.add(new JLabel("Fecha de Ingreso:"));
        
        UtilDateModel model = new UtilDateModel();
      Properties p = new Properties();
      p.put("text.today", "Today");
      p.put("text.month", "Month");
      p.put("text.year", "Year");
      JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
      JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
      
      panel.add(datePicker);
      
  	

        
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
                
                //double peso=0;
            	String nombreEspecie = (String) especieComboBox.getSelectedItem();
            	Date fecha = (Date) datePicker.getModel().getValue();
                String razon = razonField.getText();
                String estado = estadoField.getText();
                String comentarios = comentariosField.getText();
                String nombreRefugio = (String) refugioComboBox.getSelectedItem();
                String ppeso=pesoField.getText();
                controller.agregarEspecie(refugios, nombreRefugio, nombreEspecie, fecha, razon, estado, comentarios, ppeso);
                 
                dispose();
            }
        });
        crearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            	String nombreEspecie = nombreField.getText();
                
            	Date fecha = (Date) datePicker.getModel().getValue();
                String razon = razonField.getText();
                String estado = estadoField.getText();
                String comentarios = comentariosField.getText();
                String nombreRefugio = (String) refugioComboBox.getSelectedItem();
                String ppeso=pesoField.getText();
                controller.crearEspecie(refugios, nombreRefugio, nombreEspecie, fecha, razon, estado, comentarios, ppeso);
                
                
                dispose();
            }
        });

        

        add(panel);

        setVisible(true);
    }

    public static void main(String[] args) {
    	SwingUtilities.invokeLater(() -> new IngresoEspecie());
    }
    
    public static void pesoInvalido() {
    	JOptionPane.showMessageDialog(null, "El valor de peso debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}


    