//package main;
//
//import java.awt.*;
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;
//
//public class MouseHandler extends MouseAdapter {
//
//    GamePanel gp;
//
//    public MouseHandler(GamePanel gp) {
//        this.gp = gp;
//    }
//
//    @Override
//    public void mouseMoved(MouseEvent e) {
//        gp.mouseX = e.getX();
//        gp.mouseY = e.getY();
//
//        boolean hovering = false;
//
//        if (gp.gameState == gp.titleState) {
//            for (int i = 0; i < gp.ui.menuButtons.length; i++) {
//                Rectangle rect = gp.ui.menuButtons[i];
//                if (rect != null && rect.contains(gp.mouseX, gp.mouseY)) {
//                    if (gp.ui.commandNum != i) {
//                        gp.ui.commandNum = i;
//                        gp.repaint(); // <-- force repaint
//                    }
//                    gp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//                    hovering = true;
//                    break;
//                }
//            }
//        } else if (gp.gameState == gp.choosingState) {
//            for (int i = 0; i < gp.ui.chooseButtons.length; i++) {
//                Rectangle rect = gp.ui.chooseButtons[i];
//                if (rect != null && rect.contains(gp.mouseX, gp.mouseY)) {
//                    if (gp.ui.commandNum != i) {
//                        gp.ui.commandNum = i;
//                        gp.repaint(); // <-- force repaint
//                    }
//                    gp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//                    hovering = true;
//                    break;
//                }
//            }
//        }
//
//        if (!hovering) {
//            gp.setCursor(Cursor.getDefaultCursor());
//        }
//    }
//
//    @Override
//    public void mouseDragged(MouseEvent e) {
//        int x = e.getX();
//
//        if (gp.ui.draggingVolume) {
//            int newVol = Math.max(0, Math.min(100, (x - gp.ui.volumeSliderRect.x) / 2));
//            gp.ui.musicVolume = newVol;
//            gp.sound.setVolume(newVol);
//        }
//
//        if (gp.ui.draggingBrightness) {
//            int newBright = Math.max(0, Math.min(100, (x - gp.ui.brightnessSliderRect.x) / 2));
//            gp.ui.brightness = newBright;
//            // Apply brightness changes here
//        }
//    }
//
//    @Override
//    public void mouseReleased(MouseEvent e) {
//        gp.ui.draggingVolume = false;
//        gp.ui.draggingBrightness = false;
//    }
//
//
//    @Override
//    public void mousePressed(MouseEvent e) {
//        int x = e.getX();
//        int y = e.getY();
//
//        if (gp.gameState == gp.titleState) {
//            for (int i = 0; i < gp.ui.menuButtons.length; i++) {
//                Rectangle rect = gp.ui.menuButtons[i];
//                if (rect != null && rect.contains(e.getX(), e.getY())) {
//                    if (i == 0) { // PLAY
//                        gp.gameState = gp.choosingState;
//                    } else if (i == 1) {
//                        gp.gameState = gp.STATE_MENU;
//                    } else if (i == 2) {
//                        gp.gameState = gp.STATE_ABOUT;
//                    } else if (i == 3) {
//                        System.exit(0);
//                    }
//                }
//            }
//        }
//        if (gp.gameState == gp.choosingState) {
//            for (int i = 0; i < gp.ui.chooseButtons.length; i++) {
//                Rectangle rect = gp.ui.chooseButtons[i];
//                if (rect != null && rect.contains(e.getX(), e.getY())) {
//                    if (i == 0 || i == 1) { // PLAYER VS NPC or PLAYER VS PLAYER
//                        gp.gameState = gp.playState;
//                    } else if (i == 2) { // EXIT
//                        gp.gameState = gp.titleState;
//                    }
//                }
//            }
//        }
//        if (gp.gameState == gp.STATE_MENU) {
//            if (gp.ui.toggleMusicRect != null && gp.ui.toggleMusicRect.contains(x, y)) {
//                gp.ui.musicOn = !gp.ui.musicOn;
//                gp.sound.setMusicOn(gp.ui.musicOn); // Optional method to mute/unmute
//            }
//
//            if (gp.ui.volumeSliderRect != null && gp.ui.volumeSliderRect.contains(x, y)) {
//                gp.ui.draggingVolume = true;
//                int newVol = Math.max(0, Math.min(100, (x - gp.ui.volumeSliderRect.x) / 2));
//                gp.ui.musicVolume = newVol;
//                gp.sound.setVolume(newVol); // Make sure this is implemented
//            }
//
//            if (gp.ui.brightnessSliderRect != null && gp.ui.brightnessSliderRect.contains(x, y)) {
//                gp.ui.draggingBrightness = true;
//                int newBright = Math.max(0, Math.min(100, (x - gp.ui.brightnessSliderRect.x) / 2));
//                gp.ui.brightness = newBright;
//                // You can apply brightness changes here if implemented
//            }
//
//            Rectangle backRect = new Rectangle(gp.tileSize * 2, gp.tileSize * 8 - 30, 100, 40);
//            if (backRect.contains(x, y)) {
//                gp.gameState = gp.titleState;
//            }
//        }
//    }
//}