import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Paddle extends GameObject {

    protected Color color;
    protected double posX;
    protected double posY;
    protected int sizeX;
    protected int sizeY;
    protected double speed;
    protected double currentSpeed;

    public Paddle() {}

    public Paddle(double posX, double posY, int sizeX, int sizeY, Color color, double speed) {
        this.posX = posX;
        this.posY = posY;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.color = color;
        this.speed = speed;
    }

    @Override
    public void paintObject(Graphics g) {
        g.setColor(color);
        g.fillRect((int)posX, (int)posY, sizeX, sizeY);
        g.setColor(color);
        g.drawRect((int)posX, (int)posY, sizeX, sizeY);
    }

    public double getPosX() {
        return posX;
    }

    public double getPosY() {
        return posY;
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public double getCurrentSpeed() {
        return currentSpeed;
    }

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public void setPosY(double posY) {
        this.posY = posY;
    }

    @Override
    public void process(double delta) {

    }
}
