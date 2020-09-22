
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sierralee
 */
public class Ball {
    // 52:48
    public int x, y;
    
    public int width = 25, height = 25;
    
    public int diameter = 10;
    
    public int motionX, motionY;
    
    public Random random;
    
    public Pong p;
    
    public Ball(Pong p){
        this.random = new Random();
        this.p = p;
        this.x = p.width/2 - this.width/2;
        this.y = p.height/2 - this.height/2;
        this.motionY = -2 + random.nextInt(4);
        if (motionY == 0){
            motionY = 1;
        }
        if (random.nextBoolean()){
            motionX = 1;
        } else {
            motionX = -1;
        }
        
        
    }
    public void update(Paddle p1, Paddle p2){
        this.x += motionX * 5;
        this.y += motionY * 5;
        ///
        if (this.y + height - motionY > p.height || this.y + motionY < 0)
		{
			if (this.motionY < 0)
			{
				this.y = 0;
				this.motionY = random.nextInt(4);

				if (motionY == 0)
				{
					motionY = 1;
				}
			}
			else
			{
				this.motionY = -random.nextInt(4);
				this.y = p.height - height;

				if (motionY == 0)
				{
					motionY = -1;
				}
			}
		}
        ///
        if (checkCollision(p1) == 1){
           this.motionX = 1;
           this.motionY = -2 + random.nextInt(4);
           if (motionY == 0){
               motionY = 1;
           }
        }
        else if (checkCollision(p2) == 1){
           this.motionX = -1;
           this.motionY = -2 + random.nextInt(4);
           
           if (motionY == 0){
               motionY = 1;
           }
        }
        if (checkCollision(p1) == 2){
            p2.score++;
        }
        else if (checkCollision(p2) == 2){
            p1.score++;
        }
        
    }
    public int checkCollision(Paddle pa){
        if (this.x < pa.x + pa.width && this.x + width > pa.x && this.y < pa.y + pa.height && this.y + height > pa.y)
		{
			return 1; //bounce
		}
		else if ((pa.x > x && pa.num == 1) || (pa.x < x - width && pa.num == 2))
		{
			return 2; //score
		}

		return 0; //nothing 
    }
    
    public void draw(Graphics g){
        g.setColor(Color.WHITE);
        g.fillOval(x, y, width, height);
    }
}
