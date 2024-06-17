package objects;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Chest extends SuperObjects {
	public Chest() {
		name = "Chest";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/chest.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
