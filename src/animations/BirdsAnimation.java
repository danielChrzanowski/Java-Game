package animations;

import java.util.Random;

public class BirdsAnimation {

    public int x, y, frameNumber = 0, orientation = 0, centerX, centerY, speed;
    public boolean alive, hit;

    public BirdsAnimation() {
        Random random = new Random();
        y = random.nextInt((500 - 0) + 1) + 0;
        speed = random.nextInt((45 - 10) + 1) + 10;

        centerX = x + 120;
        centerY = y + 160;

        hit = false;
        alive = true;
    }

    public void action() {
    }
}
