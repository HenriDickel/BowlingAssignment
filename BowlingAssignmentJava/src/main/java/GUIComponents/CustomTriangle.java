package GUIComponents;

import javax.swing.*;
import java.awt.*;

public class CustomTriangle extends JComponent {

    public void paintComponent (Graphics g) {
        super.paintComponent(g);

        //Color
        g.setColor(Color.BLACK);

        //Get size of free space
        Rectangle clipBounds = g.getClipBounds();

        //Polygon points
        int[] xPoints = new int[]{clipBounds.width, 0, clipBounds.width};
        int[] yPoints = new int[]{clipBounds.height, clipBounds.height, 0};

        //Draw
        g.fillPolygon(xPoints,yPoints,3);
    }

}
