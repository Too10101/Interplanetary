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
    private Barriers barrier, barrier2, barrier3, barrier4, 
                     wall, wall2, wall3,
                     obstacle1, obstacle2, obstacle3,
                     door;
    private Astronaut astronaut, spaceShip,
                      shot1, shot2;
    private Enemy enemy1;
    
    int levels;
    
    public Main () {
        super();
        barrier = new Barriers(0, 0, 20, 801, Color.GRAY);
        barrier2 = new Barriers(0, 0, 1200, 20, Color.GRAY);
        barrier3 = new Barriers(1165, 0, 20, 801, Color.GRAY);
        barrier4 = new Barriers(0, 782, 1200, 20, Color.GRAY);
        
        wall = new Barriers(-800, 20, 20, 720, Color.lightGray);
        wall2 = new Barriers(-720, 60, 20, 722, Color.lightGray);
        wall3 = new Barriers(20, -600, 300, 20, Color.lightGray);
        
        obstacle1 = new Barriers(20, -200, 50, 10, Color.BLUE);
        obstacle2 = new Barriers(20, -200, 50, 10, Color.BLUE);
        obstacle3 = new Barriers(20, -200, 50, 10, Color.BLUE);
        
        door = new Barriers(794, 393, 10, 50, Color.GREEN);
        
        enemy1 = new Enemy(-2000, 500, 30, Color.BLACK);
        
        shot1 = new Astronaut(2000, 500, 5, Color.BLACK);
        shot2 = new Astronaut(500, 1000, 5, Color.BLACK);
        
        astronaut = new Astronaut(40, 40, 25, Color.WHITE);
        
        spaceShip = new Astronaut(800, 320, 200, Color.darkGray);
        
        time = new Timer();
        time.scheduleAtFixedRate(new ScheduleTask(), 150, 1000/60);
    }
       
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        this.setBackground(Color.BLACK);
        
        if (barrier2.getX() >= 400) {
            for (int i = 0; i < 50; i++) {
                int sx = (int) (Math.random() * 1200);
                int sy = (int) (Math.random() * 840);
                g.setColor(Color.YELLOW);
                g.fillOval(sx, sy, 3, 3);
            }
        }
        
        if (levels == 1 || levels == 3) {
            int x[] = {200,200,250};
            int y[] = {310,360,360};
            
            int x2[] = {200,200,250};
            int y2[] = {490,440,440};
            
            int x3[] = {520,520,600};
            int y3[] = {380,420,400};
        
            g.setColor(Color.darkGray);
            g.fillPolygon(x,y,3);
            
            g.setColor(Color.darkGray);
            g.fillPolygon(x2,y2,3);
            
            g.setColor(Color.darkGray);
            g.fillPolygon(x3,y3,3);
        }
        
        if (levels == 0) {
            int x[] = {750,700,700};
            int y[] = {415,380,450};
        
            g.setColor(Color.BLUE);
            g.fillPolygon(x,y,3);
            
            g.setColor(Color.BLUE);
            g.fillRect(600, 404, 100, 20);
        }
        
        barrier.draw(g);
        barrier2.draw(g);
        barrier3.draw(g);
        barrier4.draw(g);
        
        if (levels == 2 || levels == 4) {
            wall.draw(g);
            wall2.draw(g);
            wall3.draw(g);
            
            obstacle1.draw(g);
            obstacle2.draw(g);
            obstacle3.draw(g);
        }
        
        door.draw(g);
         
        shot1.draw(g);
        shot2.draw(g);
        
        if (levels == 2) {
            enemy1.draw(g);
        }

        astronaut.draw(g);
        
        spaceShip.draw(g);
        
        g.dispose();
    }
    
    
    private class ScheduleTask extends TimerTask {
        
        @Override
        public void run() {
            barrierCollision();
            EnemyBarrierCollision();
            spaceShipCollision();
            wallCollision();
            EnemyWallCollision();
            enemyVsAstronaut();
            obstacles();
            obstacle1.update();
            obstacle2.update();
            obstacle3.update();
            spaceShip.update();
            astronaut.update();
            shot1.update();
            shot2.update();
            enemy1.update();
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
    
    private void EnemyBarrierCollision() {
        if (enemy1.getX() <= barrier.getX() + barrier.getLength()) {
            enemy1.setX(barrier.getLength() + 5);
        }
        
        if (enemy1.getY() <= barrier2.getY() + barrier2.getWidth()) {
            enemy1.setY(barrier2.getWidth() + 5);
        }
        
        if (enemy1.getX() + astronaut.getSize() >= barrier3.getX()) {
            enemy1.setX(this.getWidth() - 50);
        }
        
        if (enemy1.getY() + astronaut.getSize() >= barrier4.getY()) {
            enemy1.setY(this.getHeight() - 50);
        }
    }
    
    private void spaceShipCollision() {
        
        if (levels == 0 || levels == 2) {
            if (astronaut.getX()+ astronaut.getSize() >= door.getX() && astronaut.getY() + astronaut.getSize() >= door.getY()) {
                if (astronaut.getX() <= door.getX() + door.getLength() && astronaut.getY() <= door.getY() + door.getWidth()) {

                    levels++;

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
                    
                    if (levels == 1) {
                        spaceShip.setColor(Color.BLUE);
                    }
                    
                    if (levels == 3) {
                        spaceShip.setColor(Color.RED);
                    }
                    
                    door.setX(-100);
                }
            }
        }
        
        if (spaceShip.getX() <= barrier2.getX() + barrier2.getLength() + 70 && spaceShip.getY() <= barrier2.getY() + barrier2.getWidth()) {
            if (spaceShip.getX() + spaceShip.getSize() >= barrier2.getX()&& spaceShip.getY() + spaceShip.getSize() >= barrier2.getY()) {
                
                levels++;
                
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
                
                if (levels == 2) {
                    wall.setX(800);
                    wall2.setX(720);
                    wall3.setY(600);
                    
                    door.setX(20);
                    door.setY(620);
                    
                    obstacle1.setX(30);
                    obstacle1.setY(200);
                    obstacle1.setColor(Color.BLUE);
                
                    obstacle2.setX(30);
                    obstacle2.setY(350);
                    obstacle2.setColor(Color.BLUE);
                
                    obstacle3.setX(30);
                    obstacle3.setY(500);
                    obstacle3.setColor(Color.BLUE);
                }
                
                if (levels == 4) {
                    wall.setX(100);
                    wall2.setX(400);
                    wall2.setY(250);
                    wall2.setWidth(400);
                    wall2.setLength(400);
                    wall3.setX(400);
                    wall3.setY(650);
                    wall3.setWidth(132);
                    wall3.setLength(20);
                    
                    door.setX(20);
                    door.setY(50);
                    
                    obstacle1.setX(800);
                    obstacle1.setY(250);
                    obstacle1.setColor(Color.RED);
                
                    obstacle2.setX(800);
                    obstacle2.setY(440);
                    obstacle2.setColor(Color.RED);
                
                    obstacle3.setX(800);
                    obstacle3.setY(640);
                    obstacle3.setColor(Color.RED);
                    
                    astronaut.setX(500);
                    astronaut.setY(750);
                }

                enemy1.setX(200);
                enemy1.setY(200);
                enemy1.setColor(Color.BLUE);
                
                shot1.setColor(Color.BLUE);
                shot2.setColor(Color.BLUE);
                
                astronaut.setColor(Color.WHITE);
            }
        } 
    }
    
    private void wallCollision() {
        if (astronaut.getX() <= wall.getX() + wall.getLength() && astronaut.getY() <= wall.getY() + wall.getWidth()) {
            if (astronaut.getX() + astronaut.getSize() >= wall.getX() && astronaut.getY() + astronaut.getSize() >= wall.getY()) {
                astronaut.bounce();
            }
        }
        
        if (astronaut.getX() <= wall2.getX() + wall2.getLength() && astronaut.getY() <= wall2.getY() + wall2.getWidth()) {
            if (astronaut.getX() + astronaut.getSize() >= wall2.getX() && astronaut.getY() + astronaut.getSize() >= wall2.getY()) {
                astronaut.bounce();
            }
        }
        
        if (astronaut.getX() <= wall3.getX() + wall3.getLength() && astronaut.getY() <= wall3.getY() + wall3.getWidth()) {
            if (astronaut.getX() + astronaut.getSize() >= wall3.getX() && astronaut.getY() + astronaut.getSize() >= wall3.getY()) {
                astronaut.bounce();
            }
        }
    }
    
    private void EnemyWallCollision() {
        
        if (enemy1.getX() <= wall.getX() + wall.getLength() && enemy1.getY() <= wall.getY() + wall.getWidth()) {
            if (enemy1.getX() + enemy1.getSize() >= wall.getX() && enemy1.getY() + enemy1.getSize() >= wall.getY()) {
                enemy1.bounce();
            }
        }
        
        if (enemy1.getX() <= wall2.getX() + wall2.getLength() && enemy1.getY() <= wall2.getY() + wall2.getWidth()) {
            if (enemy1.getX() + enemy1.getSize() >= wall2.getX() && enemy1.getY() + enemy1.getSize() >= wall2.getY()) {
                enemy1.bounce();
            }
        }
        
        if (enemy1.getX() <= wall3.getX() + wall3.getLength() && enemy1.getY() <= wall3.getY() + wall3.getWidth()) {
            if (enemy1.getX() + enemy1.getSize() >= wall3.getX() && enemy1.getY() + enemy1.getSize() >= wall3.getY()) {
                enemy1.bounce();
            }
        }
        
        
        if (shot2.getX() <= wall3.getX() + wall3.getLength() && shot2.getY() <= wall3.getY() + wall3.getWidth()) {
            if (shot2.getX() + shot2.getSize() >= wall3.getX() && shot2.getY() + shot2.getSize() >= wall3.getY()) {
                shot2.setY(this.getHeight());
            }
        }
        
        if (shot1.getX() <= wall2.getX() + wall2.getLength() && shot1.getY() <= wall2.getY() + wall2.getWidth()) {
            if (shot1.getX() + enemy1.getSize() >= wall2.getX() && shot1.getY() + shot1.getSize() >= wall2.getY()) {
                shot1.setX(this.getWidth());
            }
        }
        
        if (shot1.getX() <= wall.getX() + wall.getLength() && shot1.getY() <= wall.getY() + wall.getWidth()) {
            if (shot1.getX() + enemy1.getSize() >= wall.getX() && shot1.getY() + shot1.getSize() >= wall.getY()) {
                shot1.setX(this.getWidth());
            }
        }
    }
    
    private void enemyVsAstronaut() {
        if (astronaut.getX() <= enemy1.getX() + enemy1.getSize() && astronaut.getY() <= enemy1.getY() + enemy1.getSize()) {
            if (astronaut.getX() + astronaut.getSize() >= enemy1.getX() && astronaut.getY() + astronaut.getSize() >= enemy1.getY()) {
                astronaut.gameOver();
            }
        }
        
        if (astronaut.getX() <= shot1.getX() + shot1.getSize() && astronaut.getY() <= shot1.getY() + shot1.getSize()) {
            if (astronaut.getX() + astronaut.getSize() >= shot1.getX() && astronaut.getY() + astronaut.getSize() >= shot1.getY()) {
                astronaut.gameOver();
            }
        }
        
        if (astronaut.getX() <= shot2.getX() + shot2.getSize() && astronaut.getY() <= shot2.getY() + shot2.getSize()) {
            if (astronaut.getX() + astronaut.getSize() >= shot2.getX() && astronaut.getY() + astronaut.getSize() >= shot2.getY()) {
                astronaut.gameOver();
            }
        }
        
        if (levels == 2 && enemy1.getX() <= astronaut.getX() && shot1.getX() >= this.getWidth() - 20) {
            shot1.setX(enemy1.getX() + 15);
            shot1.setY(enemy1.getY() + 15);
            shot1.setDX(4);
        }
        
        if (levels == 2 && enemy1.getY() <= astronaut.getY() && shot2.getY() >= this.getHeight() - 20) {
            shot2.setX(enemy1.getX() + 13);
            shot2.setY(enemy1.getY() + enemy1.getSize());
            shot2.setDY(3);
        }
    }
    
    public void obstacles() {
        if (astronaut.getX() <= obstacle1.getX() + obstacle1.getLength() && astronaut.getY() <= obstacle1.getY() + obstacle1.getWidth()) {
            if (astronaut.getX() + astronaut.getSize() >= obstacle1.getX() && astronaut.getY() + astronaut.getSize() >= obstacle1.getY()) {
                astronaut.gameOver();
            }
        }
        
        if (levels <= 2) {
            if (obstacle1.getX() <= barrier.getX() + 20) {
                obstacle1.setDX(1);
            }

            if (obstacle1.getX() >= wall2.getX() - 50) {
                obstacle1.setDX(-1);
            }


            if (astronaut.getX() <= obstacle2.getX() + obstacle2.getLength() && astronaut.getY() <= obstacle2.getY() + obstacle2.getWidth()) {
                if (astronaut.getX() + astronaut.getSize() >= obstacle2.getX() && astronaut.getY() + astronaut.getSize() >= obstacle2.getY()) {
                    astronaut.gameOver();
                }
            }

            if (obstacle2.getX() <= barrier.getX() + 20) {
                obstacle2.setDX(2);
            }

            if (obstacle2.getX() >= wall2.getX() - 50) {
                obstacle2.setDX(-2);
            }


            if (astronaut.getX() <= obstacle3.getX() + obstacle3.getLength() && astronaut.getY() <= obstacle3.getY() + obstacle3.getWidth()) {
                if (astronaut.getX() + astronaut.getSize() >= obstacle3.getX() && astronaut.getY() + astronaut.getSize() >= obstacle3.getY()) {
                    astronaut.gameOver();
                }
            }

            if (obstacle3.getX() <= barrier.getX() + 20) {
                obstacle3.setDX(3);
            }

            if (obstacle3.getX() >= wall2.getX() - 50) {
                obstacle3.setDX(-3);
            }
        }
        
        
        if (levels >= 4) {
            if (obstacle1.getX() >= barrier3.getX() - 50) {
                obstacle1.setDX(-1);
            }

            if (obstacle1.getX() <= wall2.getX() + 400) {
                obstacle1.setDX(1);
            }


            if (astronaut.getX() <= obstacle2.getX() + obstacle2.getLength() && astronaut.getY() <= obstacle2.getY() + obstacle2.getWidth()) {
                if (astronaut.getX() + astronaut.getSize() >= obstacle2.getX() && astronaut.getY() + astronaut.getSize() >= obstacle2.getY()) {
                    astronaut.gameOver();
                }
            }

            if (obstacle2.getX() >= barrier3.getX() - 50) {
                obstacle2.setDX(-2);
            }

            if (obstacle2.getX() <= wall2.getX() + 400) {
                obstacle2.setDX(2);
            }


            if (astronaut.getX() <= obstacle3.getX() + obstacle3.getLength() && astronaut.getY() <= obstacle3.getY() + obstacle3.getWidth()) {
                if (astronaut.getX() + astronaut.getSize() >= obstacle3.getX() && astronaut.getY() + astronaut.getSize() >= obstacle3.getY()) {
                    astronaut.gameOver();
                }
            }

            if (obstacle3.getX() >= barrier3.getX() - 50) {
                obstacle3.setDX(-1);
            }

            if (obstacle3.getX() <= wall2.getX() + 400) {
                obstacle3.setDX(1);
            }
        }
    }
}
