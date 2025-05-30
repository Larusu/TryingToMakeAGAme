package GameCodes.tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import GameCodes.GamePanel;

public class TileManager extends Tile{

    GamePanel gp;
    Tile[] tile; // 🔤 Assigning into array every single image tile
    int mapTileNum[][]; // 🔤 Storing the txt map file

    public TileManager(GamePanel gp){

        this.gp = gp;

        // INSTANTIATE
        tile = new Tile[10]; // 🔤 Making 10 kinds of tiles: grass tile, water tile, stone tile
        mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];

        // CALLING METHODS
        getTileImage();
        loadMap();
    }

    public void getTileImage(){

        try {
            tile[0] = new Tile(); // 0 = Grass
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/resource/tiles/grass.png"));

            tile[1] = new Tile(); // 1 = Cobblestone
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/resource/tiles/cobblestone.png"));

            tile[2] = new Tile(); // 2 = Water
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/resource/tiles/water.png"));
            
        } catch (IOException e) {
            e.printStackTrace();        
        }
    }

    public void loadMap(){

        try {

            InputStream is = getClass().getResourceAsStream("/resource/maps/map01.txt"); // 🔤 Import txt file
            BufferedReader br = new BufferedReader(new InputStreamReader(is)); // 🎯 Reads the content of the txt file

            int row = 0;

            while (row < gp.maxScreenRow) {
                String line = br.readLine();  // 🎯 readLine reads a line of text

                // 🔤 Insertting content into array
                String[] numbers = line.split(" "); // 🎯 Splits the string at space " "

                // 🧠 Loop through each column  in the current row to convert and store the tile numbers
                for (int col = 0; col < gp.maxScreenCol; col++) {

                    // 🔤 Changing from string into int
                    int num = Integer.parseInt(numbers[col]); 

                    // 🔤 Store the extracted "num" in the array mapTileNum[]
                    mapTileNum[col][row] = num;
                }

                row++; // Go to the next line(row)
            }
            
            br.close(); // ⚠️ Only close after the entire map is read

        } catch (Exception e) {
            e.printStackTrace(); //  Show error in console for easier debugging
        }
    }

    public void draw(Graphics2D g2){
        
        int col = 0, row = 0, x = 0, y = 0;
        
        while(col < gp.maxScreenCol && row < gp.maxScreenRow){ 

            // Extract a tile number which is stored in mapTileNum[][] and storing into "tileNum"
            int tileNum = mapTileNum[col][row];

            g2.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
            col++; // 🔤 
            x += gp.tileSize; // 🔤 adding (tileSize) 48 to x, 🎯 draw to the right tile

            if(col == gp.maxScreenCol){ // 🔤 check if col (18) == maxScreenCol(18) 🎯 to shift the next tile to the bottom
                col = 0;
                x = 0;
                row++; // 🎯 draw to the bottom tile
                y += gp.tileSize;
            }
        }
    }
}
