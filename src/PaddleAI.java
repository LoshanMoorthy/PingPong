import java.awt.*;

public class PaddleAI extends Paddle {

    public PaddleAI(double posX, double posY, int sizeX, int sizeY, Color color, double speed) {
        super(posX, posY, sizeX, sizeY, color, speed);
    }

    public void process(double delta) {
        currentSpeed = 0;

        if (Ping.mainBall.getPosY() < (posY + (sizeY * 0.5)) && Ping.mainBall.getPosX() > 230) {
            if (posY >= 10) {
                currentSpeed = speed;
                posY -= (currentSpeed * delta);
            }
        }

        if (Ping.mainBall.getPosY() > (posY + (sizeY * 0.5)) && Ping.mainBall.getPosX() > 230) {
            if (posY <= 411) {
                currentSpeed = -speed;
                posY -= (currentSpeed * delta);
            }
        }
    }
}
