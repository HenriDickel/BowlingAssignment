package GUIComponents;

import javax.swing.*;
import java.awt.*;

public class CustomRectangle extends JComponent {

    public void paintComponent (Graphics g) {
        super.paintComponent(g);

        //Color
        g.setColor(Color.BLACK);

        //Get size of free space
        Rectangle clipBounds = g.getClipBounds();

        //Draw
        g.fillRect(0,0,clipBounds.width,clipBounds.height);
    }

}
