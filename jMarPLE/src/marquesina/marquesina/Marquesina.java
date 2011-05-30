/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package marquesina.marquesina;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.Timer;
import propiedades.Utilitarios;

/**
 *
 * @author kradac
 */
public class Marquesina extends Thread {

    private static String texto;
    private JLabel lblTexto;
    private static Timer marquee;

    public Marquesina(String text, JLabel lblTexto, int anchoP) {
        Marquesina.texto = Utilitarios.espaciosFrace(text, lblTexto.getFont().getSize(), anchoP);
        this.lblTexto = lblTexto;
    }

    @Override
    public void run() {
        Marquesina();
    }

    public static void modificarTexto(String txt) {
        Marquesina.texto = txt;
    }

    public static void cambiarVelocidad(int delay) {
        try {
            marquee.setDelay(delay);
        } catch (NullPointerException ex) {
        }
    }

    public static void ponerColorCajaTexto(JTextField txtColor, int color) {
        Color c = new Color(color);
        txtColor.setBackground(c);
    }

    private void Marquesina() {
        marquee = new Timer(250,
                new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                        char firstChar = texto.charAt(0);
                        texto = texto.substring(1, texto.length()) + firstChar;
                        lblTexto.setText(texto);
                    }
                });

        marquee.start();
    }
}
