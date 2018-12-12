/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interplanetary;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JPanel;

/**
 *
 * @author 629469
 */
public class Main extends JPanel {
    private Timer time;
    private Barriers barrier, barrier2, barrier3, barrier4, wall, wall2, door;
    private Astronaut astronaut, spaceShip;
    
    public Main () {
        super();
        barrier = new Barriers(0, 0, 20, 801, Color.GRAY);
        barrier2 = new Barriers(0, 0, 1200, 20, Color.GRAY);
        barrier3 = new Barriers(1165, 0, 20, 801, Color.GRAY);
        barrier4 = new Barriers(0, 782, 1200, 20, Color.GRAY);
        wall = new Barriers(-800, 20, 20, 720, Color.lightGray);
        wall2 = new Barriers(-720, 60, 20, 722, Color.lightGray);
        door = new Barriers(794, 393, 10, 50, Color.GREEN);
        astronaut = new Astronaut(40, 40, 25, Color.WHITE);
        spaceShip = new Astronaut(800, 320, 200, Color.darkGray);
        time = new Timer();
        time.scheduleAtFixedRate(new ScheduleTask(), 150, 1000/60);
    }
       
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        this.setBackground(Color.BLACK);
        
        barrier.draw(g);
        barrier2.draw(g);
        barrier3.draw(g);
        barrier4.draw(g);
        
        wall.draw(g);
        wall2.draw(g);
        
        door.draw(g);
        
        astronaut.draw(g);
        
        spaceShip.draw(g);
        
        if (barrier2.getX() <= 400) {
            
        }
           
        //int x[] = {100,70,130};
        //int y[] = {50,100,100};
        
        //g.setColor(Color.BLUE);
        //g.fillPolygon(x,y,3);
        
        g.dispose();
    }
    
    
    private class ScheduleTask extends TimerTask {
        
        @Override
        public void run() {
            barrierCollision();
            spaceShipCollision();
            wallCollision();
            wall.update();
            spaceShip.update();
            astronaut.update();
            repaint();
        }
    }
       
    public void keyPress (KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_D) {
            astronaut.setDX(2);
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            astronaut.setDX(-2);
        }
        if (e.getKeyCode() == KeyEvent.VK_W) {
            astronaut.setDY(-2);
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            astronaut.setDY(2);
        }
    }
       
    public void keyRelease (KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_D)
            astronaut.setDX(0);
        if (e.getKeyCode() == KeyEvent.VK_A)
            astronaut.setDX(0);
        if (e.getKeyCode() == KeyEvent.VK_W)
            astronaut.setDY(0);
        if (e.getKeyCode() == KeyEvent.VK_S)
            astronaut.setDY(0);
    }
    
    private void barrierCollision() {
        if (astronaut.getX() <= barrier.getX() + barrier.getLength()) {
            astronaut.setX(barrier.getLength() + 5);
        }
        
        if (astronaut.getY() <= barrier2.getY() + barrier2.getWidth()) {
            astronaut.setY(barrier2.getWidth() + 5);
        }
        
        if (astronaut.getX() + astronaut.getSize() >= barrier3.getX()) {
            astronaut.setX(this.getWidth() - 50);
        }
        
        if (astronaut.getY() + astronaut.getSize() >= barrier4.getY()) {
            astronaut.setY(this.getHeight() - 50);
        }
    }
    
    private void spaceShipCollision() {
        
        if (astronaut.getX()+ astronaut.getSize() >= door.getX() && astronaut.getY() + astronaut.getSize() >= door.getY()) {
            if (astronaut.getX() <= door.getX() + door.getLength() && astronaut.getY() <= door.getY() + door.getWidth()) {
                astronaut.setColor(Color.BLACK);
                
                barrier.setX(200);
                barrier.setY(360);
                barrier.setWidth(80);
                barrier.setLength(200);
                barrier.setColor(Color.DARK_GRAY);
                
                barrier2.setX(barrier.getX() + 200);
                barrier2.setY(barrier.getY() + 20);
                barrier2.setWidth(40);
                barrier2.setLength(120);
                
                barrier3.setX(-200);
                
                barrier4.setX(-1200);
                
                spaceShip.setDX(-1);
                spaceShip.setX(1500);
                spaceShip.setY(240);
                spaceShip.setSize(300);
                spaceShip.setColor(Color.BLUE);
                
                door.setX(-100);
            }
        }
        
        if (spaceShip.getX() <= barrier2.getX() + barrier2.getLength() && spaceShip.getY() <= barrier2.getY() + barrier2.getWidth()) {
            if (spaceShip.getX() + spaceShip.getSize() >= barrier2.getX()&& spaceShip.getY() + spaceShip.getSize() >= barrier2.getY()) {
                spaceShip.setDX(0);
                spaceShip.setX(-2000);
                
                barrier.setX(0);
                barrier.setY(0);
                barrier.setWidth(801);
                barrier.setLength(20);
                barrier.setColor(Color.GRAY);
                
                barrier2.setX(0);
                barrier2.setY(0);
                barrier2.setWidth(20);
                barrier2.setLength(1200);
                
                barrier3.setX(1165);
                barrier3.setY(0);
                barrier3.setWidth(801);
                barrier3.setLength(20);
                
                barrier4.setX(0);
                barrier4.setY(782);
                barrier4.setWidth(20);
                barrier4.setLength(1200);
                
                wall.setX(800);
                wall2.setX(720);
                
                astronaut.setColor(Color.WHITE);
            }
        } 
    }
    
    private void wallCollision() {
        if (astronaut.getX() <= wall.getX() + wall.getLength() && astronaut.getY() <= wall.getY() + wall.getWidth()) {
            if (astronaut.getX() + astronaut.getSize() >= wall.getX() && astronaut.getY() + astronaut.getSize() >= barrier2.getY()) {
                astronaut.bounce();
            }
        }
        
        if (astronaut.getX() <= wall2.getX() + wall2.getLength() && astronaut.getY() <= wall2.getY() + wall2.getWidth()) {
            if (astronaut.getX() + astronaut.getSize() >= wall2.getX() && astronaut.getY() + astronaut.getSize() >= barrier2.getY()) {
                astronaut.bounce();
            }
        }
    }
}
