/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interplanetary;

import java.awt.Color;

/**
 *
 * @author 629469
 */
public class Enemy extends Astronaut {
    public Enemy (int x, int y, int size, Color color) {
        super(x, y, size, color);
    }
    
    @Override
    public void update() {
        super.setDX( (int) (Math.random()*3) - 1);
        super.setDY( (int) (Math.random()*3) - 1);
        super.update();
    }
}
