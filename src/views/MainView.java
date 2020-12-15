package views;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.SwingWorker;

public class MainView extends javax.swing.JFrame {

    int minutes, seconds;
    int gameLength = 50;

    List<Clip> clipList = new ArrayList<>();
    List<AudioInputStream> AISList = new ArrayList<>();
    Clip clipBgSound;
    AudioInputStream AISBgSound;

    public MainView() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        initComponents();

        URL bgSoundURL = MainView.class.getResource("/resources/bgSound.wav");
        AISBgSound = AudioSystem.getAudioInputStream(bgSoundURL);
        clipBgSound = AudioSystem.getClip();
        clipBgSound.open(AISBgSound);

        clipBgSound.setFramePosition(0);
        clipBgSound.start();
        clipBgSound.loop(Clip.LOOP_CONTINUOUSLY);

        startCounter();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        myComponent = new components.MyComponent();
        scoreLabel = new javax.swing.JLabel();
        counterLabel = new javax.swing.JLabel();
        gameOverLabel = new javax.swing.JLabel();
        finalScoreLabel = new javax.swing.JLabel();
        LPMHelp = new javax.swing.JLabel();
        PPMHelp = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Daniel Chrzanowski");
        setCursor(new java.awt.Cursor(java.awt.Cursor.CROSSHAIR_CURSOR));
        setMaximumSize(new java.awt.Dimension(1200, 838));
        setMinimumSize(new java.awt.Dimension(1200, 838));
        setPreferredSize(new java.awt.Dimension(1200, 838));
        setResizable(false);

        myComponent.setMaximumSize(new java.awt.Dimension(1200, 800));
        myComponent.setMinimumSize(new java.awt.Dimension(1200, 800));
        myComponent.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                myComponentMousePressed(evt);
            }
        });

        scoreLabel.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        scoreLabel.setForeground(new java.awt.Color(255, 0, 0));
        scoreLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        scoreLabel.setText("0");
        myComponent.add(scoreLabel);
        scoreLabel.setBounds(924, 0, 250, 50);

        counterLabel.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        counterLabel.setText("0:00");
        myComponent.add(counterLabel);
        counterLabel.setBounds(10, 0, 90, 50);

        gameOverLabel.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        gameOverLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        gameOverLabel.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        myComponent.add(gameOverLabel);
        gameOverLabel.setBounds(0, 0, 1200, 180);

        finalScoreLabel.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        finalScoreLabel.setForeground(new java.awt.Color(255, 0, 0));
        finalScoreLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        finalScoreLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        myComponent.add(finalScoreLabel);
        finalScoreLabel.setBounds(0, 200, 1200, 110);

        LPMHelp.setText("LPM - strzał");
        LPMHelp.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        myComponent.add(LPMHelp);
        LPMHelp.setBounds(110, 0, 180, 20);

        PPMHelp.setText("PPM - przeładuj, gdy 0 amunicji");
        PPMHelp.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        myComponent.add(PPMHelp);
        PPMHelp.setBounds(110, 20, 180, 20);

        getContentPane().add(myComponent, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void myComponentMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_myComponentMousePressed
        int x = evt.getX();
        int y = evt.getY();

        if (evt.getButton() == MouseEvent.BUTTON1) {
            if (myComponent.timer.isRunning()) {
                System.out.println("click");

                if (myComponent.ammo > 0) {
                    playSound("shot");
                    myComponent.ammo--;
                    myComponent.drawAmmo(myComponent.ammo);

                    if (!myComponent.birdsLeftAnimation.hit
                            && myComponent.birdsLeftAnimation.centerX < x + 110 && myComponent.birdsLeftAnimation.centerX > x - 110
                            && myComponent.birdsLeftAnimation.centerY < y + 110 && myComponent.birdsLeftAnimation.centerY > y - 110) {
                        myComponent.birdsLeftAnimation.hit = true;
                        myComponent.score += 10;
                        System.out.println("left hit " + x + " " + myComponent.birdsLeftAnimation.centerX + " " + y + " " + myComponent.birdsLeftAnimation.centerY);
                    } else {
                        System.out.println("left miss " + x + " " + myComponent.birdsLeftAnimation.centerX + " " + y + " " + myComponent.birdsLeftAnimation.centerY);

                        if (!myComponent.birdsRightAnimation.hit
                                && myComponent.birdsRightAnimation.centerX < x + 110 && myComponent.birdsRightAnimation.centerX > x - 110
                                && myComponent.birdsRightAnimation.centerY < y + 110 && myComponent.birdsRightAnimation.centerY > y - 110) {
                            myComponent.birdsRightAnimation.hit = true;
                            myComponent.score += 10;
                            System.out.println("right hit " + x + " " + myComponent.birdsRightAnimation.centerX + " " + y + " " + myComponent.birdsRightAnimation.centerY);
                        } else {
                            System.out.println("right miss " + x + " " + myComponent.birdsRightAnimation.centerX + " " + y + " " + myComponent.birdsRightAnimation.centerY);
                        }
                    }
                    scoreLabel.setText(myComponent.score + "");
                } else {
                    playSound("empty");
                }
            }
        } else {
            if (myComponent.timer.isRunning()) {
                System.out.println("click");

                if (myComponent.ammo <= 0) {
                    playSound("reload");
                    myComponent.ammo = 5;
                    myComponent.drawAmmo(myComponent.ammo);
                }
            }
        }


    }//GEN-LAST:event_myComponentMousePressed

    private void startCounter() {
        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                long delay = gameLength * 1000;

                do {
                    minutes = gameLength / 60;
                    seconds = gameLength % 60;

                    if (seconds > 10) {
                        counterLabel.setText(minutes + ":" + seconds);
                    } else {
                        counterLabel.setText(minutes + ":0" + seconds);
                    }

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(MainView.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    gameLength = gameLength - 1;
                    delay = delay - 1000;

                } while (delay >= 0);
                System.out.println("Time's Up!");
                myComponent.timer.stop();

                gameOverLabel.setText("GAME OVER");
                finalScoreLabel.setText("Final Score: " + myComponent.score);

                return null;
            }
        }.execute();
    }

    private void playSound(String name) {
        try {
            clipList.add(AudioSystem.getClip());
            AISList.add(AudioSystem.getAudioInputStream(MainView.class.getResource("/resources/" + name + ".wav")));
            int size = (clipList.size()) - 1;

            clipList.get(size).open(AISList.get(size));
            clipList.get(size).start();
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException ex) {
            Logger.getLogger(MainView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> {
            try {
                new MainView().setVisible(true);
            } catch (IOException | UnsupportedAudioFileException | LineUnavailableException ex) {
                Logger.getLogger(MainView.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LPMHelp;
    private javax.swing.JLabel PPMHelp;
    private javax.swing.JLabel counterLabel;
    private javax.swing.JLabel finalScoreLabel;
    private javax.swing.JLabel gameOverLabel;
    private components.MyComponent myComponent;
    public javax.swing.JLabel scoreLabel;
    // End of variables declaration//GEN-END:variables
}
