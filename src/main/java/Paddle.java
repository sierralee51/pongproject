
import java.awt.Color;
import java.awt.Graphics;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sierralee
 */
public class Paddle {
    public int num;
    public int x;
    public int y;
    public int width = 50;
    public int height = 250;
    public int score;
    public String name;
    public Paddle(Pong p, int num, String name){ // num represents paddle number
        this.num = num;
        this.name = name;
        if (num == 1){
            this.x = 0;
            // this.y = height;
        } else {
            this.x = p.width - width;
            
        }
        score = 0;
        this.y = p.height/2 - this.height/2;
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(x, y, width, height);
    }
    
    public void move(boolean up){
        int speed = 10;
        if (up){
            if (y - speed > 0){
                y -= speed;
            } else {
                y = 0;
            }
        } else { 
            if (y + speed + height < Pong.p.height){
                y += speed;
            } else {
                y = Pong.p.height - height;
            }
        }
    }


}
