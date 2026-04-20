package prog2.vista;
import prog2.adaptador.Adaptador;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;



public class AppCentralUB extends JFrame{
    private JPanel panelPrincipal;
    private JButton btnGestioComponentsCentral;

    private JButton btnVisualitzarInformacio;
    private JButton btnFinalitzarDia;
    private JButton btnGuardar;
    private JButton btnCarregar;
    private JLabel lblNumDia;
    private JLabel lblDemandaPotencia;
    private JLabel lblGuanys;

    private JDialog GestioComponents;
    private JDialog VisualitzarInformacio;

    private VariableNormal variableNormal;
    private float demandaPotencia;
    private Adaptador adaptador;
    public final static float DEMANDA_MAX = 1800;
    public final static float DEMANDA_MIN = 250;
    public final static float VAR_NORM_MEAN = 1000;
    public final static float VAR_NORM_STD = 800;
    public final static long VAR_NORM_SEED = 123;





    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AppCentralUB central = new AppCentralUB();
            central.setVisible(true);
        });
    }

    public AppCentralUB(){
        variableNormal = new VariableNormal(VAR_NORM_MEAN, VAR_NORM_STD, VAR_NORM_SEED);
        demandaPotencia = generaDemandaPotencia();

        adaptador = new Adaptador();



        GestioComponents= new FrmGestioComponentsCentral(adaptador);
        GestioComponents.setSize(1200, 800);
        GestioComponents.setVisible(false);
        GestioComponents.setLocationRelativeTo(null);



        VisualitzarInformacio= new FrmVisualitzarInformacio(adaptador);
        VisualitzarInformacio.setSize(700, 450);
        VisualitzarInformacio.setVisible(false);
        VisualitzarInformacio.setLocation(400,0);


        lblNumDia.setText("Dia: "+adaptador.getDia());
        lblDemandaPotencia.setText("Demanda de potència d'avui: "+String.valueOf(demandaPotencia));
        lblGuanys.setText("Guanys acumulats: "+ adaptador.getGuanys());

        setTitle("Central Nuclear UB");
        setDefaultCloseOperation(EXIT_ON_CLOSE);





        setContentPane(panelPrincipal);



        setSize(500,500);
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        btnGestioComponentsCentral.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GestioComponents.setVisible(true);

            }
        });

        btnVisualitzarInformacio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VisualitzarInformacio.setVisible(true);

            }
        });


        btnFinalitzarDia.addActionListener(e -> {
            finalitzaDia();
            JOptionPane.showMessageDialog(this, "Dia finalitzat.");

        });

        btnGuardar.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            int result = chooser.showSaveDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                // guardar fitxer
                adaptador.guardaDades(String.valueOf(result));
                JOptionPane.showMessageDialog(this, "Dades guardades.");
            }
        });


        btnCarregar.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            int result = chooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                // carregar fitxer
                adaptador.carregaDades(String.valueOf(result));
                JOptionPane.showMessageDialog(this, "Dades carregades.");
                lblNumDia.setText("Dia: "+adaptador.getDia());
                float dem= adaptador.getDia()==1?250:adaptador.getDemanda();
                lblDemandaPotencia.setText("Demanda de potència d'avui: "+String.valueOf(dem));
                lblGuanys.setText("Guanys acumulats: "+ adaptador.getGuanys());
            }
        });
    }

    private float generaDemandaPotencia() {
        float valor = Math.round(variableNormal.seguentValor());
        if (valor > DEMANDA_MAX)
            return DEMANDA_MAX;
        else if (valor < DEMANDA_MIN)
            return DEMANDA_MIN;
        else
            return valor;
    }

    private void finalitzaDia() {

        // Generar i mostrar nova demanda de potencia
        demandaPotencia = generaDemandaPotencia();

        adaptador.finalitzaDia(demandaPotencia);

        lblNumDia.setText("Dia: "+adaptador.getDia());
        lblDemandaPotencia.setText("Demanda de potència d'avui: "+String.valueOf(demandaPotencia));
        lblGuanys.setText("Guanys acumulats: "+ adaptador.getGuanys());





        String bitacola = adaptador.mostraBitacola();
        int diaActual = adaptador.getDia();

        String inici = "DIA " + (diaActual-1);

        int iniciIndex = bitacola.indexOf(inici);

        String resultat;
        resultat = bitacola.substring(iniciIndex);



        JOptionPane.showMessageDialog(this, resultat);

    }


    private void createUIComponents() {
        panelPrincipal= new Fondo("Assets/Principal.png");
    }


}
