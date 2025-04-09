package main;

import entity.Entity;

public class CollisionChecker {

    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Entity entity) {
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX / gp.tileSize;
        int entityRightCol = entityRightWorldX / gp.tileSize;
        int entityTopRow = entityTopWorldY / gp.tileSize;
        int entityBottomRow = entityBottomWorldY / gp.tileSize;

        int tileNum1, tileNum2;

        switch (entity.direction) {

            case "up":
                entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                if (gp.tileM.tile[1].collision || gp.tileM.tile[2].collision || gp.tileM.tile[3].collision) {
                    entity.collisionOn = true;
                }
                break;

            case "right":
                entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if (gp.tileM.tile[1].collision || gp.tileM.tile[2].collision || gp.tileM.tile[3].collision) {
                    entity.collisionOn = true;
                }
                break;

            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                if (gp.tileM.tile[1].collision || gp.tileM.tile[2].collision || gp.tileM.tile[3].collision) {
                    entity.collisionOn = true;
                }
                break;
        }
    }

    // Check collision between Player and Dummy
    public void checkEntity(Entity entity, Entity target) {
        int entityLeftX = entity.worldX + entity.solidArea.x;
        int entityRightX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopY = entity.worldY + entity.solidArea.y;
        int entityBottomY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int targetLeftX = target.worldX + target.solidArea.x;
        int targetRightX = target.worldX + target.solidArea.x + target.solidArea.width;
        int targetTopY = target.worldY + target.solidArea.y;
        int targetBottomY = target.worldY + target.solidArea.y + target.solidArea.height;

        if (entityRightX > targetLeftX &&
                entityLeftX < targetRightX &&
                entityBottomY > targetTopY &&
                entityTopY < targetBottomY) {
            entity.collisionOn = true; // Collision detected
        }
    }

    // New method to check and enforce screen boundaries
    public void checkScreenBoundaries(Entity entity) {
        // Get screen boundaries
        int leftBoundary = 0;
        int rightBoundary = gp.screenWidth - (gp.tileSize * 2); // Account for entity width (2 tiles)

        // Check and correct X position
        if (entity.worldX < leftBoundary) {
            entity.worldX = leftBoundary;
            entity.collisionOn = true;
        }
        if (entity.worldX > rightBoundary) {
            entity.worldX = rightBoundary;
            entity.collisionOn = true;
        }

        // For a fighting game, we typically only restrict horizontal movement
        // but you can add vertical restrictions if needed:

        // int topBoundary = 0;
        // int bottomBoundary = gp.screenHeight - (gp.tileSize * 2);
        // if (entity.worldY < topBoundary) {
        //     entity.worldY = topBoundary;
        //     entity.collisionOn = true;
        // }
        // if (entity.worldY > bottomBoundary) {
        //     entity.worldY = bottomBoundary;
        //     entity.collisionOn = true;
        // }
    }
}