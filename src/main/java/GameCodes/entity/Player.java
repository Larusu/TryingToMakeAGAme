package GameCodes.entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;

import GameCodes.GamePanel;
import GameCodes.KeyHandler;

public class Player extends Entity{
    GamePanel gp;
    KeyHandler keyH;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){
        x = 100;
        y = 100;
        speed = 4;
        direction = "down"; // 🔤 Default direction
    }

    public void getPlayerImage(){
        try {

            up1 = ImageIO.read(getClass().getResourceAsStream("/player/back1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/back2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/front1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/front2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/right2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/left1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/left2.png"));
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void update(){ // 🧠 This update() method gets called 60 times per second

        if(keyH.upPressed == true || keyH.downPressed == true ||
        keyH.leftPressed == true || keyH.rightPressed == true){ // 🎯 Player will stay still when nothing is press

            // 🧠 In java the upper left corner is X:0 Y:0. so X increases to right, and Y increases going down

            // 🎯 Updating player coordinates
            if (keyH.upPressed == true){
                direction = "up";
                y -= speed; // up W 
            }   
            else if(keyH.downPressed == true){ 
                direction = "down";
                y += speed; // down s
            } 
            else if(keyH.rightPressed == true){
                direction = "right";
                x += speed; // right d
            }
            else if(keyH.leftPressed == true){
                direction = "left";
                x -= speed; // left a
            } 

            spriteCounter++; // 🔤 In every frame this gets called and increases spriteCounter by 1
            if (spriteCounter > 14){ // since update() get called 60 timer per second : 🎯 This counter makes the "player" image changes every 14 frames
                if(spriteNum == 1){
                    spriteNum = 2;
                } else if(spriteNum == 2){
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    public void draw(Graphics2D g2)
    {
        BufferedImage image = null;

        switch(direction){
            case "up":
                if(spriteNum == 1){
                    image = up1;
                }
                if(spriteNum == 2){
                    image = up2;
                }
                break;
            case "down":
                if(spriteNum == 1){
                    image = down1;
                }
                if(spriteNum == 2){
                    image = down2;
                }
                break;
            case "left":
                if(spriteNum == 1){
                    image = left1;
                }
                if(spriteNum == 2){
                    image = left2;
                }
                break;
            case "right":
                if(spriteNum == 1){
                    image = right1;
                }
                if(spriteNum == 2){
                    image = right2;
                }
                break;
        }

        // DrawImage(Image img, int x, int y, int width, int height, ImageObserver observer) - 🎯 draw an image on the screen
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }
}
