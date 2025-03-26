package main;

import java.awt.*;

public class Ui {

    GamePanel gp;
    Font arial_40;
    Graphics2D g2;
    public int commandNum = 0;

    public Ui(GamePanel gp) {
        this.gp = gp;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
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
    g2.setColor(Color.black);
    g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

    g2.setFont(g2.getFont().deriveFont(Font.BOLD,76F));
    String text = "Disney Clash";
    int x = getXCenter(text);
    int y = gp.screenHeight/4;

    //Shadow
    g2.setColor(Color.gray);
    g2.drawString(text, x+5, y+5);

    //Main
    g2.setColor(Color.white);
    g2.drawString(text,x,y);

    //Menu
    g2.setFont(g2.getFont().deriveFont(Font.BOLD,42F));

    text = "PLAY";
    x = getXCenter(text);
    y += gp.tileSize*4;
    g2.drawString(text, x, y);
        if(commandNum == 0){
            g2.drawString(">", x-gp.tileSize, y);
        }

    text = "ABOUT US";
    x = getXCenter(text);
    y += gp.tileSize;
    g2.drawString(text, x, y);
        if(commandNum == 1){
            g2.drawString(">", x-gp.tileSize, y);
        }

        text = "MENU";
        x = getXCenter(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if(commandNum == 2){
            g2.drawString(">", x-gp.tileSize, y);
        }

        text = "QUIT";
    x = getXCenter(text);
    y += gp.tileSize;
    g2.drawString(text, x, y);
        if(commandNum == 3){
            g2.drawString(">", x-gp.tileSize, y);
        }
    }

    public void drawChoosingScreen(){
        g2.setColor(Color.black);
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD,76F));
        String text = "CHOOSE YOUR PRINCESS";
        int x = getXCenter(text);
        int y = gp.screenHeight/4;

        //Shadow
        g2.setColor(Color.gray);
        g2.drawString(text, x+5, y+5);

        //Main
        g2.setColor(Color.white);
        g2.drawString(text,x,y);

        //Menu
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,42F));

        text = "Snow White";
        x = getXCenter(text)/2;
        y += gp.tileSize*4;
        g2.drawString(text, x, y);


        text = "Cinderella";
        x = x - 20;
        y += gp.tileSize;
        g2.drawString(text, x, y);


        text = "Moana";
        x = x - 20;
        y += gp.tileSize;
        g2.drawString(text, x, y);


        text = "Mulan";
        x = x - 20;
        y += gp.tileSize;
        g2.drawString(text, x, y);

    }
}