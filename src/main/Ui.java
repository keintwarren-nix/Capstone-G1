package main;

import java.awt.*;
import javax.swing.ImageIcon;

public class  Ui {

    GamePanel gp;
    Font arial_40;
    Graphics2D g2;
    public int commandNum = 0;
    public int commandAbt = 0;
    public int characterChoice = 0;
    private Image gifBackground, dev1, dev2, dev3, dev4, dev5, back1, back2, back3, back4, back5, back6, back7,back8;
    private Image profile1, profile2, profile3, profile4, profile5, profile6, profile7, profile8;
    private Image pfpEff1, pfpEff12, pfpEff2, pfpEff3, pfpEff32, pfpEff4, pfpEff5, pfpEff6, pfpEff7, pfpEff8;

    private Image icon1, icon2, icon3, icon4, icon5, icon6, icon7, icon8, AI, keint, rafael, trixie, april, earl;
    private Image descChar1, descChar2, descChar3, descChar4, descChar5, descChar6, descChar7, descChar8;

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

        ImageIcon charback1 = new ImageIcon(getClass().getResource("/res/background/BackgroundAriel.gif"));
        ImageIcon charback2 = new ImageIcon(getClass().getResource("/res/background/BackgroundCinderella.gif"));
        ImageIcon charback3 = new ImageIcon(getClass().getResource("/res/background/BackgroundElsa.gif"));
        ImageIcon charback4 = new ImageIcon(getClass().getResource("/res/background/BackgroundMoana.gif"));
        ImageIcon charback5 = new ImageIcon(getClass().getResource("/res/background/BackgroundMulan.gif"));
        ImageIcon charback6 = new ImageIcon(getClass().getResource("/res/background/BackgroundSnowwhite.gif"));
        ImageIcon charback7 = new ImageIcon(getClass().getResource("/res/background/BackgroundTiana.gif"));
        ImageIcon charback8 = new ImageIcon(getClass().getResource("/res/background/BackgroundRapunzel.gif"));

        back1 = charback1.getImage();
        back2 = charback2.getImage();
        back3 = charback3.getImage();
        back4 = charback4.getImage();
        back5 = charback5.getImage();
        back6 = charback6.getImage();
        back7 = charback7.getImage();
        back8 = charback8.getImage();

        ImageIcon profileAriel = new ImageIcon(getClass().getResource("/res/background/ArielProfile.png"));
        ImageIcon profileCinderella = new ImageIcon(getClass().getResource("/res/background/CinderellaProfile.png"));
        ImageIcon profileElsa = new ImageIcon(getClass().getResource("/res/background/ElsaProfile.png"));
        ImageIcon profileMoana = new ImageIcon(getClass().getResource("/res/background/MoanaProfile.png"));
        ImageIcon profileMulan = new ImageIcon(getClass().getResource("/res/background/MulanProfile.png"));
        ImageIcon profileRapunzel = new ImageIcon(getClass().getResource("/res/background/RapunzelProfile.png"));
        ImageIcon profileTiana = new ImageIcon(getClass().getResource("/res/background/TianaProfile.png"));
        ImageIcon profileSnowWhite = new ImageIcon(getClass().getResource("/res/background/SnowWhiteProfile.png"));

        profile1 = profileAriel.getImage();
        profile2 = profileCinderella.getImage();
        profile3 = profileElsa.getImage();
        profile4 = profileMoana.getImage();
        profile5 = profileMulan.getImage();
        profile6 = profileSnowWhite.getImage();
        profile7 = profileTiana.getImage();
        profile8 = profileRapunzel.getImage();

        ImageIcon descEff1 = new ImageIcon(getClass().getResource("/res/objects/descAriel.png"));
        ImageIcon descEff2 = new ImageIcon(getClass().getResource("/res/objects/descCinderella.png"));
        ImageIcon descEff3 = new  ImageIcon(getClass().getResource("/res/objects/descElsa.png"));
        ImageIcon descEff4 = new  ImageIcon(getClass().getResource("/res/objects/descMoana.png"));
        ImageIcon descEff5 = new  ImageIcon(getClass().getResource("/res/objects/descMulan.png"));
        ImageIcon descEff6 = new  ImageIcon(getClass().getResource("/res/objects/descSnowWhite.png"));
        ImageIcon descEff7 = new  ImageIcon(getClass().getResource("/res/objects/descTiana.png"));
        ImageIcon descEff8 = new  ImageIcon(getClass().getResource("/res/objects/descRapunzel.png"));

        descChar1 = descEff1.getImage();
        descChar2 = descEff2.getImage();
        descChar3 = descEff3.getImage();
        descChar4 = descEff4.getImage();
        descChar5 = descEff5.getImage();
        descChar6 = descEff6.getImage();
        descChar7 = descEff7.getImage();
        descChar8 = descEff8.getImage();

        ImageIcon profileEff1 = new ImageIcon(getClass().getResource("/res/background/PfpEffectAriel.gif"));
        ImageIcon profileEff12 = new ImageIcon(getClass().getResource("/res/background/PfpEffectAriel2.gif"));
        ImageIcon profileEff2 = new ImageIcon(getClass().getResource("/res/background/PfpEffectCinderella.gif"));
        ImageIcon profileEff22 = new ImageIcon(getClass().getResource("/res/background/PfpEffectCinderella2.gif"));
        ImageIcon profileEff3 = new ImageIcon(getClass().getResource("/res/background/PfpEffectElsa.gif"));
        ImageIcon profileEff32 = new ImageIcon(getClass().getResource("/res/background/PfpEffectElsa2.gif"));
        ImageIcon profileEff4 = new ImageIcon(getClass().getResource("/res/background/PfpEffectMoana.gif"));
        ImageIcon profileEff5 = new ImageIcon(getClass().getResource("/res/background/PfpEffectMulan.gif"));
        ImageIcon profileEff6 = new ImageIcon(getClass().getResource("/res/background/PfpEffectSnowWhite.gif"));
        ImageIcon profileEff7 = new ImageIcon(getClass().getResource("/res/background/PfpEffectTiana.gif"));
        ImageIcon profileEff8 = new ImageIcon(getClass().getResource("/res/background/PfpEffectRapunzel.gif"));

        pfpEff1 = profileEff1.getImage();
        pfpEff12 = profileEff12.getImage();
        pfpEff2 = profileEff2.getImage();
        pfpEff3 = profileEff3.getImage();
        pfpEff32 = profileEff32.getImage();
        pfpEff4 = profileEff4.getImage();
        pfpEff5 = profileEff5.getImage();
        pfpEff6 = profileEff6.getImage();
        pfpEff7 = profileEff7.getImage();
        pfpEff8 = profileEff8.getImage();

        ImageIcon icon_1 = new ImageIcon(getClass().getResource("/res/objects/Ariel_Icon.png"));
        ImageIcon icon_2 = new ImageIcon(getClass().getResource("/res/objects/Cinderella_Icon.png"));
        ImageIcon icon_3 = new ImageIcon(getClass().getResource("/res/objects/Elsa_Icon.png"));
        ImageIcon icon_4 = new ImageIcon(getClass().getResource("/res/objects/Moana_Icon.png"));
        ImageIcon icon_5 = new ImageIcon(getClass().getResource("/res/objects/Mulan_Icon.png"));
        ImageIcon icon_6 = new ImageIcon(getClass().getResource("/res/objects/SnowWhite_Icon.png"));
        ImageIcon icon_7 = new ImageIcon(getClass().getResource("/res/objects/Tiana_Icon.png"));
        ImageIcon icon_8 = new ImageIcon(getClass().getResource("/res/objects/Rapunzel_Icon.png"));

        ImageIcon ai = new ImageIcon(getClass().getResource("/res/objects/ai.png"));

        ImageIcon k = new ImageIcon(getClass().getResource("/res/objects/keint.jpg"));
        ImageIcon r = new ImageIcon(getClass().getResource("/res/objects/rafael.jpg"));
        ImageIcon t = new ImageIcon(getClass().getResource("/res/objects/trixie.jpg"));
        ImageIcon a = new ImageIcon(getClass().getResource("/res/objects/april.jpg"));
        ImageIcon e = new ImageIcon(getClass().getResource("/res/objects/earl.jpg"));

        AI = ai.getImage();
        keint = k.getImage();
        rafael = r.getImage();
        trixie = t.getImage();
        april = a.getImage();
        earl = e.getImage();

        icon1 = icon_1.getImage();
        icon2 = icon_2.getImage();
        icon3 = icon_3.getImage();
        icon4 = icon_4.getImage();
        icon5 = icon_5.getImage();
        icon6 = icon_6.getImage();
        icon7 = icon_7.getImage();
        icon8 = icon_8.getImage();

    }

    public void draw(Graphics2D g2){
        this.g2 = g2;
        g2.setFont(arial_40);
        g2.setColor(Color.WHITE);

        if(gp.gameState == gp.tileState){
            gp.sound.playMusic(7);
            drawTitleScreen();
        }

        if(gp.gameState == gp.settingState){
            drawSettingScreen();
        }

        if(gp.gameState == gp.playState){

            try {
                if (characterChoice == 0) {
                    characterChoice = 1; // Default to character 1 if no choice was made
                }
                switch (characterChoice) {
                    case 1:
                        ImageIcon a = new ImageIcon(getClass().getResource("/res/background/BackgroundAriel.gif"));
                        gp.backgroundImage = a.getImage();
                        gp.sound.playMusic(6);
                        break;
                    case 2:
                        ImageIcon b = new ImageIcon(getClass().getResource("/res/background/BackgroundCinderella.gif"));
                        gp.backgroundImage = b.getImage();
                        gp.sound.playMusic(9);
                        break;
                    case 3:
                        ImageIcon c = new ImageIcon(getClass().getResource("/res/background/BackgroundElsa.gif"));
                        gp.backgroundImage = c.getImage();
                        gp.sound.playMusic(10);
                        break;
                    case 4:
                        ImageIcon d = new ImageIcon(getClass().getResource("/res/background/BackgroundMoana.gif"));
                       gp.backgroundImage = d.getImage();
                       gp.sound.playMusic(11);
                        break;
                    case 5:
                        ImageIcon e = new ImageIcon(getClass().getResource("/res/background/BackgroundMulan.gif"));
                        gp.backgroundImage = e.getImage();
                        gp.sound.playMusic(12);
                        break;
                    case 6:
                        ImageIcon f = new ImageIcon(getClass().getResource("/res/background/BackgroundSnowWhite.gif"));
                        gp.backgroundImage = f.getImage();
                        gp.sound.playMusic(14);
                        break;
                    case 7:
                        ImageIcon g = new ImageIcon(getClass().getResource("/res/background/BackgroundTiana.gif"));
                        gp.backgroundImage = g.getImage();
                        gp.sound.playMusic(15);
                        break;
                    case 8:
                        ImageIcon h = new ImageIcon(getClass().getResource("/res/background/BackgroundRapunzel.gif"));
                        gp.backgroundImage = h.getImage();
                        gp.sound.playMusic(13);
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            g2.setColor(new Color(0, 0, 0, 150));
            g2.fillRoundRect(290,  0, 200, 100, 30, 30);
            drawRoundInfo(g2);

            g2.drawImage(AI, 660, 500, 75, 75, null);

            switch(characterChoice){
                case 1:
                    g2.drawImage(profile1, -10, 480, 150, 150, null);
                    break;
                case 2:
                    g2.drawImage(profile2, -10, 480, 150, 150, null);
                    break;
                case 3:
                    g2.drawImage(profile3, -10, 480, 150, 150, null);
                    break;
                case 4:
                    g2.drawImage(profile4, -10, 480, 150, 150, null);
                    break;
                case 5:
                    g2.drawImage(profile5, -10, 480, 150, 150, null);
                    break;
                case 6:
                    g2.drawImage(profile6, -10, 480, 150, 150, null);
                    break;
                case 7:
                    g2.drawImage(profile7, -10, 480, 150, 150, null);
                    break;
                case 8:
                    g2.drawImage(profile8, -10, 480, 150, 150, null);
                    break;
            }



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
            gp.sound.playMusic(7);
            drawChoosingScreen();
        }

        if(gp.gameState == gp.chooseCharacterState){
            gp.sound.playMusic(8);
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

        if(gp.gameState == gp.char1){
            drawAriel();
        }

        if(gp.gameState == gp.char2){
            drawCinderella();
        }

        if(gp.gameState == gp.char3){
            drawElsa();
        }

        if(gp.gameState == gp.char4){
            drawMoana();
        }

        if(gp.gameState == gp.char5){
            drawnMulan();
        }

        if(gp.gameState == gp.char6){
            drawSnowWhite();
        }

        if(gp.gameState == gp.char7){
            drawTiana();
        }

        if(gp.gameState == gp.char8){
            drawRapunzel();
        }

        if(gp.gameState == gp.roundTransitionState) {
            drawRoundTransition(g2);
        }

    }

    public void drawRoundInfo(Graphics2D g2) {
        g2.setColor(Color.WHITE);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 20F));

        // Draw round number
        String roundText = "ROUND " + gp.roundManager.getCurrentRound() + "/" + gp.roundManager.getMaxRounds();
        g2.drawString(roundText, gp.screenWidth/2 - g2.getFontMetrics().stringWidth(roundText)/2, 30);

        // Draw score
        String scoreText = "PLAYER " + gp.roundManager.getPlayerWins() + " - " + gp.roundManager.getDummyWins() + " CPU";
        g2.drawString(scoreText, gp.screenWidth/2 - g2.getFontMetrics().stringWidth(scoreText)/2, 60);

        // Draw timer
        String timeText = String.format("%02d", gp.roundManager.getRemainingTime());
        g2.drawString(timeText, gp.screenWidth/2 - g2.getFontMetrics().stringWidth(timeText)/2, 90);
    }

    public void drawRoundTransition(Graphics2D g2) {
        g2.setColor(new Color(0, 0, 0, 200)); // Semi-transparent black background
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        g2.setColor(Color.WHITE);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40F));

        // Determine the round result text
        String resultText;
        if (gp.player.health <= 0) {
            resultText = "YOU LOSE ROUND " + gp.roundManager.getCurrentRound();
        } else if (gp.dummy.health <= 0) {
            resultText = "YOU WIN ROUND " + gp.roundManager.getCurrentRound();
        } else {
            // Time up scenario
            float playerHealthPercent = gp.player.health / 100f;
            float dummyHealthPercent = gp.dummy.health / 100f;

            if (playerHealthPercent > dummyHealthPercent) {
                resultText = "YOU WIN ROUND " + gp.roundManager.getCurrentRound();
            } else if (dummyHealthPercent > playerHealthPercent) {
                resultText = "YOU LOSE ROUND " + gp.roundManager.getCurrentRound();
            } else {
                resultText = "ROUND " + gp.roundManager.getCurrentRound() + " DRAW";
            }
        }

        // Draw round result
        int y = gp.screenHeight/2 - 80;
        g2.drawString(resultText, gp.screenWidth/2 - g2.getFontMetrics().stringWidth(resultText)/2, y);

        // Draw score
        String scoreText = "SCORE: PLAYER " + gp.roundManager.getPlayerWins() + " - " + gp.roundManager.getDummyWins() + " CPU";
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 30F));
        y += 60;
        g2.drawString(scoreText, gp.screenWidth/2 - g2.getFontMetrics().stringWidth(scoreText)/2, y);

        // Draw "Next round starting..." or "Match over" message
        String nextText;
        if (gp.roundManager.getPlayerWins() >= (gp.roundManager.getMaxRounds() / 2 + 1) || gp.roundManager.getDummyWins() >= (gp.roundManager.getMaxRounds() / 2 + 1)) {
            nextText = "MATCH OVER";
        } else {
            nextText = "NEXT ROUND STARTING...";
        }

        y += 60;
        g2.drawString(nextText, gp.screenWidth/2 - g2.getFontMetrics().stringWidth(nextText)/2, y);
    }

    public void drawRapunzel() {
        characterChoice = 8;
        g2.drawImage(back8, 0, 0, gp.screenWidth, gp.screenHeight, null);
        g2.drawImage(pfpEff8, 0, 0, gp.screenWidth, gp.screenHeight, null);
        g2.drawImage(profile8, -175, 25, 720, 600, null);

        // Draw "Cinderella" title
        g2.setFont(g2.getFont().deriveFont(Font.ITALIC, 45F));
        String title = "Rapunzel";
        int titleX = 500;
        int titleY = (gp.screenHeight / 4) - 50;

        int x = getXCenter(title);

        // Draw background rectangle behind title
        FontMetrics fmTitle = g2.getFontMetrics();
        int titleWidth = fmTitle.stringWidth(title);
        int titleHeight = fmTitle.getHeight();

        g2.setColor(new Color(0, 0, 0, 150)); // semi-transparent black
        g2.fillRoundRect(420, titleY - titleHeight + 10, titleWidth+ 60, titleHeight + 20, 30, 30);

        // Draw title text
        g2.setColor(Color.white);
        g2.drawString(title, 450, titleY+5);
        // Draw BACK and NEXT
        g2.setFont(g2.getFont().deriveFont(Font.ITALIC, 30F));
        FontMetrics fm = g2.getFontMetrics();

        String Description = "DESCRIPTION";
        int descX = 470;
        int descY = titleY + 100;
        int descWidth = fm.stringWidth(Description);
        int descHeight = fm.getHeight();

        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRoundRect(420, descY - descHeight, descWidth + 40, descWidth + 70, 30, 30);

        g2.setColor(Color.white);
        g2.drawString(Description, descX-30, descY);
        g2.drawImage(descChar8, descX-60, descY, 260, 260, null);


        // BACK button
        String backText = "BACK";
        int backX = 100;
        int backY = 500;
        int backWidth = fm.stringWidth(backText);
        int backHeight = fm.getHeight();

        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRoundRect(backX - 20, backY - backHeight, backWidth + 40, backHeight + 20, 30, 30);

        g2.setColor(Color.white);
        g2.drawString(backText, backX, backY);
        if (commandAbt == 1) {
            g2.drawString(">", backX - 40, backY);
        }


        // BACK button
        String chooseText = "CHOOSE";
        int chooseX = x;
        int chooseY = 500;
        int chooseWidth = fm.stringWidth(chooseText);
        int chooseHeight = fm.getHeight();

        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRoundRect(chooseX+15, chooseY - chooseHeight, chooseWidth + 40, chooseHeight + 20, 30, 30);

        g2.setColor(Color.white);
        g2.drawString(chooseText, chooseX+30, chooseY);
        if (commandAbt == 0) {
            g2.drawString(">", chooseX - 20, backY);
        }

}

    public void drawTiana() {
        characterChoice = 7;
        g2.drawImage(back7, 0, 0, gp.screenWidth, gp.screenHeight, null);
        g2.drawImage(pfpEff7, 330, 460, gp.tileSize*3, gp.tileSize*3, null);
        g2.drawImage(profile7, -150, 60, 640, 520, null);

        g2.setFont(g2.getFont().deriveFont(Font.ITALIC, 45F));
        String title = "Tiana";
        int titleX = 500;
        int titleY = (gp.screenHeight / 4) - 50;

        int x = getXCenter(title);

        // Draw background rectangle behind title
        FontMetrics fmTitle = g2.getFontMetrics();
        int titleWidth = fmTitle.stringWidth(title);
        int titleHeight = fmTitle.getHeight();

        g2.setColor(new Color(0, 0, 0, 150)); // semi-transparent black
        g2.fillRoundRect(420, titleY - titleHeight + 10, titleWidth+ 130, titleHeight + 20, 30, 30);

        // Draw title text
        g2.setColor(Color.white);
        g2.drawString(title, 480, titleY+5);
        // Draw BACK and NEXT
        g2.setFont(g2.getFont().deriveFont(Font.ITALIC, 30F));
        FontMetrics fm = g2.getFontMetrics();

        String Description = "DESCRIPTION";
        int descX = 470;
        int descY = titleY + 100;
        int descWidth = fm.stringWidth(Description);
        int descHeight = fm.getHeight();

        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRoundRect(420, descY - descHeight, descWidth + 40, descWidth + 70, 30, 30);

        g2.setColor(Color.white);
        g2.drawString(Description, descX-30, descY);
        g2.drawImage(descChar7, descX-60, descY, 260, 260, null);

        // BACK button
        String backText = "BACK";
        int backX = 100;
        int backY = 500;
        int backWidth = fm.stringWidth(backText);
        int backHeight = fm.getHeight();

        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRoundRect(backX - 20, backY - backHeight, backWidth + 40, backHeight + 20, 30, 30);

        g2.setColor(Color.white);
        g2.drawString(backText, backX, backY);
        if (commandAbt == 1) {
            g2.drawString(">", backX - 40, backY);
        }

        // BACK button
        String chooseText = "CHOOSE";
        int chooseX = x;
        int chooseY = 500;
        int chooseWidth = fm.stringWidth(chooseText);
        int chooseHeight = fm.getHeight();

        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRoundRect(chooseX - 20, chooseY - chooseHeight, chooseWidth + 40, chooseHeight + 20, 30, 30);

        g2.setColor(Color.white);
        g2.drawString(chooseText, chooseX, chooseY);
        if (commandAbt == 0) {
            g2.drawString(">", chooseX - 40, backY);
        }

        // NEXT button
        String nextText = "Next";
        int nextX = 580;
        int nextY = 500;
        int nextWidth = fm.stringWidth(nextText);
        int nextHeight = fm.getHeight();

        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRoundRect(nextX + 10, nextY - nextHeight, nextWidth + 40, nextHeight + 20, 30, 30);

        g2.setColor(Color.white);
        g2.drawString(nextText, nextX + 30, nextY);
        if (commandAbt == 3) {
            g2.drawString(">", nextX - 50, nextY);
        }
    }

    public void drawSnowWhite() {
        characterChoice = 6;
        g2.drawImage(back6, 0, 0, gp.screenWidth, gp.screenHeight, null);
        g2.drawImage(pfpEff6, 150, 300, gp.screenWidth, gp.screenHeight, null);
        g2.drawImage(pfpEff6, -175, 300, gp.screenWidth, gp.screenHeight, null);
        g2.drawImage(profile6, -150, 60, 640, 520, null);

        g2.setFont(g2.getFont().deriveFont(Font.ITALIC, 35F));
        String title = "Snow White";
        int titleX = 500;
        int titleY = (gp.screenHeight / 4) - 50;

        int x = getXCenter(title);

        // Draw background rectangle behind title
        FontMetrics fmTitle = g2.getFontMetrics();
        int titleWidth = fmTitle.stringWidth(title);
        int titleHeight = fmTitle.getHeight();

        g2.setColor(new Color(0, 0, 0, 150)); // semi-transparent black
        g2.fillRoundRect(420, titleY - titleHeight + 10, titleWidth+ 55, titleHeight + 20, 30, 30);

        // Draw title text
        g2.setColor(Color.white);
        g2.drawString(title, 450, titleY+12);
        // Draw BACK and NEXT
        g2.setFont(g2.getFont().deriveFont(Font.ITALIC, 30F));
        FontMetrics fm = g2.getFontMetrics();

        String Description = "DESCRIPTION";
        int descX = 470;
        int descY = titleY + 100;
        int descWidth = fm.stringWidth(Description);
        int descHeight = fm.getHeight();

        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRoundRect(420, descY - descHeight, descWidth + 40, descWidth + 70, 30, 30);
        g2.drawImage(descChar6, descX-60, descY, 260, 260, null);

        g2.setColor(Color.white);
        g2.drawString(Description, descX-30, descY);

        // BACK button
        String backText = "BACK";
        int backX = 100;
        int backY = 500;
        int backWidth = fm.stringWidth(backText);
        int backHeight = fm.getHeight();

        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRoundRect(backX - 20, backY - backHeight, backWidth + 40, backHeight + 20, 30, 30);

        g2.setColor(Color.white);
        g2.drawString(backText, backX, backY);
        if (commandAbt == 1) {
            g2.drawString(">", backX - 40, backY);
        }

        // BACK button
        String chooseText = "CHOOSE";
        int chooseX = x;
        int chooseY = 500;
        int chooseWidth = fm.stringWidth(chooseText);
        int chooseHeight = fm.getHeight();

        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRoundRect(chooseX + 15, chooseY - chooseHeight, chooseWidth + 40, chooseHeight + 20, 30, 30);

        g2.setColor(Color.white);
        g2.drawString(chooseText, chooseX+28, chooseY);
        if (commandAbt == 0) {
            g2.drawString(">", chooseX - 20, backY);
        }

        // NEXT button
        String nextText = "Next";
        int nextX = 580;
        int nextY = 500;
        int nextWidth = fm.stringWidth(nextText);
        int nextHeight = fm.getHeight();

        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRoundRect(nextX + 10, nextY - nextHeight, nextWidth + 40, nextHeight + 20, 30, 30);

        g2.setColor(Color.white);
        g2.drawString(nextText, nextX + 30, nextY);
        if (commandAbt == 3) {
            g2.drawString(">", nextX - 50, nextY);
        }
    }

    public void drawnMulan() {
        characterChoice = 5;
        g2.drawImage(back5, 0, 0, gp.screenWidth, gp.screenHeight, null);
        g2.drawImage(pfpEff5, 0, 150, gp.screenWidth, gp.screenHeight, null);
        g2.drawImage(profile5, -100, 60, 640, 520, null);

        g2.setFont(g2.getFont().deriveFont(Font.ITALIC, 45F));
        String title = "Mulan";
        int titleX = 500;
        int titleY = (gp.screenHeight / 4) - 50;

        int x = getXCenter(title);

        // Draw background rectangle behind title
        FontMetrics fmTitle = g2.getFontMetrics();
        int titleWidth = fmTitle.stringWidth(title);
        int titleHeight = fmTitle.getHeight();

        g2.setColor(new Color(0, 0, 0, 150)); // semi-transparent black
        g2.fillRoundRect(420, titleY - titleHeight + 10, titleWidth+ 110, titleHeight + 20, 30, 30);

        // Draw title text
        g2.setColor(Color.white);
        g2.drawString(title, 470, titleY+5);
        // Draw BACK and NEXT
        g2.setFont(g2.getFont().deriveFont(Font.ITALIC, 30F));
        FontMetrics fm = g2.getFontMetrics();

        String Description = "DESCRIPTION";
        int descX = 470;
        int descY = titleY + 100;
        int descWidth = fm.stringWidth(Description);
        int descHeight = fm.getHeight();

        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRoundRect(420, descY - descHeight, descWidth + 40, descWidth + 70, 30, 30);
        g2.drawImage(descChar5, descX-60, descY, 260, 260, null);

        g2.setColor(Color.white);
        g2.drawString(Description, descX-30, descY);


        // BACK button
        String backText = "BACK";
        int backX = 100;
        int backY = 500;
        int backWidth = fm.stringWidth(backText);
        int backHeight = fm.getHeight();

        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRoundRect(backX - 20, backY - backHeight, backWidth + 40, backHeight + 20, 30, 30);

        g2.setColor(Color.white);
        g2.drawString(backText, backX, backY);
        if (commandAbt == 1) {
            g2.drawString(">", backX - 40, backY);
        }


        // BACK button
        String chooseText = "CHOOSE";
        int chooseX = x;
        int chooseY = 500;
        int chooseWidth = fm.stringWidth(chooseText);
        int chooseHeight = fm.getHeight();

        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRoundRect(chooseX - 20, chooseY - chooseHeight, chooseWidth + 40, chooseHeight + 20, 30, 30);

        g2.setColor(Color.white);
        g2.drawString(chooseText, chooseX, chooseY);
        if (commandAbt == 0) {
            g2.drawString(">", chooseX - 40, backY);
        }

        // NEXT button
        String nextText = "Next";
        int nextX = 580;
        int nextY = 500;
        int nextWidth = fm.stringWidth(nextText);
        int nextHeight = fm.getHeight();

        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRoundRect(nextX + 10, nextY - nextHeight, nextWidth + 40, nextHeight + 20, 30, 30);

        g2.setColor(Color.white);
        g2.drawString(nextText, nextX + 30, nextY);
        if (commandAbt == 3) {
            g2.drawString(">", nextX - 50, nextY);
        }
    }

    public void drawMoana() {
        characterChoice = 4;
        g2.drawImage(back4, 0, 0, gp.screenWidth, gp.screenHeight, null);
        g2.drawImage(pfpEff4, 190, 290, gp.screenWidth/2, gp.screenHeight/2, null);
        g2.drawImage(profile4, -175, 25, 720, 600, null);


        // Draw "Cinderella" title
        g2.setFont(g2.getFont().deriveFont(Font.ITALIC, 45F));
        String title = "Moana";
        int titleX = 500;
        int titleY = (gp.screenHeight / 4) - 50;

        int x = getXCenter(title);

        // Draw background rectangle behind title
        FontMetrics fmTitle = g2.getFontMetrics();
        int titleWidth = fmTitle.stringWidth(title);
        int titleHeight = fmTitle.getHeight();

        g2.setColor(new Color(0, 0, 0, 150)); // semi-transparent black
        g2.fillRoundRect(420, titleY - titleHeight + 10, titleWidth+ 110, titleHeight + 20, 30, 30);

        // Draw title text
        g2.setColor(Color.white);
        g2.drawString(title, 470, titleY+5);
        // Draw BACK and NEXT
        g2.setFont(g2.getFont().deriveFont(Font.ITALIC, 30F));
        FontMetrics fm = g2.getFontMetrics();

        String Description = "DESCRIPTION";
        int descX = 470;
        int descY = titleY + 100;
        int descWidth = fm.stringWidth(Description);
        int descHeight = fm.getHeight();

        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRoundRect(420, descY - descHeight, descWidth + 40, descWidth + 70, 30, 30);

        g2.setColor(Color.white);
        g2.drawString(Description, descX-25, descY);
        g2.drawImage(descChar4, descX-60, descY, 260, 260, null);

        // BACK button
        String backText = "BACK";
        int backX = 100;
        int backY = 500;
        int backWidth = fm.stringWidth(backText);
        int backHeight = fm.getHeight();

        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRoundRect(backX - 20, backY - backHeight, backWidth + 40, backHeight + 20, 30, 30);

        g2.setColor(Color.white);
        g2.drawString(backText, backX, backY);
        if (commandAbt == 1) {
            g2.drawString(">", backX - 40, backY);
        }


        // BACK button
        String chooseText = "CHOOSE";
        int chooseX = x;
        int chooseY = 500;
        int chooseWidth = fm.stringWidth(chooseText);
        int chooseHeight = fm.getHeight();

        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRoundRect(chooseX - 20, chooseY - chooseHeight, chooseWidth + 40, chooseHeight + 20, 30, 30);

        g2.setColor(Color.white);
        g2.drawString(chooseText, chooseX, chooseY);
        if (commandAbt == 0) {
            g2.drawString(">", chooseX - 40, backY);
        }

        // NEXT button
        String nextText = "Next";
        int nextX = 580;
        int nextY = 500;
        int nextWidth = fm.stringWidth(nextText);
        int nextHeight = fm.getHeight();

        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRoundRect(nextX + 10, nextY - nextHeight, nextWidth + 40, nextHeight + 20, 30, 30);

        g2.setColor(Color.white);
        g2.drawString(nextText, nextX + 30, nextY);
        if (commandAbt == 3) {
            g2.drawString(">", nextX - 50, nextY);
        }
    }

    public void drawElsa() {
        characterChoice = 3;
        g2.drawImage(back3, 0, 0, gp.screenWidth, gp.screenHeight, null);

        g2.drawImage(pfpEff3, 0, 400, gp.screenWidth/-2, gp.screenHeight/2, null);
//        g2.drawImage(pfpEff3, 500, 400, gp.screenWidth/-2, gp.screenHeight/2, null);
        g2.drawImage(pfpEff3, 350, 400, gp.screenWidth/-2, gp.screenHeight/2, null);

        g2.drawImage(pfpEff3, 0, 400, gp.screenWidth/2, gp.screenHeight/2, null);
        g2.drawImage(pfpEff3, 500, 400, gp.screenWidth/2, gp.screenHeight/2, null);
        g2.drawImage(pfpEff3, 350, 400, gp.screenWidth/2, gp.screenHeight/2, null);


        g2.drawImage(pfpEff32, 520, 380, gp.screenWidth/2, gp.screenHeight/2, null);

        g2.drawImage(profile3, -150, 0, 750, 600, null);


        g2.setFont(g2.getFont().deriveFont(Font.ITALIC, 60F));
        String title = "Elsa";
        int titleX = 500;
        int titleY = (gp.screenHeight / 4) - 50;

        int x = getXCenter(title);

        // Draw background rectangle behind title
        FontMetrics fmTitle = g2.getFontMetrics();
        int titleWidth = fmTitle.stringWidth(title);
        int titleHeight = fmTitle.getHeight();

        g2.setColor(new Color(0, 0, 0, 150)); // semi-transparent black
        g2.fillRoundRect(450, titleY - titleHeight + 10, titleWidth + 113, titleHeight + 20, 30, 30);

        // Draw title text
        g2.setColor(Color.white);
        g2.drawString(title, 510, titleY+5);

        // Draw BACK and NEXT
        g2.setFont(g2.getFont().deriveFont(Font.ITALIC, 30F));
        FontMetrics fm = g2.getFontMetrics();

        String Description = "DESCRIPTION";
        int descX = 470;
        int descY = titleY + 100;
        int descWidth = fm.stringWidth(Description);
        int descHeight = fm.getHeight();

        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRoundRect(450, descY - descHeight, descWidth + 40, descWidth + 70, 30, 30);

        g2.setColor(Color.white);
        g2.drawString(Description, descX, descY);
        g2.drawImage(descChar3, descX-30, descY, 260, 260, null);

        // BACK button
        String backText = "BACK";
        int backX = 100;
        int backY = 500;
        int backWidth = fm.stringWidth(backText);
        int backHeight = fm.getHeight();

        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRoundRect(backX - 20, backY - backHeight, backWidth + 40, backHeight + 20, 30, 30);

        g2.setColor(Color.white);
        g2.drawString(backText, backX, backY);
        if (commandAbt == 1) {
            g2.drawString(">", backX - 40, backY);
        }


        // BACK button
        String chooseText = "CHOOSE";
        int chooseX = x;
        int chooseY = 500;
        int chooseWidth = fm.stringWidth(chooseText);
        int chooseHeight = fm.getHeight();

        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRoundRect(chooseX - 20, chooseY - chooseHeight, chooseWidth + 40, chooseHeight + 20, 30, 30);

        g2.setColor(Color.white);
        g2.drawString(chooseText, chooseX, chooseY);
        if (commandAbt == 0) {
            g2.drawString(">", chooseX - 40, backY);
        }

        // NEXT button
        String nextText = "Next";
        int nextX = 580;
        int nextY = 500;
        int nextWidth = fm.stringWidth(nextText);
        int nextHeight = fm.getHeight();

        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRoundRect(nextX + 10, nextY - nextHeight, nextWidth + 40, nextHeight + 20, 30, 30);

        g2.setColor(Color.white);
        g2.drawString(nextText, nextX + 30, nextY);
        if (commandAbt == 3) {
            g2.drawString(">", nextX - 50, nextY);
        }
    }

    public void drawCinderella() {
        characterChoice = 2;
        g2.drawImage(back2, 0, 0, gp.screenWidth, gp.screenHeight, null);

//        g2.drawImage(pfpEff12, 0, 0, gp.screenWidth/2, gp.screenHeight/2, null);
//        g2.drawImage(pfpEff12, gp.screenWidth/2, 0, gp.screenWidth/2, gp.screenHeight/2, null);

//        g2.drawImage(pfpEff2, 0, 500, gp.screenWidth, gp.screenHeight, null);
//        g2.drawImage(pfpEff1, gp.screenWidth/2, gp.screenHeight/2, gp.screenWidth/2, gp.screenHeight/2, null);

        g2.drawImage(profile2, -175, 25, 720, 600, null);


        // Draw "Cinderella" title
        g2.setFont(g2.getFont().deriveFont(Font.ITALIC, 45F));
        String title = "Cinderella";
        int titleX = 500;
        int titleY = (gp.screenHeight / 4) - 50;

        int x = getXCenter(title);

        // Draw background rectangle behind title
        FontMetrics fmTitle = g2.getFontMetrics();
        int titleWidth = fmTitle.stringWidth(title);
        int titleHeight = fmTitle.getHeight();

        g2.setColor(new Color(0, 0, 0, 150)); // semi-transparent black
        g2.fillRoundRect(450, titleY - titleHeight + 10, titleWidth+ 50, titleHeight + 20, 30, 30);

        // Draw title text
        g2.setColor(Color.white);
        g2.drawString(title, 470, titleY+5);

        // Draw BACK and NEXT
        g2.setFont(g2.getFont().deriveFont(Font.ITALIC, 30F));
        FontMetrics fm = g2.getFontMetrics();

        String Description = "DESCRIPTION";
        int descX = 470;
        int descY = titleY + 100;
        int descWidth = fm.stringWidth(Description);
        int descHeight = fm.getHeight();

        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRoundRect(450, descY - descHeight, descWidth + 40, descWidth + 70, 30, 30);
        g2.drawImage(descChar2, descX-30, descY, 260, 260, null);

        g2.setColor(Color.white);
        g2.drawString(Description, descX, descY);


        // BACK button
        String backText = "BACK";
        int backX = 100;
        int backY = 500;
        int backWidth = fm.stringWidth(backText);
        int backHeight = fm.getHeight();

        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRoundRect(backX - 20, backY - backHeight, backWidth + 40, backHeight + 20, 30, 30);

        g2.setColor(Color.white);
        g2.drawString(backText, backX, backY);
        if (commandAbt == 1) {
            g2.drawString(">", backX - 40, backY);
        }


        // BACK button
        String chooseText = "CHOOSE";
        int chooseX = x;
        int chooseY = 500;
        int chooseWidth = fm.stringWidth(chooseText);
        int chooseHeight = fm.getHeight();

        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRoundRect(chooseX + 25, chooseY - chooseHeight, chooseWidth + 40, chooseHeight + 20, 30, 30);

        g2.setColor(Color.white);
        g2.drawString(chooseText, chooseX + 40, chooseY);
        if (commandAbt == 0) {
            g2.drawString(">", chooseX, backY);
        }

        // NEXT button
        String nextText = "Next";
        int nextX = 580;
        int nextY = 500;
        int nextWidth = fm.stringWidth(nextText);
        int nextHeight = fm.getHeight();

        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRoundRect(nextX + 10, nextY - nextHeight, nextWidth + 40, nextHeight + 20, 30, 30);

        g2.setColor(Color.white);
        g2.drawString(nextText, nextX + 30, nextY);
        if (commandAbt == 3) {
            g2.drawString(">", nextX - 50, nextY);
        }
    }

    public void drawAriel() {
        characterChoice = 1;
        g2.drawImage(back1, 0, 0, gp.screenWidth, gp.screenHeight, null);

        g2.drawImage(pfpEff12, 0, 0, gp.screenWidth/2, gp.screenHeight/2, null);
        g2.drawImage(pfpEff12, gp.screenWidth/2, 0, gp.screenWidth/2, gp.screenHeight/2, null);


        g2.drawImage(pfpEff1, 0, gp.screenHeight/2, gp.screenWidth/2, gp.screenHeight/2, null);
        g2.drawImage(pfpEff1, gp.screenWidth/2, gp.screenHeight/2, gp.screenWidth/2, gp.screenHeight/2, null);
        g2.drawImage(profile1, -175, 25, 750, 620, null);


        // Draw "Ariel" title
        g2.setFont(g2.getFont().deriveFont(Font.ITALIC, 60F));
        String title = "Ariel";
        int titleX = 500;
        int titleY = (gp.screenHeight / 4) - 50;

        int x = getXCenter(title);

        // Draw background rectangle behind title
        FontMetrics fmTitle = g2.getFontMetrics();
        int titleWidth = fmTitle.stringWidth(title);
        int titleHeight = fmTitle.getHeight();

        g2.setColor(new Color(0, 0, 0, 150)); // semi-transparent black
        g2.fillRoundRect(450, titleY - titleHeight + 10, titleWidth + 113, titleHeight + 20, 30, 30);

        // Draw title text
        g2.setColor(Color.white);
        g2.drawString(title, 510, titleY+5);

        // Draw BACK and NEXT
        g2.setFont(g2.getFont().deriveFont(Font.ITALIC, 30F));
        FontMetrics fm = g2.getFontMetrics();

        String Description = "DESCRIPTION";
        int descX = 470;
        int descY = titleY + 100;
        int descWidth = fm.stringWidth(Description);
        int descHeight = fm.getHeight();

        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRoundRect(450, descY - descHeight, descWidth + 40, descWidth + 70, 30, 30);
        g2.drawImage(descChar1, descX-30, descY, 260, 260, null);

        g2.setColor(Color.white);
        g2.drawString(Description, descX, descY);



        // BACK button
        String backText = "BACK";
        int backX = 100;
        int backY = 500;
        int backWidth = fm.stringWidth(backText);
        int backHeight = fm.getHeight();

        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRoundRect(backX - 20, backY - backHeight, backWidth + 40, backHeight + 20, 30, 30);

        g2.setColor(Color.white);
        g2.drawString(backText, backX, backY);
        if (commandAbt == 1) {
            g2.drawString(">", backX - 40, backY);
        }


        // BACK button
        String chooseText = "CHOOSE";
        int chooseX = x;
        int chooseY = 500;
        int chooseWidth = fm.stringWidth(chooseText);
        int chooseHeight = fm.getHeight();

        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRoundRect(chooseX - 20, chooseY - chooseHeight, chooseWidth + 40, chooseHeight + 20, 30, 30);

        g2.setColor(Color.white);
        g2.drawString(chooseText, chooseX, chooseY);
        if (commandAbt == 0) {
            g2.drawString(">", chooseX - 40, backY);
        }

        // NEXT button
        String nextText = "Next";
        int nextX = 580;
        int nextY = 500;
        int nextWidth = fm.stringWidth(nextText);
        int nextHeight = fm.getHeight();

        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRoundRect(nextX + 10, nextY - nextHeight, nextWidth + 40, nextHeight + 20, 30, 30);

        g2.setColor(Color.white);
        g2.drawString(nextText, nextX + 30, nextY);
        if (commandAbt == 3) {
            g2.drawString(">", nextX - 50, nextY);
        }
    }


    public void drawSettingScreen() {
        
    }

    public void drawAboutUsScreen() {
        g2.drawImage(dev1, 0, 0, gp.screenWidth, gp.screenHeight, null);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD,60F));
        String text = "Rafael Antonio S. Abella";
        int x = getXCenter(text);
        int y = gp.screenHeight/4;
        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRoundRect( x, y - 60, 700, 80, 30, 30);

        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRoundRect( x, y + 30, 700, 300, 30, 30);

        g2.drawImage(rafael, x+20 ,y + 60, 240, 240, null);

        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD,30F));
        text = "Programmer & Sound Design";
        x = getXCenter(text);
        y =  gp.screenHeight/4 + 300;
        g2.setColor(Color.white);
        g2.drawString(text, x + 130, y - 150);

        text = "BSIT 2 - G1 CSIT 228";
        x = getXCenter(text);
        y =  gp.screenHeight/4 + 350;
        g2.setColor(Color.white);
        g2.drawString(text, x + 120, y - 120);

        text = "23-2026-790";
        x = getXCenter(text);
        y =  gp.screenHeight/4 + 350;
        g2.setColor(Color.white);
        g2.drawString(text, x + 120, y - 60);

        text = "Member 1";
        x = getXCenter(text);
        y =  gp.screenHeight/4 + 350;
        g2.setColor(Color.white);
        g2.drawString(text, x + 120, y - 265);

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
        g2.drawImage(dev1, 0, 0, gp.screenWidth, gp.screenHeight, null);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD,60F));
        String text = "Trixie Ann V. Rentuma";
        int x = getXCenter(text);
        int y = gp.screenHeight/4;
        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRoundRect( x-10, y - 60, 700, 80, 30, 30);

        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRoundRect( x-10, y + 30, 700, 300, 30, 30);

        g2.drawImage(trixie, x+10 ,y + 60, 240, 240, null);

        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD,30F));
        text = "Programmer & File handling";
        x = getXCenter(text);
        y =  gp.screenHeight/4 + 300;
        g2.setColor(Color.white);
        g2.drawString(text, x + 140, y - 150);

        text = "BSIT 2 - G1 CSIT 228";
        x = getXCenter(text);
        y =  gp.screenHeight/4 + 350;
        g2.setColor(Color.white);
        g2.drawString(text, x + 120, y - 120);

        text = "23-1061-884";
        x = getXCenter(text);
        y =  gp.screenHeight/4 + 350;
        g2.setColor(Color.white);
        g2.drawString(text, x + 120, y - 60);

        text = "Member 2";
        x = getXCenter(text);
        y =  gp.screenHeight/4 + 350;
        g2.setColor(Color.white);
        g2.drawString(text, x + 120, y - 265);

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
        g2.drawImage(dev1, 0, 0, gp.screenWidth, gp.screenHeight, null);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD,42));
        String text = "Christian Earl V. Mahumot";
        int x = getXCenter(text)-75;
        int y = gp.screenHeight/4;
        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRoundRect( x, y - 60, 690, 80, 30, 30);

        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRoundRect( x, y + 30, 680, 300, 30, 30);

        g2.drawImage(earl, x+20 ,y + 60, 240, 240, null);

        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD,30F));
        text = "Assets & Graphics Design";
        x = getXCenter(text);
        y =  gp.screenHeight/4 + 300;
        g2.setColor(Color.white);
        g2.drawString(text, x + 130, y - 150);

        text = "BSIT 2 - G1 CSIT 228";
        x = getXCenter(text);
        y =  gp.screenHeight/4 + 350;
        g2.setColor(Color.white);
        g2.drawString(text, x + 120, y - 120);

        text = "23-7090-133";
        x = getXCenter(text);
        y =  gp.screenHeight/4 + 350;
        g2.setColor(Color.white);
        g2.drawString(text, x + 120, y - 60);

        text = "Member 3";
        x = getXCenter(text);
        y =  gp.screenHeight/4 + 350;
        g2.setColor(Color.white);
        g2.drawString(text, x + 120, y - 265);

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
        g2.drawImage(dev1, 0, 0, gp.screenWidth, gp.screenHeight, null);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD,60F));
        String text = "April John A. Sultan";
        int x = getXCenter(text)-60;
        int y = gp.screenHeight/4;
        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRoundRect( x, y - 60, 690, 80, 30, 30);

        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRoundRect( x, y + 30, 690, 300, 30, 30);

        g2.drawImage(april, x+20 ,y + 60, 240, 240, null);

        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD,30F));
        text = "Assets  & Character Design";
        x = getXCenter(text);
        y =  gp.screenHeight/4 + 300;
        g2.setColor(Color.white);
        g2.drawString(text, x + 130, y - 150);

        text = "BSIT 2 - G1 CSIT 228";
        x = getXCenter(text);
        y =  gp.screenHeight/4 + 350;
        g2.setColor(Color.white);
        g2.drawString(text, x + 120, y - 120);

        text = "23-5711-257";
        x = getXCenter(text);
        y =  gp.screenHeight/4 + 350;
        g2.setColor(Color.white);
        g2.drawString(text, x + 120, y - 60);

        text = "Member 4";
        x = getXCenter(text);
        y =  gp.screenHeight/4 + 350;
        g2.setColor(Color.white);
        g2.drawString(text, x + 120, y - 265);

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
        g2.drawImage(dev1, 0, 0, gp.screenWidth, gp.screenHeight, null);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD,60F));
        String text = "Keint Warren J. Poliquit";
        int x = getXCenter(text);
        int y = gp.screenHeight/4;
        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRoundRect( x, y - 60, 690, 80, 30, 30);

        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRoundRect( x, y + 30, 680, 300, 30, 30);

        g2.drawImage(keint, x+20 ,y + 60, 240, 240, null);

        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD,32F));
        text = "Programmer & UI Design";
        x = getXCenter(text);
        y =  gp.screenHeight/4 + 300;
        g2.setColor(Color.white);
        g2.drawString(text, x + 130, y - 150);

        text = "BSIT 2 - G1 CSIT 228";
        x = getXCenter(text);
        y =  gp.screenHeight/4 + 350;
        g2.setColor(Color.white);
        g2.drawString(text, x + 120, y - 120);

        text = "23-5004-291";
        x = getXCenter(text);
        y =  gp.screenHeight/4 + 350;
        g2.setColor(Color.white);
        g2.drawString(text, x + 120, y - 60);

        text = "Member 5";
        x = getXCenter(text);
        y =  gp.screenHeight/4 + 350;
        g2.setColor(Color.white);
        g2.drawString(text, x + 120, y - 265);

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

    public void drawChooseCharacterScreen() {
        drawAriel();
        switch(commandNum){
            case 1:
                drawAriel();
                break;
            case 2:
                drawCinderella();
                break;
            case 3:
                drawElsa();
                break;
            case 4:
                drawMoana();
                break;
            case 5:
                drawnMulan();
                break;
            case 6:
                drawSnowWhite();
                break;
            case 7:
                drawTiana();
                break;
            case 8:
                drawRapunzel();
                break;
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
