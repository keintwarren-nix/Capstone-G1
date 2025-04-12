//package main;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.image.BufferedImage;
//import javax.imageio.ImageIO;
//import java.io.File;
//import java.io.IOException;
//
//public class MapSelectionFrame extends JFrame {
//
//    private String selectedMap;
//    private final GamePanel gp;
//
//    // Filenames without extension (for display & loading)
//    private final String[] mapNames = {
//            "Ariel_BackgroundMap",
//            "Cinderella_BackgroundMap",
//            "Elsa_BackgroundMap",
//            "Moana_BackgroundMap",
//            "Mulan_BackgroundMap",
//            "Rapunzel_BackgroundMap",
//            "SnowWhite_BackgroundMap",
//            "Tiana_BackgroundMap"
//    };
//
//    public MapSelectionFrame(GamePanel gp) {
//        this.gp = gp;
//
//        setTitle("Select Map");
//        setExtendedState(JFrame.MAXIMIZED_BOTH);
//        setUndecorated(true);
//        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//
//        JPanel mainPanel = new JPanel(new BorderLayout());
//        mainPanel.setBackground(Color.BLACK);
//
//        JPanel mapsPanel = new JPanel(new GridLayout(2, 4, 20, 20));
//        mapsPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
//        mapsPanel.setBackground(Color.DARK_GRAY);
//
//        for (String map : mapNames) {
//            JButton mapButton = createMapButton(map);
//            mapsPanel.add(mapButton);
//        }
//
//        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 20));
//        buttonPanel.setBackground(Color.DARK_GRAY);
//
//        JButton confirmButton = new JButton("Confirm");
//        confirmButton.setPreferredSize(new Dimension(200, 50));
//        confirmButton.addActionListener(e -> {
//            if (selectedMap != null) {
//                try {
//                    // Try to load image from resources first
//                    BufferedImage selectedImage = loadMapImage(selectedMap);
//
//                    if (selectedImage != null) {
//                        gp.setBackgroundImage(selectedImage);
//                        System.out.println("Selected map: " + selectedMap);
//                        dispose();
//                    } else {
//                        JOptionPane.showMessageDialog(this,
//                                "Could not load map image: " + selectedMap,
//                                "Error Loading Map",
//                                JOptionPane.ERROR_MESSAGE);
//                    }
//                } catch (IOException ex) {
//                    ex.printStackTrace();
//                    JOptionPane.showMessageDialog(this,
//                            "Error loading map: " + ex.getMessage(),
//                            "Map Loading Error",
//                            JOptionPane.ERROR_MESSAGE);
//                }
//            } else {
//                JOptionPane.showMessageDialog(this,
//                        "Please select a map first.",
//                        "No Map Selected",
//                        JOptionPane.WARNING_MESSAGE);
//            }
//        });
//
//        JButton backButton = new JButton("Back");
//        backButton.setPreferredSize(new Dimension(200, 50));
//        backButton.addActionListener(e -> dispose());
//
//        buttonPanel.add(confirmButton);
//        buttonPanel.add(backButton);
//
//        mainPanel.add(mapsPanel, BorderLayout.CENTER);
//        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
//
//        setContentPane(mainPanel);
//        setVisible(true);
//    }
//
//    private JButton createMapButton(String mapName) {
//        JButton button = new JButton();
//
//        try {
//            // Try to load thumbnail image
//            BufferedImage image = loadMapImage(mapName);
//
//            if (image != null) {
//                Image scaledImage = image.getScaledInstance(200, 120, Image.SCALE_SMOOTH);
//                button.setIcon(new ImageIcon(scaledImage));
//            } else {
//                // Fallback to text if image can't be loaded
//                button.setText(mapName.replace("_", " "));
//                System.err.println("Could not load image for: " + mapName);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//            button.setText(mapName.replace("_", " "));
//        }
//
//        button.setPreferredSize(new Dimension(200, 120));
//        button.setBackground(Color.GRAY);
//        button.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
//
//        button.addActionListener(e -> {
//            // Highlight the selected button
//            for (Component c : button.getParent().getComponents()) {
//                if (c instanceof JButton) {
//                    c.setBackground(Color.GRAY);
//                }
//            }
//            button.setBackground(new Color(100, 180, 255)); // Highlight color
//
//            selectedMap = mapName;
//            System.out.println("Map selected: " + mapName);
//        });
//
//        return button;
//    }
//
//    private BufferedImage loadMapImage(String mapName) throws IOException {
//        BufferedImage image = null;
//
//        // Try multiple file formats and locations
//        String[] extensions = {".gif", ".png", ".jpg"};
//        String[] paths = {
//                "/background/",  // Resource path
//                "res/background/",  // Alternative resource path
//                "resources/background/",  // Another common path
//                "assets/background/"  // Yet another common path
//        };
//
//        // First try to load from resources (inside JAR)
//        for (String path : paths) {
//            for (String ext : extensions) {
//                try {
//                    image = ImageIO.read(getClass().getResourceAsStream(path + mapName + ext));
//                    if (image != null) {
//                        System.out.println("Loaded image from resource: " + path + mapName + ext);
//                        return image;
//                    }
//                } catch (Exception e) {
//                    // Continue trying other paths
//                }
//            }
//        }
//
//        // Then try to load from file system
//        for (String path : paths) {
//            for (String ext : extensions) {
//                try {
//                    File file = new File(path + mapName + ext);
//                    if (file.exists()) {
//                        image = ImageIO.read(file);
//                        if (image != null) {
//                            System.out.println("Loaded image from file: " + file.getAbsolutePath());
//                            return image;
//                        }
//                    }
//                } catch (Exception e) {
//                    // Continue trying other paths
//                }
//            }
//        }
//
//        return null; // Return null if no image could be loaded
//    }
//}