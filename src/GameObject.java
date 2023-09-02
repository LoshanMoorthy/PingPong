import java.awt.Graphics;

public abstract class GameObject {

    public abstract void paintObject(Graphics g);
    public abstract void process(double delta);

}
