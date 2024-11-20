/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package HILOS;
import GUI.LineaProduccionGUI;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.atomic.AtomicInteger;

public class LineaProduccionHilos extends Thread {
    private LineaProduccionGUI gui;
    private AtomicInteger contadorIngresados, contadorEmpaquetados;
    private int camionActual = 0;
    private int[] camionesLlenos = new int[5]; // Array para contar cuántos camiones están llenos

    public LineaProduccionHilos(LineaProduccionGUI gui) {
        this.gui = gui;
        this.contadorIngresados = new AtomicInteger(0);
        this.contadorEmpaquetados = new AtomicInteger(0);
    }

    public void run() {
        boolean continuar = true;
        while (continuar) {
            try {
                // Verificar si todos los camiones están llenos
                if (camionesLlenos[0] == 20 && camionesLlenos[1] == 20 && camionesLlenos[2] == 20 && camionesLlenos[3] == 20 && camionesLlenos[4] == 20) {
                    JOptionPane.showMessageDialog(null, "Todos los camiones están llenos. El proceso ha finalizado.");
                    // Volver a poner los camiones vacíos
                    resetearCamiones();
                }

                // Solicitar al usuario la cantidad de artículos a ingresar
                String input = JOptionPane.showInputDialog(null, "Ingrese la cantidad de artículos:", 
                                                           "Entrada de Artículos", JOptionPane.QUESTION_MESSAGE);
                if (input == null || input.isEmpty()) continue;

                int cantidadArticulos = Integer.parseInt(input);

                // Simulación de ingreso y procesamiento de artículos
                for (int i = 0; i < cantidadArticulos; i++) {
                    Thread.sleep(500); // Simular tiempo entre artículos
                    int ingresados = contadorIngresados.incrementAndGet();
                    gui.getContadorIngresados().setText("Artículos ingresados: " + ingresados);

                    // Simulación de movimiento en la banda
                    JPanel bandaPanel = gui.getBandaPanel();
                    JLabel articulo = new JLabel("🎁");
                    articulo.setOpaque(true);
                    articulo.setBackground(Color.ORANGE);
                    bandaPanel.add(articulo);
                    bandaPanel.revalidate();
                    bandaPanel.repaint();

                    Thread.sleep(100); // Velocidad de paso de la banda transportadora
                    bandaPanel.remove(articulo);
                    bandaPanel.revalidate();
                    bandaPanel.repaint();

                    // Empaquetar y distribuir
                    int empaquetados = contadorEmpaquetados.incrementAndGet();
                    gui.getContadorEmpaquetados().setText("Artículos empaquetados: " + empaquetados);

                    JLabel contadorCamion = gui.getContadorCamiones().get(camionActual);
                    int valorActual = Integer.parseInt(contadorCamion.getText().split("/")[0]) + 1;
                    contadorCamion.setText(valorActual + "/20");

                    if (valorActual == 20) {
                        // Marcar camión como lleno
                        camionesLlenos[camionActual] = 20;
                        // Simular que el camión está lleno
                        JOptionPane.showMessageDialog(null, 
                            "El camión " + (camionActual + 1) + " está lleno y se retira.");
                        gui.getPanelCamiones().remove(contadorCamion);
                        gui.getPanelCamiones().revalidate();
                        gui.getPanelCamiones().repaint();

                        camionActual = (camionActual + 1) % 5;
                        Thread.sleep(1000); // Simular cambio de camión
                    }
                }

                // Preguntar si desea continuar después de agregar los artículos
                int opcion = JOptionPane.showConfirmDialog(null, 
                        "¿Desea ingresar más artículos?", 
                        "Continuar", JOptionPane.YES_NO_OPTION);

                if (opcion != JOptionPane.YES_OPTION) {
                    continuar = false;
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Entrada no válida. Intente nuevamente.");
            }
        }

        JOptionPane.showMessageDialog(null, "Proceso finalizado. Gracias por usar el sistema.");
        System.exit(0);
    }

    private void resetearCamiones() {
        // Reiniciar los camiones vacíos
        for (int i = 0; i < 5; i++) {
            camionesLlenos[i] = 0;  // Resetear el contador de camiones a 0
            JLabel contadorCamion = new JLabel("0/20");
            gui.getContadorCamiones().set(i, contadorCamion); // Reemplazar el camión en la GUI
            gui.getPanelCamiones().add(contadorCamion); // Agregarlo al panel
        }

        // Asegurarse de que los camiones vacíos sean visibles en la interfaz
        gui.getPanelCamiones().revalidate();
        gui.getPanelCamiones().repaint();

        // Mensaje de notificación
        JOptionPane.showMessageDialog(null, "Los camiones han sido vacíos y pueden volver a cargarse.");
    }
}
