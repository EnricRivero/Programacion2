package prog2.vista;

import prog2.adaptador.Adaptador;
import prog2.model.BombaRefrigerant;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;

public class FrmGestioComponentsCentral extends JDialog {
    private JPanel panelComp;
    private JButton btnAplicarModificacions;
    private JButton btnCancelarModificacions;
    private JSlider sldBarresControl;
    private JTextField txtIntroduirInsercioBarresControl;
    private JButton btnIntroduirInsercioBarresControl;
    private JCheckBox chkReactor;
    private JCheckBox chkBomba1,chkBomba2, chkBomba3,chkBomba4;
    private JLabel lblTemp;
    private JButton btnTotesBombes;
    private JList Fora_serv;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;
    private JPanel panel5;
    private JPanel mask;
    private JPanel mascara;
    private JProgressBar progressBar1;
    private JProgressBar progressBar2;
    private JPanel panel6;
    private JPanel panelAjsute;
    private int insercioOriginal;
    private boolean reactorOriginal;
    private boolean orig_b1, orig_b2, orig_b3, orig_b4;


    private Adaptador adaptador;
    StringBuilder errores = new StringBuilder();
    private String[] FS= new String[4];


    /*
    public static void main(String[] args) {
        FrmGestioComponentsCentral dialog = new FrmGestioComponentsCentral(adaptador);
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
*/
    public FrmGestioComponentsCentral(Adaptador adaptador) {
        this.adaptador = adaptador;
        setContentPane(panelComp);
        setModal(true);
        getRootPane().setDefaultButton(btnAplicarModificacions);

        mask.setLayout(null);
        mask.setBackground(new Color(0, 0, 0, 0));
        mask.setOpaque(false);


        txtIntroduirInsercioBarresControl.setBounds(185, 330, 80, 25);
        btnIntroduirInsercioBarresControl.setBounds(295, 330, 150, 25);


        sldBarresControl.setBounds(0, 358, 450, 30);
        mascara.setBounds(0, 0, 362, 20);

        sldBarresControl.setMinimum(0);
        sldBarresControl.setMaximum(100);
        sldBarresControl.setValue((int)adaptador.getInsercioBarres());

        panel1.setBackground(new Color(0, 0, 0, 0));
        panel1.setOpaque(false);

        panel2.setBackground(new Color(0, 0, 0, 0));
        panel2.setOpaque(false);

        panel3.setBackground(new Color(0, 0, 0, 0));
        panel3.setOpaque(false);

        panel4.setBackground(new Color(0, 0, 0, 0));
        panel4.setOpaque(false);

        panel5.setBackground(new Color(0, 0, 0, 0));
        panel5.setOpaque(false);







        Fora_serv.setBackground(new Color(0, 0, 0, 0));
        Fora_serv.setOpaque(false);

        chkBomba1.setBackground(new Color(0, 0, 0, 0));
        chkBomba1.setOpaque(false);

        chkBomba2.setBackground(new Color(0, 0, 0, 0));
        chkBomba2.setOpaque(false);

        chkBomba3.setBackground(new Color(0, 0, 0, 0));
        chkBomba3.setOpaque(false);

        chkBomba4.setBackground(new Color(0, 0, 0, 0));
        chkBomba4.setOpaque(false);

        sldBarresControl.setBackground(new Color(0, 0, 0, 0));
        sldBarresControl.setOpaque(false);




        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                estatAll();
                lblTemp.setText("Temperatura: "+ adaptador.mostraEstatReactor().getTemperatura()+ " Cº");
                insercioOriginal = (int)adaptador.getInsercioBarres();
                sldBarresControl.setValue(insercioOriginal);
                reactorOriginal = adaptador.mostraEstatReactor().getActivat();
                chkReactor.setSelected(reactorOriginal);
                orig_b1 = adaptador.bomba(0).getActivat();
                orig_b2 = adaptador.bomba(1).getActivat();
                orig_b3 = adaptador.bomba(2).getActivat();
                orig_b4 = adaptador.bomba(3).getActivat();

                chkBomba1.setSelected(orig_b1);
                chkBomba2.setSelected(orig_b2);
                chkBomba3.setSelected(orig_b3);
                chkBomba4.setSelected(orig_b4);


                for(int i=0; i<4; i++){
                    if(adaptador.bomba((i)).getForaDeServei()==true){
                        FS[i]= "Bomba "+ (i+1);
                    }else{
                        FS[i]= " ";
                    }

                }

                Fora_serv.setListData(FS);

            }
        });




        btnAplicarModificacions.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                onOK();
            }
        });

        btnCancelarModificacions.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        panelComp.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);




        sldBarresControl.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int valorActual = sldBarresControl.getValue();
                txtIntroduirInsercioBarresControl.setText(String.valueOf(valorActual));
                int tamaño=(int)((100-valorActual)*(4.5));
                mascara.setBounds(450-tamaño, 362, tamaño, 20);

            }
        });

        txtIntroduirInsercioBarresControl.addActionListener(e -> {
            try {
                int valor = Integer.parseInt(txtIntroduirInsercioBarresControl.getText());
                if (valor < 0) {
                    valor = 0;
                } else if (valor > 100) {
                    valor = 100;
                }
                sldBarresControl.setValue(valor);
                txtIntroduirInsercioBarresControl.setText(String.valueOf(valor));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Introdueix un número enter vàlid entre 0 i 100.");
            }
        });

        btnIntroduirInsercioBarresControl.addActionListener(e -> {
            try {
                int valor = Integer.parseInt(txtIntroduirInsercioBarresControl.getText());
                if (valor < 0) {
                    valor = 0;
                } else if (valor > 100) {
                    valor = 100;
                }
                sldBarresControl.setValue(valor);
                txtIntroduirInsercioBarresControl.setText(String.valueOf(valor));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Introdueix un número enter vàlid entre 0 i 100.");
            }
        });




        btnTotesBombes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                intercanviText();

                if(btnTotesBombes.getText().equals("Activar totes les bombes")) {
                    chkBomba1.setSelected(false);
                    chkBomba2.setSelected(false);
                    chkBomba3.setSelected(false);
                    chkBomba4.setSelected(false);
                }else{
                    chkBomba1.setSelected(true);
                    chkBomba2.setSelected(true);
                    chkBomba3.setSelected(true);
                    chkBomba4.setSelected(true);

                }
            }
        });


        chkBomba1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                estatAll();
            }
        });
        chkBomba2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 estatAll();
            }
        });
        chkBomba3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 estatAll();

            }
        });
        chkBomba4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                estatAll();

            }
        });


    }

    private void onOK() {
        adaptador.setInsercioBarres(sldBarresControl.getValue());


            if (chkReactor.isSelected()) {
                try {
                adaptador.activaReactor();
                } catch (Exception e) {
                    errores.append("- ").append(e.getMessage()).append("\n");
                }
            } else {
                adaptador.desactivaReactor();
            }


            boolean atLeastOne = false;
            for (int i = 0; i <= 3; i++) {
                JCheckBox bomba;
                switch (i) {
                    case 0:
                        bomba = chkBomba1;
                        break;

                    case 1:
                        bomba = chkBomba2;
                        break;

                    case 2:
                        bomba = chkBomba3;
                        break;

                    case 3:
                        bomba = chkBomba4;
                        break;

                    default:
                        bomba = null;
                }

                try{
                if (bomba != null) {
                    if (bomba.isSelected()) {
                        adaptador.activaBomba(i);
                        atLeastOne = true;

                    } else {
                        adaptador.desactivaBomba(i);
                    }
                }
                } catch (Exception e) {
                    errores.append("- ").append(e.getMessage()).append("\n");
                }


            }


        if (errores.length() > 0) {
            JOptionPane.showMessageDialog(this, errores.toString(), "Errores encontrados", JOptionPane.ERROR_MESSAGE);
        }
        errores.setLength(0);

        if (atLeastOne && !chkReactor.isSelected()) {
            int respuesta= JOptionPane.showConfirmDialog(this, "El reactor està desactivat, tenir les bombes activades suposa un cost innecessari.\nVols continuar amb la selecció? ","Advertència", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

            if(respuesta == JOptionPane.NO_OPTION){
                return;
            }
        }

        dispose();
        }

    private void onCancel() {
        sldBarresControl.setValue(insercioOriginal);
        chkReactor.setSelected(reactorOriginal);
        chkBomba1.setSelected(orig_b1);
        chkBomba2.setSelected(orig_b2);
        chkBomba3.setSelected(orig_b3);
        chkBomba4.setSelected(orig_b4);
        dispose();
    }

    private void estatAll(){
        if(chkBomba1.isSelected()&&chkBomba2.isSelected()&&chkBomba3.isSelected()&&chkBomba4.isSelected()){
            btnTotesBombes.setText("Desactivar totes les bombes");
        } else if ((!chkBomba1.isSelected())&&(!chkBomba2.isSelected())&&(!chkBomba3.isSelected())&&(!chkBomba4.isSelected())) {
            btnTotesBombes.setText("Activar totes les bombes");

        }
    }

    private void intercanviText(){
        String tAct= "Activar totes les bombes";
        String tDes= "Desactivar totes les bombes";
        String t= btnTotesBombes.getText();

        if (t.equals(tDes)) {
            btnTotesBombes.setText(tAct);


        }else{
            btnTotesBombes.setText(tDes);
        }
    }

    private void createUIComponents() {
        panelComp= new Fondo("Assets/Components.png");

    }
}
