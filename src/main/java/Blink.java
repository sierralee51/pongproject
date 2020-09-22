
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sierralee
 */
public class Blink implements ActionListener{

    public String text;
    public Graphics g;
    public int x;
    public int y;
    public Background b;
    public JFrame frame;
    
    public Blink(String text, Graphics g, int x, int y, Background b){
        this.text = text;
        this.g = g;
        this.x = x;
        this.y = y;
        this.b = b;
 
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        g.setColor(Color.BLACK);
        g.setFont(new Font("Krungthep", 1, 75));
        g.fillRect(50, 50, 50, 50);
        g.drawString(text, x, y);
        System.out.println(g);
        System.out.println("y");
        System.out.println(text);
        // System.out.println("TEST");
        b.repaint();
    }
    
}
