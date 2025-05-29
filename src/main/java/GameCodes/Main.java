package GameCodes;

import javax.swing.JFrame;

public class Main extends JFrame{
    public static void main(String[] args) {
        Main display = new Main();
        display.setTitle("Gameerrsss");
        display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Properly close the display when user click "X" button
        display. setResizable(false); // Display can not be resize

        GamePanel gamePanel = new GamePanel();
        display.add(gamePanel);

        display.pack(); // causes the display to be sized to fit the preferred size and layouts of its subcomponents

        display.setLocationRelativeTo(null); // Center the display
        display.setVisible(true); // To see the display

        gamePanel.startGameThread();
        
    }
}
