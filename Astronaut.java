/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interplanetary;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author 629469
 */
public class Astronaut {
    int x;
    int y;
    int dx;
    int dy;
    int size;
    Color color;
    
    public Astronaut(int x, int y, int size, Color color) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.color = color;
    }
    
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getDX() {
        return dx;
    }
    public int getDY() {
        return dy;
    }
    public int getSize() {
        return size;
    }
    public Color getColor() {
        return color;
    }
    
    
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public void setDX(int dx) {
        this.dx = dx;
    }
    public void setDY(int dy) {
        this.dy = dy;
    }
    public void setSize(int size) {
        this.size = size;
    }
    public void setColor(Color color) {
        this.color = color;
    }
    
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(x, y, size, size);
    }
    
    public void move(int dx, int dy) {
        x += dx * 3;
        y += dy * 3;
    }
    
    public void bounce() {
        dx = -dx;
        dy = -dy;
    }
    
    public void update() {
        move(dx,dy);
    }
}
