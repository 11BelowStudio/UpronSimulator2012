package Packij;

import javax.swing.*;
import java.awt.*;

public class BasicView extends JComponent {
    // background colour
    public static final Color BG_COLOR = Color.black;

    private BasicGame game;

    MouseListener mouseListener;



    public BasicView(BasicGame game) throws InterruptedException {
        this.game = game;
        mouseListener = new MouseListener();
        this.addMouseListener(mouseListener);
        this.addMouseMotionListener(mouseListener);

    }

    public void changeGame(BasicGame game){
        this.game = game;
    }

    @Override
    public void paintComponent(Graphics g0) {
        Graphics2D g = (Graphics2D) g0;
        // paint the background
        g.setColor(BG_COLOR);
        g.fillRect(0, 0, getWidth(), getHeight());

        Font backupFont = g.getFont();

        g.setFont(Constants.sans);

        //FontMetrics metrics = g.getFontMetrics(Constants.sans);

        synchronized (BasicGame.class) {


            for (GameObject o : game.objectList) {
                o.draw(g);
                //o.drawBoundingRect(g);
                //basically calls the draw method of each gameObject
            }
            //}

        }


        g.setFont(backupFont);
    }

    public BasicGame getGame() {
        return game;
    }

    public void clicked(Point clickLocation){
        game.clicked(clickLocation);
    }

    public void mouseMoved(Point mouseLocation) {game.mouseMoved(mouseLocation);}

    @Override
    public Dimension getPreferredSize() {
        return Constants.FRAME_SIZE;
    }
}