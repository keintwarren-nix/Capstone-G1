    package Tile;

    import main.GamePanel;

    import javax.imageio.ImageIO;
    import java.awt.*;
    import java.awt.image.BufferedImage;
    import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStream;
    import java.io.InputStreamReader;

    public class tileManager {

        GamePanel gp;
        public Tile[] tile;
        public int[][] mapTileNum;


        public tileManager(GamePanel gp) {
            this.gp = gp;
            tile = new Tile[10];
            mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

            getTileImage();
            loadMap("/maps/map01.txt");
        }

        public void getTileImage() {
            try {
                tile[0] = new Tile();
                tile[0].image = ImageIO.read(getClass().getResource("/res/tiles/tile.png"));
                tile[0].collision = true;

                tile[1] = new Tile();
                tile[1].image = ImageIO.read(getClass().getResource("/res/tiles/grass1.png"));
                tile[1].collision = false;


                tile[2] = new Tile();
                tile[2].image = ImageIO.read(getClass().getResource("/res/tiles/grass2.png"));
                tile[2].collision = false;


                tile[3] = new Tile();
                tile[3].image = ImageIO.read(getClass().getResource("/res/tiles/grass3.png"));
                tile[3].collision = false;

                tile[4] = new Tile();
                tile[4].image = ImageIO.read(getClass().getResource("/res/tiles/dirt1.png"));
                tile[4].collision = true;

                tile[5] = new Tile();
                tile[5].image = ImageIO.read(getClass().getResource("/res/tiles/dirt2.png"));
                tile[5].collision = true;

                tile[6] = new Tile();
                tile[6].image = ImageIO.read(getClass().getResource("/res/tiles/dirt3.png"));
                tile[6].collision = true;

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void loadMap(String filePath) {
            try {
                InputStream is = getClass().getResourceAsStream(filePath);
                BufferedReader br = new BufferedReader(new InputStreamReader(is));

                int col = 0;
                int row = 0;

                while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
                    String line = br.readLine();
                    while (col < gp.maxScreenCol) {
                        String numbers[] = line.split(" ");
                        int num = Integer.parseInt(numbers[col]);

                        mapTileNum[col][row] = num;
                        col++;

                        if (col == gp.maxScreenRow) {
                            col = 0;
                            row++;
                        }
                    }
                }
                br.close();

            } catch (Exception e) {

            }
        }

        public void draw(Graphics g2) throws IOException {
            int worldCol = 0, worldRow = 0, x = 0, y = 500;

            while (worldCol < gp.maxWorldRow && worldRow < gp.maxWorldRow) {

                int tileNum;

                if (worldRow == 0) {
                    if (worldCol == 0) {
                        tileNum = 1;
                    } else if (worldCol == 15) {
                        tileNum = 3;
                    } else {
                        tileNum = 2;
                    }
                } else if (worldRow == 1) {
                    if (worldCol == 0) {
                        tileNum = 4;
                    } else if (worldCol == 15) {
                        tileNum = 6;
                    } else {
                        tileNum = 5;
                    }
                } else {
                    tileNum = 0;
                }

                g2.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);

                worldCol++;
                x += gp.tileSize;

                if (worldCol == gp.maxScreenCol) {
                    worldCol = 0;
                    x = 0;
                    worldRow++;
                    y += gp.tileSize;
                }
            }
        }

    }
