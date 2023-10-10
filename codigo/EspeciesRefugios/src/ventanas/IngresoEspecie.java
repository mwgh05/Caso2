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
	private IngresoEspecieController controller= new IngresoEspecieController();
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
                //Refugio refugioSeleccionado = null;
                String ppeso=pesoField.getText();
                controller.agregarEspecie(refugios, nombreRefugio, nombreEspecie, fecha, razon, estado, comentarios, ppeso);
                /*
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
                	
                    try {
                        peso = Double.parseDouble(pesoField.getText());
                    
                    
                    	Individuo nuevoIndividuo = new Individuo(fecha, razon, estado, peso, comentarios);
                        especieExistente.agregarIndividuo(nuevoIndividuo);
                        controller.guardarRefugiosSerializados(refugios);
                        JOptionPane.showMessageDialog(null, "Individuo agregado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                 
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "El valor de peso debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }else {
                    JOptionPane.showMessageDialog(null, "El refugio seleccionado no existe.", "Error", JOptionPane.ERROR_MESSAGE);
                }   

                    
             */ 
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
                
                /*double peso=0;

                Refugio refugioSeleccionado = null;
                for (Refugio refugio : refugios) {
                    if (refugio.getNombre().equals(nombreRefugio)) {
                        refugioSeleccionado = refugio;
                        break;
                    }
                }

                if (refugioSeleccionado != null) {
                    
                    try {
                        peso = Double.parseDouble(pesoField.getText());                                  
                    
                            Especie nuevaEspecie = new Especie(nombreEspecie);
                            Individuo nuevoIndividuo = new Individuo(fecha, razon, estado, peso, comentarios);
                            nuevaEspecie.agregarIndividuo(nuevoIndividuo);
                            try {
                            	refugioSeleccionado.agregarEspecie(nuevaEspecie);
                            	controller.guardarRefugiosSerializados(refugios);
                            	JOptionPane.showMessageDialog(null, "Especie e individuo agregados correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                                
                            }catch (EspecieDuplicadaException ex) {
                            	JOptionPane.showMessageDialog(null, "No se puede ingresar especies ya existentes.", "Error", JOptionPane.ERROR_MESSAGE);
        					}
                            
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "El valor de peso debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
                    } 

                    
                } else {
                    JOptionPane.showMessageDialog(null, "El refugio seleccionado no existe.", "Error", JOptionPane.ERROR_MESSAGE);
                }
             */
                dispose();
            }
        });

        

        add(panel);

        setVisible(true);
    }/*
    private void guardarRefugiosSerializados() {
        try (FileOutputStream fos = new FileOutputStream(archivo);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            oos.writeObject(refugios);
            
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al guardar la especie.", "Error", JOptionPane.ERROR_MESSAGE);
        }
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
    }*/

    public static void main(String[] args) {
    	SwingUtilities.invokeLater(() -> new IngresoEspecie());
    }
}

/*
public class IngresoEspecie extends JFrame {
    private IngresoEspecieController controller;
    private JComboBox<String> refugioComboBox, especieComboBox;
    private JLabel nombreLabel;
    private JTextField nombreField;
    private JButton agregarButton, crearButton;
    //private List<Refugio> refugios;

    
    
    public JComboBox<String> getRefugioComboBox() {
        return refugioComboBox;
    }
    public void setRefugioComboBoxModel(DefaultComboBoxModel<String> model) {
        refugioComboBox.setModel(model);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            IngresoEspecieController controller = new IngresoEspecieController();
            //IngresoEspecie ingresoEspecie = new IngresoEspecie(controller);
            //ingresoEspecie.initialize();
            SwingUtilities.invokeLater(() -> new IngresoEspecie(controller));
        });
    }

    public IngresoEspecie(IngresoEspecieController controller) {
        this.controller = controller;
        controller.setIngresoEspecie(this);
        setTitle("Ingreso de Especie");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JPanel panel = new JPanel(new GridLayout(9, 2, 10, 10));
        panel.add(new JLabel("Seleccione un Refugio:"));
        refugioComboBox = new JComboBox<>();
        /*for (Refugio refugio : refugios) {
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

        JLabel selecLabel = new JLabel("Seleccione una Especie:");
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
        panel.add(nombreLabel);


        nombreField = new JTextField();
        panel.add(nombreField);

        panel.add(new JLabel("Fecha de Ingreso:"));

        UtilDateModel model = new UtilDateModel();
        JDatePanelImpl datePanel = new JDatePanelImpl(model, new Properties());
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        panel.add(datePicker);

        panel.add(new JLabel("Razón de Ingreso:"));
        JTextField razonField = new JTextField();
        panel.add(razonField);

        panel.add(new JLabel("Estado:"));
        JTextField estadoField = new JTextField();
        panel.add(estadoField);

        panel.add(new JLabel("Peso (en gramos):"));
        JTextField pesoField = new JTextField();
        panel.add(pesoField);

        panel.add(new JLabel("Comentarios:"));
        JTextField comentariosField = new JTextField();
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
                String nombreEspecie = (String) especieComboBox.getSelectedItem();
                Date fecha = (Date) datePicker.getModel().getValue();
                String razon = razonField.getText();
                String estado = estadoField.getText();
                String comentarios = comentariosField.getText();
                String nombreRefugio = (String) refugioComboBox.getSelectedItem();
                double peso = 0;

                controller.agregarIndividuo(nombreRefugio, nombreEspecie, fecha, razon, estado, peso, comentarios);
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
                double peso = 0;

                controller.crearEspecieConIndividuo(nombreRefugio, nombreEspecie, fecha, razon, estado, peso, comentarios);
            }
        });

        add(panel);
        setVisible(true);
    }
}




public class IngresoEspecie extends JFrame {
	
    private JComboBox<String> refugioComboBox;
    private JComboBox<String> especieComboBox;
    private JTextField nombreField, razonField, estadoField, pesoField, comentariosField;
    private JButton agregarButton, crearButton;
    private JLabel selecLabel, nombreLabel;

    private List<Refugio> refugios=new ArrayList<>();;

    String archivo = "C:\\Users\\Melanie\\OneDrive - Estudiantes ITCR\\POO\\Caso 2\\Caso2\\codigo\\EspeciesRefugios\\src\\files\\Datos.dat";

    public IngresoEspecie() {
    	cargarRefugiosSerializados();
    	
    
        

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
                
                double peso=0;
            	String nombreEspecie = (String) especieComboBox.getSelectedItem();
            	Date fecha = (Date) datePicker.getModel().getValue();
                String razon = razonField.getText();
                String estado = estadoField.getText();
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
                	
                    try {
                        peso = Double.parseDouble(pesoField.getText());
                    
                    
                    	Individuo nuevoIndividuo = new Individuo(fecha, razon, estado, peso, comentarios);
                        especieExistente.agregarIndividuo(nuevoIndividuo);
                        guardarRefugiosSerializados();
                        JOptionPane.showMessageDialog(null, "Individuo agregado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                 
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "El valor de peso debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }else {
                    JOptionPane.showMessageDialog(null, "El refugio seleccionado no existe.", "Error", JOptionPane.ERROR_MESSAGE);
                }   

                    
                 
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
                double peso=0;

                Refugio refugioSeleccionado = null;
                for (Refugio refugio : refugios) {
                    if (refugio.getNombre().equals(nombreRefugio)) {
                        refugioSeleccionado = refugio;
                        break;
                    }
                }

                if (refugioSeleccionado != null) {
                    
                    try {
                        peso = Double.parseDouble(pesoField.getText());                                  
                    
                            Especie nuevaEspecie = new Especie(nombreEspecie);
                            Individuo nuevoIndividuo = new Individuo(fecha, razon, estado, peso, comentarios);
                            nuevaEspecie.agregarIndividuo(nuevoIndividuo);
                            try {
                            	refugioSeleccionado.agregarEspecie(nuevaEspecie);
                            	guardarRefugiosSerializados();
                            	JOptionPane.showMessageDialog(null, "Especie e individuo agregados correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                                
                            }catch (EspecieDuplicadaException ex) {
                            	JOptionPane.showMessageDialog(null, "No se puede ingresar especies ya existentes.", "Error", JOptionPane.ERROR_MESSAGE);
        					}
                            
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "El valor de peso debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
                    } 

                    
                } else {
                    JOptionPane.showMessageDialog(null, "El refugio seleccionado no existe.", "Error", JOptionPane.ERROR_MESSAGE);
                }
             
                dispose();
            }
        });

        

        add(panel);

        setVisible(true);
    }
    private void guardarRefugiosSerializados() {
        try (FileOutputStream fos = new FileOutputStream(archivo);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            oos.writeObject(refugios);
            
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al guardar la especie.", "Error", JOptionPane.ERROR_MESSAGE);
        }
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

    public static void main(String[] args) {
    	SwingUtilities.invokeLater(() -> new IngresoEspecie());
    }
}*/
    