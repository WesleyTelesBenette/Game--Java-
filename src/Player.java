import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends Rectangle {

    public int W, A, S, D, movX, movY;
    private int vel = 4; //Velocidade

    public Player(int x, int y) {
        super(x, y, 32, 32);
    }
    
    public void tick() {

        movX = (A - D);
        System.out.println(movX);

        if (movX != 0) {
            x += ((D - A) * vel);
        }
        System.out.println(x);
        if ((W >= 1) || (S >= 1)) {
            y += ((S - W) * vel);
        }
    }

    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(x, y, width, height);
    }
}
