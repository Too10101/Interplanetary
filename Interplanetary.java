/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interplanetary;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;

/**
 *
 * @author 629469
 */
public class Interplanetary {


    public static void main(String[] args) {
        JFrame p = new JFrame("Planetary");
        p.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        p.setSize(1200, 840);
        Main i = new Main();
        p.add(i);
        p.setVisible(true);
        p.setLocationRelativeTo(null);
        p.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                i.keyPress(e);
            }
            @Override
            public void keyReleased(KeyEvent e) {
                i.keyRelease(e);
            }
        });
    }
    
}
