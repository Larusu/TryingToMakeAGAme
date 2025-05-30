package GameCodes;

import javax.swing.JPanel;

import GameCodes.entity.Player;
import GameCodes.tile.TileManager;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class GamePanel extends JPanel implements Runnable{

    // SCREEN SETTINGS
    final int originalTileSize = 16; // 16 x 16 tile
    final int scale = 3; // 🔤 to in
    public final int tileSize = originalTileSize * scale; // 🔤 Sets the actual tile size used in the game: 48 x 48 tile

    public final int maxScreenCol = 18;
    public final int maxScreenRow = 14; // 4 x 5 screen
    public final int screenWidth = tileSize * maxScreenCol; // 960 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 768 pixels

    // FPS
    int FPS = 60;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread; // 🔤 Thread is something you can start and stop and once a thread started, 🎯 it keeps the program running
    Player player = new Player(this, keyH);
    TileManager tileM = new TileManager(this);

    // Set Player's default position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    public GamePanel(){

        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); // 🔤 set the size of this class (JPanel)
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true); // 🔤 set to true, all the drawing from this component will be done in an offscreen painting buffer.
                                            // 🎯 In short, enabling this can improve game's rendering performance  
        this.addKeyListener(keyH); // 🔤 Key inputs
        this.setFocusable(true); // 🔤 the GamePanel can be "focused" to receive key input
        
    }

    public void startGameThread(){
        gameThread = new Thread(this); // 🔤 passing GamePanel class to the thread's constructor
        gameThread.start(); // 🔤 Automatically call run()
    }

    // 🎯 Where game loop happens
    @Override
    public void run() { // ⚠️ Required when starting "gameThread", it automatically calls this run() method; 🧠 and a built-in method of "Runnable"
        
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime(); // 🔤 Returns the current value in nanoseconds.. 1,000,000,000 nanoseconds = 1 second
        long currentTime;

        long timer = 0;
        int drawCount = 0;

        while(gameThread != null){

            // 🧠 Making 60 FPS Happens using Delta/Accumulator
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime); // 🔤 for FPS Display
            lastTime = currentTime;            
            
            if(delta >= 1){
                // 🧠 1. UPDATE: Update information such as character position
                update();

                // 🧠 2 DRAW: Draw the screen with the updated information
                repaint(); // ⚠️ Use to call paintComponent method

                delta--;
                drawCount++;
            }

            // 🎯 Displaying (on terminal) FPS
            if (timer >= 1000000000){ 
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
            
        }
    }

    public void update(){

            player.update();
    }

    public void paintComponent(Graphics g) // 🧠 Built-in method in java, 🔤 Graphics class to draw objects on the screen
    {
        super.paintComponent(g); // ⚠️ Required format to properly override paintComponent

        Graphics2D g2 = (Graphics2D)g; // 🔤 Changing "Graphics g" to 2D Graphics, 🎯 it has a bit more function that we can use later

        tileM.draw(g2); // 🔤 Adding this before 'player' since this is the background; 🎯 first layer

        player.draw(g2);


        // g2.fillRect(playerX, playerY, tileSize, tileSize); // 🔤 Test Rectangle :(

        g2.dispose(); // 🔤 Dispose of this graphics context and release any system resource that is using 🎯 to save memories
    }
}
