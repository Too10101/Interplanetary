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
public class Barriers {
    int x;
    int y;
    int dx;
    int dy;
    int length;
    int width;
    Color color;

    
    public Barriers(int x, int y, int length, int width, Color color) {
        this.x = x;
        this.y = y;
        this.length = length;
        this.width = width;
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
    public int getLength() {
        return length;
    }
    public int getWidth() {
        return width;
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
    public void setLength(int length) {
        this.length = length;
    }
    public void setWidth(int width) {
        this.width = width;
    }
    public void setColor(Color color) {
        this.color = color;
    }
    
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, length, width);
    }
    
    public void move(int dx, int dy) {
        x += dx * 3;
        y += dy * 3;
    }
    
    public void update() {
        move(dx,dy);
    }
}
