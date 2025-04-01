package main;

import java.awt.*;
import javax.swing.ImageIcon;

public class Ui {

    GamePanel gp;
    Font arial_40;
    Graphics2D g2;
    public int commandNum = 0;
    public int commandAbt = 0;
    private Image gifBackground, dev1, dev2, dev3, dev4, dev5;


    public Ui(GamePanel gp) {
        this.gp = gp;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        // Load the GIF background
        ImageIcon gifIcon = new ImageIcon(getClass().getResource("/res/background/falls.gif"));
        ImageIcon gifIcon2 = new ImageIcon(getClass().getResource("/res/background/keint.gif"));
        ImageIcon gifIcon3 = new ImageIcon(getClass().getResource("/res/background/raf.gif"));
        ImageIcon gifIcon4 = new ImageIcon(getClass().getResource("/res/background/trixie.gif"));
        ImageIcon gifIcon5 = new ImageIcon(getClass().getResource("/res/background/earl.gif"));
        ImageIcon gifIcon6 = new ImageIcon(getClass().getResource("/res/background/april.gif"));

        gifBackground = gifIcon.getImage();
        dev1 = gifIcon2.getImage();
        dev2 = gifIcon3.getImage();
        dev3 = gifIcon4.getImage();
        dev4 = gifIcon5.getImage();
        dev5 = gifIcon6.getImage();

    }

    public void draw(Graphics2D g2){
        this.g2 = g2;
        g2.setFont(arial_40);
        g2.setColor(Color.WHITE);

        if(gp.gameState == gp.tileState){
            drawTitleScreen();
        }

        if(gp.gameState == gp.playState){
            // Gameplay UI elements could go here
        }

        if(gp.gameState == gp.pauseState){
            drawPauseScreen();
        }

        if(gp.gameState == gp.gameOverState){
            drawGameOverScreen();
        }

        if(gp.gameState == gp.winState){
            drawWinScreen();
        }

        if (gp.gameState == gp.choosingState) {
            drawChoosingScreen();
        }

        if(gp.gameState == gp.chooseCharacterState){
            drawChooseCharacterScreen();
        }

        if(gp.gameState == gp.abState){
            drawAboutUsScreen();
        }

        if(gp.gameState == gp.abState2){
            drawAboutUsScreen2();
        }

        if(gp.gameState == gp.abState3){
            drawAboutUsScreen3();
        }

        if(gp.gameState == gp.abState4){
            drawAboutUsScreen4();
        }

        if(gp.gameState == gp.abState5){
            drawAboutUsScreen5();
        }

    }

    public void drawAboutUsScreen() {
        g2.drawImage(dev1, 0, 0, gp.screenWidth, gp.screenHeight, null);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD,60F));
        String text = "DEVELOPER 1";
        int x = getXCenter(text);
        int y = gp.screenHeight/4;

        // Main Title
        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD,42F));

        text = "BACK";
        g2.drawString(text, 100, 520);
        if(commandNum == 1){
            g2.drawString(">", 60, 520);
        }

        text = "Next";
        g2.drawString(text, 580, 520);
        if(commandNum == 2){
            g2.drawString(">", 530, 520);
        }
    }

    public void drawAboutUsScreen2() {
        g2.drawImage(dev2, 0, 0, gp.screenWidth, gp.screenHeight, null);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,60F));
        String text = "DEVELOPER 2";
        int x = getXCenter(text);
        int y = gp.screenHeight/4;

        // Main Title
        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD,42F));

        text = "BACK";
        g2.drawString(text, 100, 520);
        if(commandNum == 1){
            g2.drawString(">", 60, 520);
        }

        text = "Next";
        g2.drawString(text, 580, 520);
        if(commandNum == 2){
            g2.drawString(">", 530, 520);
        }
    }

    public void drawAboutUsScreen3() {
        g2.drawImage(dev3, 0, 0, gp.screenWidth, gp.screenHeight, null);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,60F));
        String text = "DEVELOPER 3";
        int x = getXCenter(text);
        int y = gp.screenHeight/4;

        // Main Title
        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD,42F));

        text = "BACK";
        g2.drawString(text, 100, 520);
        if(commandNum == 1){
            g2.drawString(">", 60, 520);
        }

        text = "Next";
        g2.drawString(text, 580, 520);
        if(commandNum == 2){
            g2.drawString(">", 530, 520);
        }
    }

    public void drawAboutUsScreen4() {
        g2.drawImage(dev4, 0, 0, gp.screenWidth, gp.screenHeight, null);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,60F));
        String text = "DEVELOPER 4";
        int x = getXCenter(text);
        int y = gp.screenHeight/4;

        // Main Title
        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD,42F));

        text = "BACK";
        g2.drawString(text, 100, 520);
        if(commandNum == 1){
            g2.drawString(">", 60, 520);
        }

        text = "Next";
        g2.drawString(text, 580, 520);
        if(commandNum == 2){
            g2.drawString(">", 530, 520);
        }
    }

    public void drawAboutUsScreen5() {
        g2.drawImage(dev5, 0, 0, gp.screenWidth, gp.screenHeight, null); g2.setFont(g2.getFont().deriveFont(Font.BOLD,60F));
        String text = "DEVELOPER 5";
        int x = getXCenter(text);
        int y = gp.screenHeight/4;

        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD,42F));

        text = "BACK";
        g2.drawString(text, 100, 520);
        if(commandNum == 1){
            g2.drawString(">", 60, 520);
        }
    }

    public void drawChooseCharacterScreen() {
    }

    public void drawPauseScreen(){
        String text = "PAUSED";
        int x = getXCenter(text);
        int y = gp.screenHeight/2;
        g2.drawString(text, x, y);
    }

    public void drawGameOverScreen(){
        String text = "GAME OVER";
        int x = getXCenter(text);
        int y = gp.screenHeight/2;
        g2.drawString(text, x, y);

        text = "Press ENTER to restart";
        g2.setFont(new Font("Arial", Font.PLAIN, 20));
        x = getXCenter(text);
        y = gp.screenHeight/2 + 50;
        g2.drawString(text, x, y);
    }

    public void drawWinScreen(){
        String text = "YOU WIN!";
        int x = getXCenter(text);
        int y = gp.screenHeight/2;
        g2.drawString(text, x, y);

        text = "Press ENTER to play again";
        g2.setFont(new Font("Arial", Font.PLAIN, 20));
        x = getXCenter(text);
        y = gp.screenHeight/2 + 50;
        g2.drawString(text, x, y);
    }

    public int getXCenter(String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }

    public void drawTitleScreen(){
        // Draw the background GIF
        g2.drawImage(gifBackground, 0, 0, gp.screenWidth, gp.screenHeight, null);

        // Title Text
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,76F));
        String text = "Disney Clash";
        int x = getXCenter(text);
        int y = gp.screenHeight/4;

        // Shadow for Title
        g2.setColor(Color.gray);
        g2.drawString(text, x + 5, y + 5);

        // Main Title
        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        // Menu Options
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,42F));

        text = "PLAY";
        x = getXCenter(text);
        y += gp.tileSize*4;
        g2.drawString(text, x, y);
        if(commandNum == 0){
            g2.drawString(">", x - gp.tileSize, y);
        }

        text = "MENU";
        x = getXCenter(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if(commandNum == 1){
            g2.drawString(">", x - gp.tileSize, y);
        }

        text = "ABOUT US";
        x = getXCenter(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if(commandNum == 2){
            g2.drawString(">", x - gp.tileSize, y);
        }

        text = "QUIT";
        x = getXCenter(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if(commandNum == 3){
            g2.drawString(">", x - gp.tileSize, y);
        }
    }

    public void drawChoosingScreen(){
        g2.setColor(Color.black);
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 42F));
        String text = "CHOOSE YOUR MODE";
        int x = getXCenter(text);
        int y = gp.screenHeight / 4;

        // Shadow for Title
        g2.setColor(Color.gray);
        g2.drawString(text, x + 5, y + 5);

        // Main Title
        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        // Menu Options
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 42F));

        text = "PLAYER VS NPC";
        x = getXCenter(text);
        y += gp.tileSize * 4;
        g2.drawString(text, x, y);
        if(commandNum == 0){
            g2.drawString(">", x - gp.tileSize, y);
        }

        text = "PLAYER VS PLAYER";
        x = getXCenter(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if(commandNum == 1){
            g2.drawString(">", x - gp.tileSize, y);
        }

        text = "EXIT";
        x = getXCenter(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if(commandNum == 2){
            g2.drawString(">", x - gp.tileSize, y);
        }
    }
}
