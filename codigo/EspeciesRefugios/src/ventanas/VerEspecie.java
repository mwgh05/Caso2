package ventanas;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import elementos.*;
import javax.swing.table.DefaultTableModel;

import controladores.Controller;

public class VerEspecie extends JFrame {
    private JComboBox<String> refugioComboBox;
    private JButton verEspeciesButton;
    private JTable especiesTable;
    private DefaultTableModel tableModel;
    private Controller controller= new Controller();

    private List<Refugio> refugios=new ArrayList<>();

    public VerEspecie() {
    	this.refugios=controller.cargarRefugiosSerializados();
    	
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

        tableModel = new DefaultTableModel();
        tableModel.addColumn("Nombre de la Especie");
        especiesTable = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(especiesTable);
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

                tableModel.setRowCount(0);
                for (Especie especie : refugioSeleccionado.getEspecies()) {
                	if(!especie.getNombre().equals("Other")) {
                		tableModel.addRow(new Object[]{especie.getNombre()});
                    }
                }
            }
        });

        add(panel);

        setVisible(true);
    }
    
    public static void main(String[] args) {
    	SwingUtilities.invokeLater(() -> new VerEspecie());
    }
}
