/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author enox4
 */
public class Game {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws RuntimeException, IOException {
        BufferedReader lee = new BufferedReader(new InputStreamReader(System.in));
        boolean yes = true;
        boolean acertado = false;

        int num = (int) (Math.random() * 20 + 1);
        int intentos = 5;
        int bas;
        System.out.println("-------------------------------------------------------");
        System.out.println("He generado un número aleatorio del 1 al 20, aciertalo.");
        System.out.println("-------------------------------------------------------");
        while (yes) {
            if (intentos == 5) {
                System.out.println("Prueba un numero:");
            } else if (intentos > 0) {
                System.out.println("Prueba otra vez: (Intentos restantes: [" + intentos + "])");
            } else {
                System.out.println("Has fracasado, procederé a apagarte el sistema.");
            }
            bas = Integer.parseInt(lee.readLine());
            System.out.println("------------------------------------------");

            if (bas == -1) {
                yes = false;
            } else {
                acertado = bas == num;

                if (acertado) {
                    System.out.println("Felicidades por acertar! Procederé a apagarte el sistema.");
                    yes = false;
                }
            }
            intentos--;
        }

    }



}
