package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import objects.Objects;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {
	// Screen Settings
	final int originalTileSize = 16; // ?16x16 tile
	final int scale = 3;
	public final int tileSize = originalTileSize * scale; // ?48x48 tile
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12;
	public final int screenWidth = tileSize * maxScreenCol; // ?768 pixels
	public final int screenHeight = tileSize * maxScreenRow; // ?576 pixels
	// World Settings
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;

	// FPS
	int FPS = 90;
	// System
	TileManager tileManager = new TileManager(this);
	KeyHandler keyHandler = new KeyHandler();
	Thread gameThread;
	Sound sound = new Sound();
	public AssetsSetter aSetter = new AssetsSetter(this);
	public CollisionChecker cChecker = new CollisionChecker(this);

	// Entity and Objects
	public Player player = new Player(this, keyHandler);
	public Objects obj[] = new Objects[10];

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

	public void setUpGame() {
		aSetter.setObjects();
		playMusic(0);
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

		tileManager.draw(graphics2d);

		for (int i = 0; i < obj.length; i++) {
			if (obj[i] != null) {
				obj[i].draw(graphics2d, this);
			}
		}

		player.draw(graphics2d);

		graphics2d.dispose();
	}

	public void playMusic(int idx) {
		sound.setFile(idx);
		sound.play();
		sound.loop();
	}

	public void stopMusic() {
		sound.stop();
	}

	// Play sound Effect
	public void playSE(int idx) {
		sound.setFile(idx);
		sound.play();
	}
}
