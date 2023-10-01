import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Ping extends JFrame {

    private static final long serialVersion = 1L;

    private static JFrame window = new JFrame("Ping Pong");
    private static Draw mainDraw = new Draw();

    private static ArrayList<Integer> keyPressed = new ArrayList<>();
    private static ArrayList<GameObject> objects = new ArrayList<>();
    private static int[] wins = {0, 0};

    static Ball mainBall;
    static Paddle leftPaddle;
    static Paddle rightPaddle;

    private static long lastTime = System.nanoTime();

    public static void init() {
        GameObject ball = new Ball(240, 230, 20, 20, Color.PINK, 9);
        objects.add(ball);
        mainBall = (Ball) ball;

        GameObject paddle = new Paddle(5, 200, 10, 80, Color.BLUE, 9);
        objects.add(paddle);
        ((Ball) ball).setPaddleLeft((Paddle) paddle);
        leftPaddle = (Paddle) paddle;

        GameObject paddleTwo = new PaddleAI(478, 200, 10, 80, Color.BLUE, 6);
        objects.add(paddleTwo);
        ((Ball) ball).setPaddleRight((Paddle) paddleTwo);
        rightPaddle = (Paddle) paddleTwo;
    }

    public static void main(String[] args) {
        createWindow();
        SwingUtilities.updateComponentTreeUI(window);
        window.getContentPane().add(mainDraw);
        window.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                if (!keyPressed.contains(e.getKeyCode()))
                    keyPressed.add(e.getKeyCode());
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (keyPressed.contains(e.getKeyCode()))
                    keyPressed.remove((Integer)(e.getKeyCode()));
            }
        });
        init();
        gameLoop();
    }

    public static void win(int n) {
        wins[n] += 1;
        mainBall.setPosX(240);
        mainBall.setPosY(230);
        leftPaddle.setPosX(5);
        leftPaddle.setPosY(200);
        rightPaddle.setPosX(478);
        rightPaddle.setPosY(200);

        int[] velocity = {0, 0};

        if (n == 1)
            velocity[0] = 1;
        else
            velocity[0] = -1;

        mainBall.setVelocity(velocity);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {}
        lastTime = System.nanoTime();
    }

    public static boolean isKeyPressed(int code) {
        return keyPressed.contains((Integer) (code));
    }

    public static void gameLoop() {
        lastTime = System.nanoTime();
        final double TARGET_FPS = 60.0;
        final double OPTIMAL_TIME = 1000000000 / TARGET_FPS;
        double delta = 0;

        while (true) {
            long now = System.nanoTime();
            long updateLength = now - lastTime;
            delta += (updateLength/ ((double) OPTIMAL_TIME));
            lastTime = now;

            while (delta >= 1) {
                for (GameObject g : objects)
                    g.process(((double)Math.round(delta * 100d) / 100d));

                process(((double)Math.round(delta * 100d) / 100d));
                delta = 0;
            }
        }
    }

    public static void process(double delta) {
        redraw();
    }

    static class Draw extends JComponent {
        private static final long serialVersion = 1L;
        private static Font f = new Font("Futura", Font.PLAIN, 36);
        public void paint(Graphics g) {
            for (GameObject o : objects)
                o.paintObject(g);

            g.setFont(f);
            g.drawString(wins[0] + "", 140, 40);
            g.drawString(wins[1] + "", 340, 40);
        }
    }

    public static void redraw() {
        mainDraw.paintImmediately(0, 0, 500, 500);
    }

    public static void createWindow() {
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(500, 500);
        window.setResizable(false);
        window.setVisible(true);
        window.getContentPane().setBackground(Color.RED);
    }


}
