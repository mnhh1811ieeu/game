/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import main.GamePanel;

/**
 *
 * @author DELL 3400
 */
public class MouseInputs implements MouseListener,MouseMotionListener{
    private GamePanel gamePanel;
    public MouseInputs (GamePanel gamePanel){
        this.gamePanel=gamePanel;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("mouse click");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
     
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
       
    }

    @Override
    public void mouseDragged(MouseEvent e) {
       
    }

    @Override
    public void mouseMoved(MouseEvent e) {
       gamePanel.setRectPos(e.getX(), e.getY());
    }
    
}
