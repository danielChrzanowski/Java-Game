package animations;

public class BirdsRightAnimation extends BirdsAnimation {

    public BirdsRightAnimation() {
        super();
        x = -200;
    }

    @Override
    public void action() {
        centerX = x + 120;
        centerY = y + 160;

        if (hit) {
            orientation = 180;

            if (y < 700) {
                y += 8;
            } else {
                alive = false;
            }

        } else {
            if (x < 1400) {
                x += speed;
            } else {
                alive = false;
            }

            if (frameNumber < 19) {
                frameNumber++;
            } else {
                frameNumber = 0;
            }
        }

    }

}
