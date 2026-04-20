package prog2.vista;
import javax.swing.*;
import java.awt.*;

public class Fondo extends JPanel {

    ImageIcon imatge;
    Image bgImg;

    public Fondo(String path) {
        imatge = new ImageIcon(path);
        bgImg = imatge.getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bgImg, 0, 0,getWidth(), getHeight(), null);
    }
}
