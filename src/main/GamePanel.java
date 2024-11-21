/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import input.KeyboardInputs;
import input.MouseInputs;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import static utilz.Constains.PlayerConstants.*;
import static utilz.Constains.Directions.*;
import static main.Game.GAME_HEIGHT;
import static main.Game.GAME_WIDTH;
/**
 *
 * @author DELL 3400
 */
public class GamePanel extends JPanel {
	
    private MouseInputs mouseInputs;
    private Game game;
   
    public GamePanel(Game game) throws IOException  {
        // random = new Random();
        mouseInputs = new MouseInputs(this);
   this.game=game;
        setPanelSize();
       
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }
public Game getGame() {
	return game;
}
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        game.render(g);
      //  subImg=img.getSubimage(1*64, 8*40, 64, 40);
      
     
      //  g.drawImage(img.getSubimage(0, 0, 64, 40), 0, 0, null);
      
        /*   updateRectangle();
        g.setColor(color);
        g.fillRect((int) xDelta, (int) yDelta, 200, 50);
         */
 /*  frames++;
        if (System.currentTimeMillis() - lastCheck >= 1000) {
        lastCheck = System.currentTimeMillis();
        System.out.println("FPS: " + frames);
        frames = 0;
        }
         */
    }

	/*  private void updateRectangle() {
        xDelta += xDir;
        if (xDelta > 400 || xDelta < 0) {
            xDir *= -1;
            color= getRndColor();
        }
        yDelta += yDir;
        if (yDelta > 400 || yDelta < 0) {
            yDir *= -1;
        }
    }

    
    private Color getRndColor(){
        int r=random.nextInt(255);
        int b=random.nextInt(255);
        int g=random.nextInt(255);
        return new Color(r,g,b);
        
    }
     */
	public void updateGame() {
		
	}
    private void setPanelSize() {
    	 Dimension size = new Dimension(GAME_WIDTH,GAME_HEIGHT);
       // Dimension size = new Dimension(1280, 800);
        //setMinimumSize(size);
        setPreferredSize(size);
        //setMaximumSize(size);
        System.out.println("size :"+ GAME_WIDTH+ ";" + GAME_HEIGHT);
        
    }

    }
