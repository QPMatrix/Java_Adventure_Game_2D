package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;

public class GamePanel extends JPanel implements Runnable {
	final int originalTileSize = 16; // ?16x16 tile
	final int scale = 3;
	public final int tileSize = originalTileSize * scale; // ?48x48 tile
	final int maxScreenCol = 16;
	final int maxScreenRow = 12;
	final int screenWidth = tileSize * maxScreenCol; // ?768 pixels
	final int screenHeight = tileSize * maxScreenRow; // ?576 pixels
	int FPS = 90;
	Thread gameThread;
	KeyHandler keyHandler = new KeyHandler();
	Player player = new Player(this, keyHandler);
	// ?Set Player Default Position
	int PlayerX = 100;
	int PlayerY = 100;
	int PlayerSpeed = 4;

	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.BLACK);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyHandler);
		this.setFocusable(true);
	}

	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {
		double drawInterval = 1000000000 / FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer = 0;
		int drawCount = 0;

		while (gameThread != null) {
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / drawInterval;
			timer += (currentTime - lastTime);
			lastTime = currentTime;
			if (delta >= 1) {
				update();
				repaint();
				delta--;
				drawCount++;
			}
			if (timer >= 1000000000) {
				System.out.println("FPS" + drawCount);
				drawCount = 0;
				timer = 0;
			}
		}
	}

	public void update() {
		player.update();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D graphics2d = (Graphics2D) g;
		player.draw(graphics2d);
		graphics2d.dispose();
	}
}
