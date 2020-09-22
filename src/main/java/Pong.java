
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sierralee
 */
public class Pong implements ActionListener, KeyListener{
    public static Pong p;
    public Painter b;
    public int width = 700;
    public int height = 700;
    public Paddle player1;
    public Paddle player2;
    public Ball ball;
    public JFrame frame;
    public String font = "Krungthep";
    public Color fontColor = new Color(84, 201, 151);
    
    public int won = 0;
    public boolean w, s, up, down;
    public int gameStatus = 0; 
    
    public Pong(){
        Timer timer = new Timer(20, this);
        frame = new JFrame("Pong Game");
        b = new Painter();
        frame.setSize(width, height);
        frame.add(b);
        frame.addKeyListener(this);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        timer.start();
    }
    public static void main(String[] args){
        p = new Pong();
    }
    public void start(String s1, String s2){
        gameStatus = 2;
        player1 = new Paddle(this, 1, s1);
        player2 = new Paddle(this, 2, s2);
        ball = new Ball(this);
    }
    public void update(){
        
        if (player1.score == 1){
            gameStatus = 3;
            won = 1;
        }
        else if (player2.score == 1){
            gameStatus = 3;
            won = 2;
        }
        if (w){
            player1.move(true);
        }
        if (s){
            player1.move(false);
        }
        if (up){
            player2.move(true);
        }
        if (down){
            player2.move(false);
        }
        ball.update(player1, player2);
    }
    public void draw(Graphics2D g){
        g.setColor(new Color(171, 242, 192));
        g.fillRect(0, 0, width, height);
        
        if (gameStatus == 0){
            g.setColor(fontColor);
            g.setFont(new Font(font, 1, 75));
            g.drawString("PONG", width/2 - 105, height/2-65);
            g.setFont(new Font(font, 1, 20));
            System.out.println("x");
            g.drawString("Press space to play", width/2 - 105, height/2-25);
            
            /* for testing something
            Blink bl = new Blink("Press space to play", g, width/2 - 105, height/2-25, b);
            Timer t = new Timer(1000, bl);
            t.addActionListener(bl);
            t.start(); 
            */
            
        }
        
        if (gameStatus == 2 || gameStatus == 1){
            g.setColor(Color.WHITE);
            g.setStroke(new BasicStroke(10));
            g.drawLine(width/2, 0, width/2, height);
            player1.draw(g);
            player2.draw(g);
            ball.draw(g);
        }
        
        if (gameStatus == 1){
            g.setColor(fontColor);
            g.setFont(new Font(font, 1, 50));
            g.drawString("PAUSED", width/2-95, 300);
        }
        
        if (gameStatus == 3){
            g.setColor(fontColor);
            g.setFont(new Font(font, 1, 50));
            String s = "";
            if (won == 1){
                s = player1.name;
            } else if (won == 2){
                s = player2.name;
            }
            g.drawString(s + " wins!", width/2-145+((5-s.length())*10), height/2-65);
            g.setFont(new Font(font, 1, 25));
            g.drawString("Press space to play again", width/2 - 165, height/2-20);
        }
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameStatus == 2){
            update();
        }
        b.repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int id = e.getKeyCode();
        if (id == KeyEvent.VK_W){
            w = true;
        }
        if (id == KeyEvent.VK_S){
            s = true;
        }
        if (id == KeyEvent.VK_UP){
            up = true;
        }
        if (id == KeyEvent.VK_DOWN){
            down = true;
        }
        if (id == KeyEvent.VK_SPACE){
            if (gameStatus == 0 || gameStatus == 3){ 
                int m = JOptionPane.NO_OPTION;
                if (gameStatus == 3){
                    m = JOptionPane.showConfirmDialog(frame, "Same players?");
                }
                if (m == JOptionPane.CANCEL_OPTION){
                    frame.setVisible(false);
                    frame.dispose();
                    System.exit(0);
                }
                if (m == JOptionPane.NO_OPTION){
                   String p1Name = JOptionPane.showInputDialog(frame, "Player 1 (q/s) name? ", "Names", 1);
                   String p2Name = JOptionPane.showInputDialog(frame, "Player 2 (up/down) name?", "Names", 1); 
                   start(p1Name, p2Name);
                } else {
                start(player1.name, player2.name);
                }
                // gameStatus = 2;
            }
            else if (gameStatus == 2){
                gameStatus = 1;
            }
            else if (gameStatus == 1){
                gameStatus = 2;
            }
        }
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int id = e.getKeyCode();
        if (id == KeyEvent.VK_W){
            w = false;
        }
        if (id == KeyEvent.VK_S){
            s = false;
        }
        if (id == KeyEvent.VK_UP){
            up = false;
        }
        if (id == KeyEvent.VK_DOWN){
            down = false;
        }
        
    }
}
