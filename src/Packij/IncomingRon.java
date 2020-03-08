package Packij;

import utilities.Vector2D;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;

import static Packij.Constants.*;

public class IncomingRon extends GameObject implements Ronnable {

    int karmaValue;

    Image orangeArrow = Constants.UPRON;

    Vector2D direction;

    double distance;

    boolean downron;

    double angleToMiddle;


    IncomingRon() {
        super();
        objRect = new Rectangle(-16,-16,32,32);

        //ronPaulCanStillWin(p);

        objRect = new Rectangle(-16,-16,32,32);

        /*
        Vector2D.polar(Math.toRadians(Math.random() * 360), Math.random() * MAX_SPEED)
        Vector2D.polar(Math.toRadians(Math.random() * 360), Math.random() * MAX_SPEED)*/
    }

    public void ronPaulCanStillWin(Vector2D p, double angle){
        //position = Vector2D.randomVectorFromVector(MIDDLE_VECTOR, 1024, 1536);
        //angleToMiddle = angle;
        position = p;
        distance = position.dist(MIDDLE_VECTOR);
        velocity = Vector2D.polar(angle,Math.random()*MAX_SPEED+100);



        objRect = new Rectangle(-16,0,32,32);

        hitArea = new Area(objRect);

        karmaValue = (int)(Math.random() * 16)+1;

        if (Math.random() > 0.5){
            img = UPRON;
            downron = false;
        } else{
            img = DOWNRON;
            karmaValue = -karmaValue;
            downron = true;
        }
    }

    public void update(){
        super.update();
        if (position.getAngleBetween(MIDDLE_VECTOR) != angleToMiddle){
            System.out.println("oh dear");
            dead = true;
        }
    }

    @Override
    public void draw(Graphics2D g) {
        AffineTransform at = g.getTransform();
        g.translate(position.x,position.y);
        double rot = velocity.angle() + Math.PI / 2;
        if (downron){
            rot += Math.PI;
        }
        g.rotate(rot);
        //g.translate(0, position.dist(MIDDLE_VECTOR));
        g.drawImage(img,-16,0,32,32,null);
        g.setTransform(at);
    }

    @Override
    public boolean intersects(Area a) {
        return false;
    }
}
