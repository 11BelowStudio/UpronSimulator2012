package Packij;

import utilities.Vector2D;

import java.awt.*;
import java.awt.geom.AffineTransform;

import static Packij.Constants.*;

public class StringObject extends GameObject {

    private String thisString;
    private double scale;

    public StringObject(Vector2D p, Vector2D v, String s){
        super(p,v);
        thisString = s;

        objectColour = Color.WHITE;
    }


    @Override
    public void draw(Graphics2D g) {
        AffineTransform at = g.getTransform();
        //g.scale(1,scale);
        g.translate(position.x, position.y);
        //g.setColor(Color.red);
        g.setColor(objectColour);
        FontMetrics metrics = g.getFontMetrics(Constants.sans);
        int w = metrics.stringWidth(thisString);
        g.drawString(thisString,0-(w/2),0);
        g.setTransform(at);
    }


    @Override
    public void update(){
        //collided = false;

        if (position.y < 0){
            dead = true;
        }
        if (!dead) {
            position.addScaled(velocity, DT);
            //scale = position.y/10;
            //position.wrap(FRAME_WIDTH, FRAME_HEIGHT);
        }
    }
}
