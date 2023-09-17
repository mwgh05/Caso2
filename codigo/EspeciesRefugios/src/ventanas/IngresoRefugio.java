package ventanas;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IngresoRefugio extends JFrame {
    private JTextField nombreField, ubicacionField, areaField;
    private JButton agregarButton;

    public IngresoRefugio() {
        setTitle("Ingreso de Refugio");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(4, 2));
        panel.add(new JLabel("Nombre:"));
        nombreField = new JTextField();
        panel.add(nombreField);
        panel.add(new JLabel("Ubicación:"));
        ubicacionField = new JTextField();
        panel.add(ubicacionField);
        panel.add(new JLabel("Área:"));
        areaField = new JTextField();
        panel.add(areaField);

        agregarButton = new JButton("Agregar");
        panel.add(agregarButton);

        agregarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombre = nombreField.getText();
                String ubicacion = ubicacionField.getText();
                String area = areaField.getText();

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
