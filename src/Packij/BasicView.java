package Packij;

import javax.swing.*;
import java.awt.*;

public class BasicView extends JComponent {
    private static final Color BG_COLOR = Color.black;

    private BasicGame game;


    BasicView(BasicGame game) {
        this.game = game;
        MouseListener mouseListener = new MouseListener();
        this.addMouseListener(mouseListener);
        this.addMouseMotionListener(mouseListener);

    }

    void changeGame(BasicGame game){
        this.game = game;
    }

    @Override
    public void paintComponent(Graphics g0) {
        Graphics2D g = (Graphics2D) g0;
        g.setColor(BG_COLOR);
        g.fillRect(0, 0, getWidth(), getHeight());

        Font backupFont = g.getFont();

        g.setFont(Constants.sans);


        synchronized (BasicGame.class) {
            for (GameObject o : game.objectList) {
                o.draw(g);
            }
        }

        g.setFont(backupFont);
    }

    BasicGame getGame() {
        return game;
    }

    void clicked(Point clickLocation){
        game.clicked(clickLocation);
    }

    void mouseMoved(Point mouseLocation) {game.mouseMoved(mouseLocation);}

    @Override
    public Dimension getPreferredSize() {
        return Constants.FRAME_SIZE;
    }
}