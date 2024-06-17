package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyHandler;
    public Player(GamePanel gp , KeyHandler keyHandler){
        this.gp = gp;
        this.keyHandler = keyHandler;
        setDefaultValues();
        getPlayerImage();
    }
    public void getPlayerImage() {  
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    public void setDefaultValues(){
        x = 100;
        y= 100;
        speed=4;
        direction = "down";
    }
    public void update(){
    	if(keyHandler.downPressed || keyHandler.rightPressed || keyHandler.leftPressed || keyHandler.upPressed) {
    		 if(keyHandler.upPressed){
    	            direction = "up";
    	            y -= speed;
    	        }else if(keyHandler.downPressed){
    	            direction = "down";
    	            y += speed;
    	        }else if (keyHandler.leftPressed){
    	            direction = "left";
    	            x -= speed;
    	        }else if(keyHandler.rightPressed){
    	            direction = "right";
    	            x += speed;
    	        }
    	        spriteCounter++;

    	        if(spriteCounter > 12) {
    	        	if(spriteNum ==1) {
    	        		spriteNum = 2 ;
    	        	}
    	        	else if(spriteNum == 2) {
    	        		spriteNum = 1;
    	        	}
    	        	spriteCounter = 0;

    	        }
    	}
       
    }
    public void draw( Graphics2D graphics2d){
        BufferedImage image = null;
        switch (direction) {
            case "up":
            	if(spriteNum == 1) {
            		image = up1;
            	}
            	if(spriteNum == 2) {
            		image = up2;
            	}
                break;
            case "down":
            	if(spriteNum == 1) {
            		image = down1;
            	}
            	if(spriteNum == 2) {
            		image = down2;
            	}
                break;
            case "left":
            	if(spriteNum == 1) {
            		image = left1;
            	}
            	if(spriteNum == 2) {
            		image = left2;
            	}
                break;
            case "right":
            	if(spriteNum == 1) {
            		image = right1;
            	}
            	if(spriteNum == 2) {
            		image = right2;
            	}
                break;
        }
        graphics2d.drawImage(image, x , y ,gp.tileSize, gp.tileSize, null);
    }
}