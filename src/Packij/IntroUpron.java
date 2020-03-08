package Packij;

import utilities.Vector2D;

import java.awt.*;
import java.awt.geom.AffineTransform;

import static Packij.Constants.*;

public class IntroUpron extends GameObject {

    private Rectangle upronArea;

    public int upronCount;

    public IntroUpron() {
        super(new Vector2D(HALF_WIDTH,HALF_HEIGHT),new Vector2D());

        upronArea = new Rectangle((int)(position.x - 256),(int)(position.y - 256),512,512);

        upronCount = 0;

        img = UPRON;
        //img = SNOO;

    }

    @Override
    public void draw(Graphics2D g) {
        AffineTransform at = g.getTransform();
        g.translate(position.x,position.y);
        g.drawImage(img,-256,-256,512,512,null);
        g.setTransform(at);
    }



    public void upron(Point upronLocation){
        if(upronArea.contains(upronLocation)) {
            System.out.println("So brave!");
            upronCount++;
        }
    }
}
