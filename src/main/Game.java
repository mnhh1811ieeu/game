/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.awt.Graphics;
import java.io.IOException;


import gamestates.Gamestate;
import gamestates.Menu;
import gamestates.Playing;

/**
 *
 * @author DELL 3400
 */
public class Game implements Runnable {

	private GameWindow gameWindow;
	private GamePanel gamePanel;
	private Thread gameThread;
	private final int FPS_SET = 120;
	private final int UPS_SET = 200;
	private Playing  playing;
	private Menu menu;
	
	public final static int TILES_DEFAULT_SIZE = 33;
	public final static float SCALE = 1.5f;
	public final static int TITLES_IN_WIDTH = 26;
	public final static int TITLES_IN_HEIGHT = 14;
	public final static int TITLES_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE);
	public final static int GAME_WIDTH = TITLES_SIZE * TITLES_IN_WIDTH;
	public final static int GAME_HEIGHT = TITLES_SIZE * TITLES_IN_HEIGHT;

	public Game() throws IOException {
		initClasses();
		gamePanel = new GamePanel(this);
		gameWindow = new GameWindow(gamePanel);
		gamePanel.requestFocus();

		startGameLoop();
	}

	private void initClasses() {
		menu= new Menu(this);
		playing = new Playing(this);
			}

	private void startGameLoop() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {
		double timePerFrame = 1000000000.0 / FPS_SET;
		double timerPerUpdate = 1000000000.0 / UPS_SET;
		// long lastFrame = System.nanoTime();
		// long now=System.nanoTime();
		long previousTime = System.nanoTime();
		int updates = 0;
		int frames = 0;
		long lastCheck = System.currentTimeMillis();
		double deltaU = 0;
		double deltaF = 0;
		while (true) {
			// now=System.nanoTime();
			long currentTime = System.nanoTime();

			deltaU += (currentTime - previousTime) / timerPerUpdate;
			deltaF += (currentTime - previousTime) / timePerFrame;
			previousTime = currentTime;
			if (deltaU >= 1) {
				update();
				updates++;
				deltaU--;
			}
			if (deltaF >= 1) {
				gamePanel.repaint();
				// lastFrame= now;
				frames++;
				deltaF--;
			}

			if (System.currentTimeMillis() - lastCheck >= 1000) {
				lastCheck = System.currentTimeMillis();
				System.out.println("FPS: " + frames + " | UPS: " + updates);
				frames = 0;
				updates = 0;
			}
		}

	}

	private void update() {
		// TODO Auto-generated method stub
		
		switch(Gamestate.state) {
		case MENU:
			menu.update();
			break;
		case PLAYING:
			playing.update();
			break;
		default:
			break;
		
		}
	}

	public void render(Graphics g) {
		switch(Gamestate.state) {
		case MENU:
			menu.draw(g);
			break;
		case PLAYING:
			playing.draw(g);
			break;
		default:
			break;
		
		}
		

	}
public Menu getMenu() {
	return menu;
}
	public Playing getPlaying() {
		return playing;
	}

	public void windowFocusLost() {
		if(Gamestate.state==Gamestate.PLAYING) {
			playing.getPlayer().resetDirBooleans();
		}
	}

}
