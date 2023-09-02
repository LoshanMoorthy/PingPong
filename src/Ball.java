import java.awt.*;

public class Ball extends GameObject {

    private double posX;
    private double posY;
    private int sizeX;
    private int sizeY;
    private Color color;
    private double speed = 3;
    private double[] velocity = {1, 0};
    private Paddle paddleLeft;
    private Paddle paddleRight;

    public Ball(double posX, double posY, int sizeX, int sizeY, Color color, double speed) {
        this.posX = posX;
        this.posY = posY;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.color = color;
        this.speed = speed;
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

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public void setPosY(double posY) {
        this.posY = posY;
    }

    public void setVelocity(double[] velocity) {
        this.velocity = velocity;
    }

    public void setPaddleLeft(Paddle paddleLeft) {
        this.paddleLeft = paddleLeft;
    }

    public void setPaddleRight(Paddle paddleRight) {
        this.paddleRight = paddleRight;
    }

    @Override
    public void paintObject(Graphics g) {
        g.setColor(color);
        g.drawOval((int)posX, (int)posY, sizeX, sizeY);
        g.setColor(color);
        g.fillOval((int)posX, (int)posY, sizeX, sizeY);
    }

    @Override
    public void process(double delta) {
        double angle = Math.atan2(velocity[1], velocity[2]);
        velocity[0] = speed * Math.cos(angle);
        velocity[1] = speed * Math.sin(angle);

        if (paddleLeft != null) {
            if (posY >= (paddleLeft.getPosY()-sizeY) && posY <= (paddleLeft.getPosY() + paddleLeft.getSizeY())) {
                if (posX >= paddleLeft.getPosX() && posX <= (paddleLeft.getPosX() + paddleLeft.getSizeX())) {
                    if (velocity[0] < 0) {
                        velocity[0] *= -1;
                        velocity[0] += ((Math.random() - 0.5) * 5);
                        velocity[1] += ((Math.random() - 0.5) * 2);
                    }
                }
            }
        }
    }
}
