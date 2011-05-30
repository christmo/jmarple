/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PrincipalGUI.java
 *
 * Created on 09/05/2011, 10:10:31 AM
 */
package marquesina;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.io.FileNotFoundException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import marquesina.fondo.FondoKradac;
import marquesina.marquesina.Marquesina;
import propiedades.Utilitarios;

/**
 *
 * @author kradac
 */
public class PrincipalGUI extends JFrame {

    private Properties arc;
    private Utilitarios utilitario = new Utilitarios();
    private String texto;
    private Marquesina marquee;
    //private int anchoP;
    private String macN;
    public static JFrame gui;
    private static Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    private static int altoVentana = 120;
    private static String[] parametros;
    private Configuracion config;
    private static Container fc;
    private FondoKradac fondo;

    private PrincipalGUI(int ancho, int alto) {
        initComponents();
        fc = this.getContentPane();
        
        int y = ((alto / 2) - (100 / 2));
        fondo = new FondoKradac(10, y, ancho, alto);
        //setContentPane(fondo);
        fc.add(fondo);

        /**
         * Cambiar la MAC del Equipo para que funcione en el computador
         */
        //if (macN.equals("90 FB A6 38 D6 AA")) {
        configInicial();
//        } else {
//            JOptionPane.showMessageDialog(null, "No es posible ejecutar este programa en el equipo...", "Error...", 0);
//            System.exit(0);
//        }
    }

    private String getMAC() {
        try {
            byte[] macAddress;
            Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
            for (NetworkInterface netint : Collections.list(nets)) {
                macAddress = netint.getHardwareAddress();
                //System.out.println("Nombre:" + netint.getDisplayName() + " Nombre: " + netint.getName());
                StringBuilder mac = new StringBuilder();
                if (macAddress != null) {
                    for (byte b : macAddress) {
                        mac.append(String.format("%1$02X ", b));
                    }
                }
                try {
                    if (!mac.substring(0, 2).equals("00")) {
                        //System.out.println(mac);
                        return mac.substring(0, 17);
                    }
                } catch (StringIndexOutOfBoundsException ex) {
                }
            }
        } catch (SocketException ex) {
            Logger.getLogger(PrincipalGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    private void configInicial() {
        this.setIconImage(new ImageIcon(getClass().getResource("/iconos/kradac_icono.png")).getImage());
        initComponents();
        this.setTitle("Maquesina - KRADAC");
        try {
            arc = utilitario.obtenerArchivoPropiedades("configuracion.properties");
        } catch (FileNotFoundException ex) {
            utilitario.crearArchivoPropiedades("configuracion.properties");
            try {
                arc = utilitario.obtenerArchivoPropiedades("configuracion.properties");
            } catch (FileNotFoundException ex1) {
                JOptionPane.showMessageDialog(this, "No se encontró el archivo de configuración", "Error...", 0);
            }
        }

        if (!arc.getProperty("titulo").equals("si")) {
            setUndecorated(true);
        }

        texto = leerMensajePropiedades();
        setAlwaysOnTop(true);

        try {
            marquee = new Marquesina(texto, lblTexto, d.width);
            formatoTextoMaquesina();
            iniciarMarquesina();
        } catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(null, "Eliminar el archivo de configuracion...", "Error...", 0);
        }

        try {
            int velocidad = Integer.parseInt(arc.getProperty("velocidad"));
            Marquesina.cambiarVelocidad(velocidad);
        } catch (NumberFormatException ex) {
            Marquesina.cambiarVelocidad(250);
        }

        try {
            Color col = new Color(Integer.parseInt(arc.getProperty("colLetra")));
            lblTexto.setForeground(col);

            lblTexto.setOpaque(true);
            col = new Color(Integer.parseInt(arc.getProperty("colFondo")));
            lblTexto.setBackground(col);

            //Container fc = this.getContentPane();
            fc.setBackground(col);
        } catch (NumberFormatException ex) {
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpmMenu = new javax.swing.JPopupMenu();
        jmMarquesina = new javax.swing.JMenuItem();
        jmConfigurar = new javax.swing.JMenuItem();
        jmSalir = new javax.swing.JMenuItem();
        lblTexto = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        jmMarquesina.setText("Marquesina");
        jmMarquesina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmMarquesinaActionPerformed(evt);
            }
        });
        jpmMenu.add(jmMarquesina);

        jmConfigurar.setText("Configurar");
        jmConfigurar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmConfigurarActionPerformed(evt);
            }
        });
        jpmMenu.add(jmConfigurar);

        jmSalir.setText("Salir");
        jmSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmSalirActionPerformed(evt);
            }
        });
        jpmMenu.add(jmSalir);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                formMouseReleased(evt);
            }
        });
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });

        lblTexto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("by KRADAC");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(119, 119, 119)
                .addComponent(lblTexto, javax.swing.GroupLayout.DEFAULT_SIZE, 608, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(88, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
            .addComponent(lblTexto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
        );

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-816)/2, (screenSize.height-151)/2, 816, 151);
    }// </editor-fold>//GEN-END:initComponents

    private void jmMarquesinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmMarquesinaActionPerformed
        if (lblTexto.isVisible()) {
            lblTexto.setVisible(false);
            jmMarquesina.setText("Mostrar Marquesina");
        } else {
            lblTexto.setVisible(true);
            jmMarquesina.setText("Ocultar Marquesina");
        }
}//GEN-LAST:event_jmMarquesinaActionPerformed

    private void jmConfigurarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmConfigurarActionPerformed
        if (config == null || !config.isDisplayable()) {
            config = new Configuracion(lblTexto, d.width);
            config.setResizable(false);
        }
        config.setVisible(true);
        config.setLocationRelativeTo(null);
}//GEN-LAST:event_jmConfigurarActionPerformed

    private void jmSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmSalirActionPerformed
        System.exit(0);
}//GEN-LAST:event_jmSalirActionPerformed

    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased
        if (evt.isPopupTrigger()) {
            jpmMenu.show(evt.getComponent(), evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_formMouseReleased

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        int y = ((evt.getComponent().getHeight() / 2) - (100 / 2));
        fondo.setNuevoLugarIcono(10, y, evt.getComponent().getWidth(), evt.getComponent().getHeight());
    }//GEN-LAST:event_formComponentResized

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        parametros = args;

        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                try {
                    UIManager.setLookAndFeel("org.pushingpixels.substance.api.skin.SubstanceNebulaBrickWallLookAndFeel");
                } catch (Exception e) {
                    System.out.println("Problemas al cargar Temas Substance");
                }
                try {
                    gui = new PrincipalGUI(Integer.parseInt(parametros[2]), Integer.parseInt(parametros[3]));
                } catch (IndexOutOfBoundsException ex) {
                    gui = new PrincipalGUI(d.width, altoVentana);
                }
                gui.setVisible(true);
                gui.setSize(d.width, altoVentana);
                if (parametros == null) {
                    gui.setBounds(0, d.height - altoVentana, d.width, altoVentana);
                } else {
                    try {
                        gui.setBounds(Integer.parseInt(parametros[0]),
                                Integer.parseInt(parametros[1]),
                                Integer.parseInt(parametros[2]),
                                Integer.parseInt(parametros[3]));
                    } catch (IndexOutOfBoundsException ex) {
                        gui.setBounds(0, d.height - altoVentana, d.width, altoVentana);
                    }
                }
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuItem jmConfigurar;
    private javax.swing.JMenuItem jmMarquesina;
    private javax.swing.JMenuItem jmSalir;
    private javax.swing.JPopupMenu jpmMenu;
    private javax.swing.JLabel lblTexto;
    // End of variables declaration//GEN-END:variables

    /**
     * Lee el mensaje del archivo de propiedades
     * @return String
     */
    private String leerMensajePropiedades() {
        try {
            return arc.getProperty("mensaje");
        } catch (NullPointerException ex) {
            return "No hay un mensaje en el archivo de propiedades...";
        }
    }

    private void iniciarMarquesina() {
        marquee.start();
    }

    /**
     * Formato desde el archivo de propiedades
     */
    private void formatoTextoMaquesina() {
        String fuente = "Arial";
        try {
            fuente = arc.getProperty("fuente");
        } catch (MissingResourceException ms) {
        } catch (NullPointerException ex) {
        }
        int tipo = 0;
        try {
            tipo = Integer.parseInt(arc.getProperty("tipo"));
        } catch (MissingResourceException ms) {
        } catch (NullPointerException ex) {
        } catch (NumberFormatException ex) {
        }
        int tamano = 12;
        try {
            tamano = Integer.parseInt(arc.getProperty("tamano"));
        } catch (MissingResourceException ms) {
        } catch (NullPointerException ex) {
        } catch (NumberFormatException ex) {
        }
        Font font = new Font(fuente, tipo, tamano);
        lblTexto.setFont(font);
    }

    static void cambiarColorFondo(Color fondo) {
        fc.setBackground(fondo);
    }
}
