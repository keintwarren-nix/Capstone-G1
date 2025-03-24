package main;

import object.Icon;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp){
    this.gp = gp;
    }

    public void setObject() {
        gp.obj[0] = new Icon();
        gp.obj[0].worldX = 20;
        gp.obj[0].worldY = 4;
    }
}
