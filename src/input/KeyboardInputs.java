/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import main.GamePanel;

/**
 *
 * @author DELL 3400
 */
public class KeyboardInputs implements KeyListener {

    private GamePanel gamePanel;

    public KeyboardInputs(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //h
    }

    @Override
    public void keyPressed(KeyEvent e) {
        /* switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
             gamePanel.changeYDelta(-5);
                break;
            case KeyEvent.VK_A:
              gamePanel.changeXDelta(-5);
                break;
            case KeyEvent.VK_S:
                gamePanel.changeYDelta(5);
                break;
            case KeyEvent.VK_D:
                gamePanel.changeXDelta(5);
                break;
        }
        */
    
       switch (e.getKeyCode()) {
    case KeyEvent.VK_UP:
        gamePanel.changeYDelta(-5); // Di chuyển lên
        break;
    case KeyEvent.VK_LEFT:
        gamePanel.changeXDelta(-5); // Di chuyển sang trái
        break;
    case KeyEvent.VK_DOWN:
        gamePanel.changeYDelta(5);  // Di chuyển xuống
        break;
    case KeyEvent.VK_RIGHT:
        gamePanel.changeXDelta(5);  // Di chuyển sang phải
        break;
}

    /*switch (e.getKeyCode()) {
            case KeyEvent.VK_Y:
             gamePanel.changeYDelta(-5);
                break;
            case KeyEvent.VK_G:
              gamePanel.changeXDelta(-5);
                break;
            case KeyEvent.VK_H:
                gamePanel.changeYDelta(5);
                break;
            case KeyEvent.VK_J:
                gamePanel.changeXDelta(5);
                break;
        }*/
    





    }

    @Override
    public void keyReleased(KeyEvent e) {
//ầ
    }

}
