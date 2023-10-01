import java.awt.*;

public class Ball extends GameObject {

    private double posX;            // X-coordinate of the ball's position
    private double posY;            // Y-coordinate of the ball's position
    private int sizeX;              // Width of the ball
    private int sizeY;              // Height of the ball
    private Color color;            // Color of the ball
    private double speed = 3;       // Initial speed of the ball
    private double[] velocity = {1, 0};  // Velocity vector (direction and speed) of the ball
    private Paddle paddleLeft;      // Reference to the left paddle
    private Paddle paddleRight;     // Reference to the right paddle


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

    public void setVelocity(int[] velocity) {
        this.velocity[0] = velocity[0];
        this.velocity[1] = velocity[1];
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

    // Method for updating the ball's position and behavior
    @Override
    public void process(double delta) {
        // Calculate the angle of the current velocity vector
        double angle = Math.atan2(velocity[1], velocity[0]);

        // Update the velocity vector based on the angle and speed
        velocity[0] = speed * Math.cos(angle);
        velocity[1] = speed * Math.sin(angle);

        // Check for collision with the left paddle
        if (paddleLeft != null) {
            if (posY >= (paddleLeft.getPosY() - sizeY) && posY <= (paddleLeft.getPosY() + paddleLeft.getSizeY())) {
                if (posX >= paddleLeft.getPosX() && posX <= (paddleLeft.getPosX() + paddleLeft.getSizeX())) {
                    if (velocity[0] < 0) {
                        // Reverse the horizontal velocity and add some randomness
                        velocity[0] *= -1;
                        velocity[0] += ((Math.random() - 0.5) * 5);
                        velocity[1] += ((Math.random() - 0.5) * 2);
                    }
                    velocity[1] -= (paddleLeft.getCurrentSpeed() * 0.3);
                }
            }
        }
        // Check for collision with the right paddle
        if (paddleRight != null) {
            // Check if the ball's Y-coordinate is within the vertical range of the right paddle
            if (posY >= (paddleRight.getPosY() - sizeY) && posY <= (paddleRight.getPosY() + paddleRight.getSizeY())) {
                // Check if the ball's X-coordinate is within the horizontal range of the right paddle
                if (posX <= paddleRight.getPosX() && posX >= (paddleRight.getPosX() - sizeX)) {
                    // Check if the ball is moving towards the right paddle
                    if (velocity[0] > 0) {
                        // Reverse the horizontal velocity and add some randomness
                        velocity[0] *= -1;
                        velocity[0] += ((Math.random() - 0.5) * 5);
                        velocity[1] += ((Math.random() - 0.5) * 2);
                    }
                    // Reduce the vertical velocity based on the right paddle's current speed
                    velocity[1] -= (paddleRight.getCurrentSpeed() * 0.3);
                }
            }
        }

        // Check if the ball has hit the right wall
        if (posX >= (491 - sizeX)) {
            if (velocity[0] > 0) {
                // Reverse the horizontal velocity and add some randomness
                velocity[0] *= -1;
            }
            // Add some randomness to the ball's velocity
            velocity[0] += ((Math.random() - 0.5) * 4);
            velocity[1] += ((Math.random() - 0.5) * 1.5);
            // Handle scoring or game logic when the ball hits the right wall
            Ping.win(0);
        }

        // Check if the ball has hit the left wall
        if (posX <= 0) {
            if (velocity[0] < 0) {
                // Reverse the horizontal velocity and add some randomness
                velocity[0] *= -1;
            }
            // Add some randomness to the ball's velocity
            velocity[0] += ((Math.random() - 0.5) * 4);
            velocity[1] += ((Math.random() - 0.5) * 1.5);
            // Handle scoring or game logic when the ball hits the left wall
            Ping.win(1);
        }

        // Check if the ball has hit the bottom wall
        if (posY >= (468 - sizeY)) {
            if (velocity[1] > 0) {
                // Reverse the vertical velocity and add some randomness
                velocity[1] *= -1;
            }
            // Add some randomness to the ball's velocity
            velocity[0] += ((Math.random() - 0.5) * 4);
            velocity[1] += ((Math.random() - 0.5) * 1.5);
        }

        // Check if the ball has hit the top wall
        if (posY <= 0) {
            if (velocity[1] < 0) {
                // Reverse the vertical velocity and add some randomness
                velocity[1] *= -1;
            }
            // Add some randomness to the ball's velocity
            velocity[0] += ((Math.random() - 0.5) * 4);
            velocity[1] += ((Math.random() - 0.5) * 1.5);
        }

        // Update the ball's position based on its velocity and the time delta
        posX += (velocity[0] * delta);
        posY += (velocity[1] * delta);
    }
}
