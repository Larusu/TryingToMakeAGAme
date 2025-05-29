package GameCodes.entity;

import java.awt.image.BufferedImage;

// 🔤 Storing variables that will be used for player, monster, npc's classes

public class Entity { 
    public int x, y;
    public int speed;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    // 🔤 BufferedImage - describes an image with an accessible buffer of image data: 🎯 Use this to store image files
    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;
}
