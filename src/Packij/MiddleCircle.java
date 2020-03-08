package Packij;

import utilities.Vector2D;

import java.awt.*;
import java.awt.geom.AffineTransform;

import static Packij.Constants.*;

public class MiddleCircle extends GameObject {

    Vector2D vectorPointingFromIt;

    int timeUntilNextVectorFromIt;

    boolean hmm;

    MiddleCircle(){
        super(new Vector2D(HALF_WIDTH,HALF_HEIGHT),new Vector2D());
        hmm = false;
        vectorPointingFromIt = Vector2D.polar(Math.toRadians(Math.random()*360),(Math.random()*200)+100);
        timeUntilNextVectorFromIt = 100;
    }

    MiddleCircle(boolean nice){
        super(new Vector2D(),new Vector2D());
        hmm = nice;
        vectorPointingFromIt = Vector2D.polar(Math.toRadians(Math.random()*360),(Math.random()*200)+100);
        vectorPointingFromIt.add(MIDDLE_VECTOR);
        timeUntilNextVectorFromIt = 100;
    }

    @Override
    public void update() {
        super.update();
        timeUntilNextVectorFromIt--;
        if (timeUntilNextVectorFromIt == 0){
            timeUntilNextVectorFromIt = 100;
            vectorPointingFromIt = Vector2D.polar(Math.toRadians(Math.random()*360),(Math.random()*200)+100);
            if (hmm){
                vectorPointingFromIt.add(MIDDLE_VECTOR);
            }
        }

    }

    @Override
    public void draw(Graphics2D g) {
        AffineTransform at = g.getTransform();
        g.translate(position.x,position.y);
        g.setPaint(Color.ORANGE);
        g.fillOval(-16,-16,32,32);
        if (!hmm) {
            g.setPaint(Color.green);
            g.drawLine(0, 0, (int) vectorPointingFromIt.x, (int) vectorPointingFromIt.y);
            g.setTransform(at);
        } else{
            g.setPaint(Color.yellow);
            g.setTransform(at);
            g.drawLine(HALF_WIDTH, HALF_HEIGHT, (int) vectorPointingFromIt.x, (int) vectorPointingFromIt.y);
        }
    }
}
