/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MAIN;

import GUI.LineaProduccionGUI;
import HILOS.LineaProduccionHilos;

public class Main {
    public static void main(String[] args) {
        LineaProduccionGUI gui = new LineaProduccionGUI();
        LineaProduccionHilos hilos = new LineaProduccionHilos(gui);
        hilos.start();
    }
}
