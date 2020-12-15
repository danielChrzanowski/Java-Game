package components;

import animations.BirdsLeftAnimation;
import animations.BirdsRightAnimation;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.Timer;

public class MyComponent extends JComponent implements ActionListener {

    public BirdsLeftAnimation birdsLeftAnimation = new BirdsLeftAnimation();
    public BirdsRightAnimation birdsRightAnimation = new BirdsRightAnimation();

    public Timer timer;

    BufferedImage backgroundImage, ammoImage, birdsLeft, birdsRight;
    public Graphics2D graphics2d;

    public int score, ammo = 5;
    public boolean crossMarker = false;

    java.util.List<Line2D> lineList = new java.util.ArrayList<>();
    java.util.List<Line2D> lineList2 = new java.util.ArrayList<>();

    int width = 1200;
    int height = 800;

    public MyComponent() {
        timer = new Timer(30, this);
        timer.start();

        try {
            backgroundImage = ImageIO.read(MyComponent.class.getResourceAsStream("/resources/backgroundImage.jpg"));
            ammoImage = ImageIO.read(MyComponent.class.getResourceAsStream("/resources/ammo.png"));
            birdsLeft = ImageIO.read(MyComponent.class.getResourceAsStream("/resources/birdsLeft.png"));
            birdsRight = ImageIO.read(MyComponent.class.getResourceAsStream("/resources/birdsRight.png"));

        } catch (IOException ex) {
            Logger.getLogger(MyComponent.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void paintComponent(Graphics graph) {
        graphics2d = (Graphics2D) graph;
        graph.drawImage(backgroundImage, 0, 0, width, height, null);

        drawAmmo(ammo);
        birdsLeftAnimation();
        birdsRightAnimation();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.repaint();
    }

    public void drawCross(int x, int y) {
        graphics2d.setColor(Color.RED);
        Stroke stroke = new BasicStroke(4f);
        graphics2d.setStroke(stroke);

        Line2D line = new Line2D.Double(x - 30, y - 30, x + 30, y + 30);
        Line2D line2 = new Line2D.Double(x + 30, y - 30, x - 30, y + 30);

        lineList.add(line);
        lineList2.add(line2);

        for (int i = 0; i < lineList.size(); i++) {
            graphics2d.draw(lineList.get(i));
            graphics2d.draw(lineList2.get(i));
        }

        lineList.remove(0);
        lineList2.remove(0);
    }

    public void drawAmmo(int number) {
        int xPosition = width - 80;
        int offset = 0;

        for (int i = 0; i < number; i++) {
            graphics2d.drawImage(ammoImage, xPosition - offset, height - 170, 80, 160, null);
            offset += 40;
        }
    }

    private void birdsLeftAnimation() {
        if (!birdsLeftAnimation.alive) {
            birdsLeftAnimation = new BirdsLeftAnimation();
        }

        BufferedImage actualFrame = birdsLeft.getSubimage(birdsLeftAnimation.frameNumber * 240, 0, 240, 320);
        AffineTransform affineTransform = new AffineTransform();
        affineTransform.translate(birdsLeftAnimation.x, birdsLeftAnimation.y);
        affineTransform.rotate(Math.toRadians(birdsLeftAnimation.orientation), 120, 160);

        graphics2d.drawImage(actualFrame, affineTransform, null);

        if (birdsLeftAnimation.hit) {
            drawCross(birdsLeftAnimation.centerX, birdsLeftAnimation.centerY);
        }

        birdsLeftAnimation.action();
    }

    private void birdsRightAnimation() {
        if (!birdsRightAnimation.alive) {
            birdsRightAnimation = new BirdsRightAnimation();
        }

        BufferedImage actualFrame = birdsRight.getSubimage(birdsRightAnimation.frameNumber * 240, 0, 240, 320);
        AffineTransform affineTransform = new AffineTransform();
        affineTransform.translate(birdsRightAnimation.x, birdsRightAnimation.y);
        affineTransform.rotate(Math.toRadians(birdsRightAnimation.orientation), 120, 160);

        graphics2d.drawImage(actualFrame, affineTransform, null);

        if (birdsRightAnimation.hit) {
            drawCross(birdsRightAnimation.centerX, birdsRightAnimation.centerY);
        }

        birdsRightAnimation.action();
    }

}
