package Packij;

import utilities.Vector2D;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;

import static Packij.Constants.*;

public class IncomingRon extends GameObject {

    int karma;


    //private boolean xIntersect;
    //private boolean yIntersect;


    private Rectangle hitBounds;


    IncomingRon() { //it's either going to be an upron or a downron
        super();
        objRect = new Rectangle(-16,-16,32,32);

        //ronPaulCanStillWin(p);

        objRect = new Rectangle(-16,-16,32,32);

        /*
        Vector2D.polar(Math.toRadians(Math.random() * 360), Math.random() * MAX_SPEED)
        Vector2D.polar(Math.toRadians(Math.random() * 360), Math.random() * MAX_SPEED)*/
    }

    void ronPaulCanStillWin(Vector2D p, double angle){

        position = p;
        dead = false;

        //xIntersect = (position.x > HALF_WIDTH);
        //yIntersect = (position.y > HALF_HEIGHT);

        velocity = Vector2D.polar(angle,Math.random()*MAX_SPEED+100);


        objRect = new Rectangle(-16,0,32,32);

        hitArea = new Area(objRect);
        hitBounds = hitArea.getBounds();

        karma = (int)(Math.random() * 32)+1;

        if (Math.random() > 0.5){
            img = UPRON;

        } else{
            img = DOWNRON;
            karma *= -1;
        }
    }




    void braved(){
        dead = true;
    }

    /*
    private boolean intersectedMiddle(){
        return (xIntersect != (position.x > HALF_WIDTH) || yIntersect != (position.y > HALF_HEIGHT));
    }*/

    @Override
    public void draw(Graphics2D g) {
        AffineTransform at = g.getTransform();
        g.translate(position.x,position.y);
        double rot = velocity.angle() + Math.PI / 2;
        g.rotate(rot);
        g.drawImage(img,-16,0,32,32,null);
        hitArea = new Area(g.getTransform().createTransformedShape(objRect));
        hitBounds = hitArea.getBounds();
        g.setTransform(at);
    }

    Rectangle getBounds(){
        return hitBounds;
    }

    Area getArea(){
        return hitArea;
    }

    int getKarma(){
        return this.karma;
    }

}
