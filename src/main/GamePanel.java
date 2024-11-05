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

/**
 *
 * @author DELL 3400
 */
public class GamePanel extends JPanel {

    private float xDelta = 100, yDelta = 100;
    //   private float xDir = 1f, yDir = 1f;
    private MouseInputs mouseInputs;
    private int frames = 0;
    private long lastCheck = 0;
    private BufferedImage img,subImg;
    //   private Color color = new Color(90, 15, 5);
//  private Random random;

    public GamePanel() throws IOException {
        // random = new Random();
        mouseInputs = new MouseInputs(this);
        setPanelSize();
        importImg();
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    public void changeXDelta(int value) {
        this.xDelta += value;

    }

    public void changeYDelta(int value) {
        this.yDelta += value;

    }

    public void setRectPos(int x, int y) {
        this.xDelta = x;
        this.yDelta = y;

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        subImg=img.getSubimage(1*64, 8*40, 64, 40);
       g.drawImage(subImg,(int) xDelta,(int) yDelta, 64, 40, null);
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
    private void setPanelSize() {
        Dimension size = new Dimension(1280, 800);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }

    private void importImg() throws IOException {
        InputStream is = getClass().getResourceAsStream("/images/player_sprites.png");
        try{
        img = ImageIO.read(is);
    } catch(IOException e){
        e.printStackTrace();
    }
    }
}
