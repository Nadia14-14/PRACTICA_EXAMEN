/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class LineaProduccionGUI {
    private JFrame frame;
    private JPanel bandaPanel;
    private JPanel panelCamiones;
    private JLabel contadorIngresados;
    private JLabel contadorEmpaquetados;
    private ArrayList<JLabel> contadorCamiones;

    public LineaProduccionGUI() {
        frame = new JFrame("Línea de Producción");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(null);

        // Banda transportadora
        bandaPanel = new JPanel();
        bandaPanel.setBounds(50, 100, 400, 50);
        bandaPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        frame.add(bandaPanel);

        JLabel bandaLabel = new JLabel("Banda Transportadora");
        bandaLabel.setBounds(50, 50, 200, 20);
        frame.add(bandaLabel);

        // Panel de camiones
        panelCamiones = new JPanel();
        panelCamiones.setBounds(500, 100, 200, 400);
        panelCamiones.setLayout(new GridLayout(5, 1, 10, 10));
        frame.add(panelCamiones);

        contadorCamiones = new ArrayList<>();
        String[] colores = {"Rojo", "Verde", "Azul", "Amarillo", "Rosa"};
        for (int i = 0; i < 5; i++) {
            JLabel camion = new JLabel("0/20");
            camion.setOpaque(true);
            camion.setHorizontalAlignment(SwingConstants.CENTER);
            camion.setBackground(Color.decode(getColorHex(colores[i])));
            panelCamiones.add(camion);
            contadorCamiones.add(camion);
        }

        // Contadores generales
        contadorIngresados = new JLabel("Artículos ingresados: 0");
        contadorIngresados.setBounds(50, 200, 200, 20);
        frame.add(contadorIngresados);

        contadorEmpaquetados = new JLabel("Artículos empaquetados: 0");
        contadorEmpaquetados.setBounds(50, 250, 200, 20);
        frame.add(contadorEmpaquetados);

        frame.setVisible(true);
    }

    public JPanel getBandaPanel() {
        return bandaPanel;
    }

    public JPanel getPanelCamiones() {
        return panelCamiones;
    }

    public JLabel getContadorIngresados() {
        return contadorIngresados;
    }

    public JLabel getContadorEmpaquetados() {
        return contadorEmpaquetados;
    }

    public ArrayList<JLabel> getContadorCamiones() {
        return contadorCamiones;
    }

    private String getColorHex(String color) {
        switch (color.toLowerCase()) {
            case "rojo":
                return "#FF0000";
            case "verde":
                return "#00FF00";
            case "azul":
                return "#0000FF";
            case "amarillo":
                return "#FFFF00";
            case "rosa":
                return "#FFC0CB";
            default:
                return "#FFFFFF";
        }
    }

    // Método para resetear los camiones a 0/20
    public void resetearCamiones() {
        for (int i = 0; i < contadorCamiones.size(); i++) {
            JLabel camion = contadorCamiones.get(i);
            camion.setText("0/20");  // Reiniciar los camiones vacíos
            camion.setBackground(Color.decode(getColorHex(new String[]{"Rojo", "Verde", "Azul", "Amarillo", "Rosa"}[i]))); // Restaurar color original
        }

        // Revalidar y repintar el panel para actualizar la interfaz
        panelCamiones.revalidate();
        panelCamiones.repaint();
    }
}
