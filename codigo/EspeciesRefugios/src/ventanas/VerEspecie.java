package ventanas;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import elementos.*;

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
                    especiesTexto.append("Descripción: ").append(especie.getDescripcion()).append("\n");
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

