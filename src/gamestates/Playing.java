package gamestates;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import Levels.LevelManager;
import entities.Player;
import main.Game;

public class Playing extends State implements Statemethods{
	private Player player;
	private LevelManager levelManager;
	public Playing(Game game) {
		super(game);
		initClasses();
	}

	
	private void initClasses() {
		// TODO Auto-generated method stub
		levelManager = new LevelManager(game);
		player = new Player(200, 200, (int) (Game.SCALE * 64), (int) (Game.SCALE * 40));
		player.loadLvData(levelManager.getCurrentLevel().getLevelData());
	}
	public Player getPlayer() {
		return player;
	}

	public void windowFocusLost() {
		player.resetDirBooleans();
	}


	@Override
	public void update() {
		levelManager.update();
		player.update();
	}


	@Override
	public void draw(Graphics g) {
		levelManager.draw(g);
		player.render(g);
		
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1)
			player.setAttacking(true);
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			player.setJump(true);
			break;
		case KeyEvent.VK_LEFT:
			player.setLeft(true);
			break;
//		case KeyEvent.VK_DOWN:
//			player.setDown(true);
//			break;
		case KeyEvent.VK_RIGHT:
			player.setRight(true);
			break;
		case KeyEvent.VK_BACK_SPACE:
			Gamestate.state=Gamestate.MENU;
			break;
		}
	}


	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			player.setJump(false);
			break;
		case KeyEvent.VK_LEFT:
			player.setLeft(false);
			break;
//		case KeyEvent.VK_DOWN:
//			gamePanel.getGame().getPlayer().setDown(false);
//			break;
		case KeyEvent.VK_RIGHT:
			player.setRight(false);
			break;
//		case KeyEvent.VK_SPACE:
//			gamePanel.getGame().getPlayer().setJump(false);
//			break;
		}
	}
}
