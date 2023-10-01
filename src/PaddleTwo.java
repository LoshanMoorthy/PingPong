import java.awt.*;
import java.awt.event.KeyEvent;

public class PaddleTwo extends Paddle {

    public PaddleTwo(double posX, double posY, int sizeX, int sizeY, Color color, double speed) {
        super(posX, posY, sizeX, sizeY, color, speed);
    }

    public void process(double delta) {
        currentSpeed = 0;
        if (Ping.isKeyPressed(KeyEvent.VK_UP)) {
            if (posY >= 10) {
                currentSpeed = speed;
                posY -= (currentSpeed * delta);
            }
        }

        if (Ping.isKeyPressed(KeyEvent.VK_DOWN)) {
            if (posY <= 411) {
                currentSpeed = -speed;
                posY -= (currentSpeed * delta);
            }
        }
    }
}
