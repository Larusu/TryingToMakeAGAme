package GameCodes;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{ // 🎯 KeyListener is the interface for receiving keyboard events (keystrokes)
    
    public boolean upPressed, downPressed, rightPressed, leftPressed;

    // ⚠️ Must add this 3 methods
    @Override
    public void keyTyped(KeyEvent e){

    }    

    @Override
    public void keyPressed(KeyEvent e){
        int code = e.getKeyCode(); // 🎯 getKeyCode Detects which key was released

        // 🔤 Updating the movement to where the player wants to move
        if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP){ upPressed = true; } 
        if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT){ leftPressed = true; }
        if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN){ downPressed = true; }
        if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT){ rightPressed = true; }
    }    

    @Override
    public void keyReleased(KeyEvent e){
        int code = e.getKeyCode();

        // 🔤 Resetting the movement to prevent the player to move in that direction
        if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP){ upPressed = false; }
        if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT){ leftPressed = false; }
        if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN){ downPressed = false; }
        if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT){ rightPressed = false; }
    }    
}
