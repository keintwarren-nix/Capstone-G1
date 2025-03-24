package main;

import object.iconCinder;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp){
    this.gp = gp;
    }

    public void setObject() {
        gp.obj/*[mapNum]*/[0] = new iconCinder(gp);
    }
}
