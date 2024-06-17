package main;

import objects.Chest;
import objects.Door;
import objects.Key;

public class AssetsSetter {
	
	GamePanel gp;
	
	public AssetsSetter(GamePanel gp) {
		
		this.gp = gp;
	}
	
	public void setObjects() {
		gp.obj[0] = new Key();
		gp.obj[0].worldX = 23 * gp.tileSize;
		gp.obj[0].worldY = 7 * gp.tileSize;
		
		gp.obj[1] = new Key();
		gp.obj[1].worldX = 23 * gp.tileSize;
		gp.obj[1].worldY = 40 * gp.tileSize;
		
		gp.obj[2] = new Key();
		gp.obj[2].worldX = 39 * gp.tileSize;
		gp.obj[2].worldY = 10 * gp.tileSize;
		
		gp.obj[3] = new Door();
		gp.obj[3].worldX = 10 * gp.tileSize;
		gp.obj[3].worldY = 11 * gp.tileSize;
		
		gp.obj[4] = new Door();
		gp.obj[4].worldX = 8 * gp.tileSize;
		gp.obj[4].worldY = 28 * gp.tileSize;
		
		gp.obj[5] = new Door();
		gp.obj[5].worldX = 12 * gp.tileSize;
		gp.obj[5].worldY = 22 * gp.tileSize;
		
		gp.obj[5] = new Chest();
		gp.obj[5].worldX = 10 * gp.tileSize;
		gp.obj[5].worldY = 7 * gp.tileSize;	
	}
}