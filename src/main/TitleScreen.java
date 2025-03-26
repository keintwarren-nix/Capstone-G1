package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TitleScreen extends JPanel implements KeyListener, ActionListener {
    GamePanel gp;
    JButton playButton;
    boolean enterPressed = false;

    public TitleScreen(GamePanel gp) {
        this.gp = gp;
        this.setPreferredSize(new Dimension(gp.screenWidth, gp.screenHeight));
        this.setBackground(Color.WHITE);
        this.setLayout(null);

        playButton = new JButton("Play Game");
        playButton.setFont(new Font("Arial", Font.BOLD, 30));
        playButton.setForeground(Color.BLACK);
        playButton.setBackground(Color.LIGHT_GRAY);
        playButton.setFocusPainted(false);
        playButton.setBounds(gp.screenWidth / 2 - 100, gp.screenHeight / 2 + 50, 200, 50);
        playButton.addActionListener(this);

        this.add(playButton);
        this.setFocusable(true);
        this.addKeyListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setFont(new Font("Arial", Font.BOLD, 50));
        g2.setColor(Color.BLACK);
        String title = "GAME TITLE";
        int titleWidth = g2.getFontMetrics().stringWidth(title);
        g2.drawString(title, (gp.screenWidth - titleWidth) / 2, gp.screenHeight / 2 - 50);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER && !enterPressed) {
            enterPressed = true;
            startGame();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == playButton) {
            startGame();
        }
    }

    private void startGame() {
        gp.gameState = gp.playState;
        gp.remove(this);
        gp.requestFocusInWindow();
        gp.startGameThread();
    }

    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}
}
