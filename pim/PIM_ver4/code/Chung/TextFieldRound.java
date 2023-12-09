package Chung;

import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JTextField;

public class TextFieldRound extends JTextField {
    private Shape shape;
    private int radius = 0;
    
    public TextFieldRound () {
    	this.radius = 10;
    }
    
    public void setRoundAllCorner(int radius) {
    	this.radius = radius;
    	setOpaque(false);
    	repaint();
    }
    
    
    protected void paintComponent(Graphics g) {
         g.setColor(getBackground());
         g.fillRoundRect(0, 0, getWidth() - 1, getHeight()-1, radius, radius);
         super.paintComponent(g);
    }
    protected void paintBorder(Graphics g) {
         g.setColor(getForeground());
         g.drawRoundRect(0, 0, getWidth() - 1, getHeight()-1, radius, radius);
    }
    public boolean contains(int x, int y) {
         if (shape == null || !shape.getBounds().equals(getBounds())) {
             shape = new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight()-1, radius, radius);
         }
         return shape.contains(x, y);
    }
}