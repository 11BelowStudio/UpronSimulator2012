package Packij;

import javax.swing.*;
import java.awt.*;

class BraveFrame extends JFrame {
    //le ce218 sample code has arrived (but made a bit better)

    private BasicView view;

    private BasicGame game;

    private HighScoreHandler highScores;




    BraveFrame(BasicView view) throws InterruptedException {
        super("Upron Simulator 2012");
        this.view = view;
        this.game = view.getGame();
        this.highScores = new HighScoreHandler(this);
        getContentPane().add(BorderLayout.CENTER, view);
        pack();
        this.setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        repaint();
        mainLoop();

    }

    private void mainLoop() throws InterruptedException {
        boolean firstOne = true;

        Timer repaintTimer = new Timer(Constants.DELAY, ev -> view.repaint());
        repaintTimer.start();
        boolean isIntro = true;
        while (true) {
            while (!game.stopThis) {
                long t0 = System.currentTimeMillis();
                game.update();
                long t1 = System.currentTimeMillis();
                long timeout = Constants.DELAY - (t1 - t0);
                if (timeout > 0) {
                    Thread.sleep(timeout);
                }
            }
            repaintTimer.stop();

            System.out.println("changing");

            /*
            view.changeGame(new Intro());
            game = view.getGame();*/
            isIntro = changeGame(isIntro);
            repaintTimer.start();
            if (firstOne){
                firstOne = false;
                JOptionPane.showMessageDialog(
                        this,
                        "ok so basically defend the Ron \"Ron Paul\" Paul\n"
                                +"presidential campaign headquarters\n"
                                +"(aka the website known as reddit dot com)\n"
                                +"from all the unbrave downronners who arent brave.\n\n"
                                +"Your job is to use your BraveryStick (So Brave)\n"
                                +"to hit the downrons (the blue ones) away from us\n"
                                +"whilst allowing the uprons (the orange ones) through.\n\n\n"
                                +"We *will* get Ron \"Ron Paul\" Paul into the white house!",
                        "ur job",
                        JOptionPane.PLAIN_MESSAGE
                );
            }

        }

    }

    private boolean changeGame(boolean isIntro){
        if (isIntro) {
            view.changeGame(new TheGameItself(highScores));
            game = view.getGame();
            isIntro = false;
            System.out.println("intro over");
        } else{
            view.changeGame(new Intro());
            game = view.getGame();
            isIntro = true;
            System.out.println("game over, replaying intro");
        }
        return isIntro;
    }


}
