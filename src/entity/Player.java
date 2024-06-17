package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {
	GamePanel gp;
	KeyHandler keyHandler;
	public final int screenX;
	public final int screenY;
	public int hasKeys = 0;

	public Player(GamePanel gp, KeyHandler keyHandler) {
		this.gp = gp;
		this.keyHandler = keyHandler;

		screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
		screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

		solidArea = new Rectangle();
		solidArea.x = 8;
		solidArea.y = 16;
		solidArea.width = 30;
		solidArea.height = 30;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;

		setDefaultValues();
		getPlayerImage();
	}

	public void getPlayerImage() {
		try {
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setDefaultValues() {
		worldX = gp.tileSize * 23;
		worldY = gp.tileSize * 21;
		speed = 4;
		direction = "down";
	}

	public void update() {
		if (keyHandler.downPressed || keyHandler.rightPressed || keyHandler.leftPressed || keyHandler.upPressed) {

			if (keyHandler.upPressed) {
				direction = "up";
			} else if (keyHandler.downPressed) {
				direction = "down";
			} else if (keyHandler.leftPressed) {
				direction = "left";
			} else if (keyHandler.rightPressed) {
				direction = "right";
			}
			// Check tile collision
			collisionOn = false;
			gp.cChecker.checkTile(this);
			// Check Object Collision
			int objIdx = gp.cChecker.checkObject(this, true);
			pickUpObject(objIdx);

			if (!collisionOn) {
				switch (direction) {
				case "up":
					worldY -= speed;
					break;
				case "down":
					worldY += speed;
					break;
				case "left":
					worldX -= speed;
					break;
				case "right":
					worldX += speed;
					break;
				}
			}
			spriteCounter++;

			if (spriteCounter > 12) {
				if (spriteNum == 1) {
					spriteNum = 2;
				} else if (spriteNum == 2) {
					spriteNum = 1;
				}
				spriteCounter = 0;

			}
		}

	}

	public void pickUpObject(int idx) {
		if (idx != 999) {
			String objName = gp.obj[idx].name;
			switch (objName) {
			case "Key":
				gp.playSE(1);
				hasKeys++;
				gp.obj[idx] = null;
				gp.ui.toast("You got a key!");
				break;
			case "Door":
				if (hasKeys > 0) {
					gp.playSE(3);
					gp.obj[idx] = null;
					hasKeys--;
					gp.ui.toast("Door opened!");
				}else {
					gp.ui.toast("You need a key!");
				}
				break;
			case "Boots":
				gp.playSE(2);
				speed += 1;
				gp.obj[idx] = null;
				gp.ui.toast("Speed UP!");
				break;
			case "Chest":
				gp.ui.ChapterFisnhed = true;
				gp.stopMusic();
				gp.playSE(4);
				break;
			}
		}

	}

	public void draw(Graphics2D graphics2d) {
		BufferedImage image = null;
		switch (direction) {
		case "up":
			if (spriteNum == 1) {
				image = up1;
			}
			if (spriteNum == 2) {
				image = up2;
			}
			break;
		case "down":
			if (spriteNum == 1) {
				image = down1;
			}
			if (spriteNum == 2) {
				image = down2;
			}
			break;
		case "left":
			if (spriteNum == 1) {
				image = left1;
			}
			if (spriteNum == 2) {
				image = left2;
			}
			break;
		case "right":
			if (spriteNum == 1) {
				image = right1;
			}
			if (spriteNum == 2) {
				image = right2;
			}
			break;
		}
		graphics2d.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
	}
}
