package prog2.vista;

import prog2.adaptador.Adaptador;

import javax.swing.*;
import java.awt.event.*;

public class FrmVisualitzarInformacio extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JComboBox cmboxOpcionsVisualitzar;
    private JTextArea textArea1;
    private Adaptador adaptador;

    public FrmVisualitzarInformacio(Adaptador adaptador) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        textArea1.setEditable(false);



        cmboxOpcionsVisualitzar.addItem("---Selecciona una opció---");
        cmboxOpcionsVisualitzar.addItem("Estat de la central");
        cmboxOpcionsVisualitzar.addItem("Incidencies");
        cmboxOpcionsVisualitzar.addItem("Quadern de Bitacola");


        cmboxOpcionsVisualitzar.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String seleccion = (String) cmboxOpcionsVisualitzar.getSelectedItem();

                    switch (seleccion) {
                        case "Estat de la central":
                            textArea1.setText(adaptador.mostraEstatReactor().toString());
                            break;
                        case "Incidencies":
                            textArea1.setText(adaptador.mostraIncidencies());
                            break;
                        case "Quadern de Bitacola":
                            textArea1.setText(adaptador.mostraBitacola());

                            break;
                        default:
                            textArea1.setText(""); // Por ejemplo, si selecciona la opción por defecto
                    }
                }
            }
        });


        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });
    }

    private void onOK() {
        cmboxOpcionsVisualitzar.setSelectedItem("---Selecciona una opció---");
        textArea1.setText("");
        dispose();
    }
    /*
    public static void main(String[] args) {
        FrmVisualitzarInformacio dialog = new FrmVisualitzarInformacio();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
    */
}
