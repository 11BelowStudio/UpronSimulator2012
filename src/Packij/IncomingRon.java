package Packij;

import utilities.Vector2D;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;

import static Packij.Constants.*;

public class IncomingRon extends GameObject {

    int karmaValue;

    Image orangeArrow = Constants.UPRON;

    Vector2D direction;

    double distance;

    boolean downron;

    double angleToMiddle;

    boolean xIntersect;
    boolean yIntersect;


    Rectangle hitBounds;


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
        dead = false;

        xIntersect = (position.x > HALF_WIDTH);
        yIntersect = (position.y > HALF_HEIGHT);

        distance = position.dist(MIDDLE_VECTOR);
        velocity = Vector2D.polar(angle,Math.random()*MAX_SPEED+100);



        objRect = new Rectangle(-16,0,32,32);

        hitArea = new Area(objRect);
        hitBounds = hitArea.getBounds();

        karmaValue = (int)(Math.random() * 32)+1;

        if (Math.random() > 0.5){
            img = UPRON;
            downron = false;
        } else{
            img = DOWNRON;
            karmaValue *= -1;
            downron = true;
        }
    }


    /*
    public void update(){

    }*/

    public void braved(){
        dead = true;
    }

    private boolean intersectedMiddle(){
        return (xIntersect != (position.x > HALF_WIDTH) || yIntersect != (position.y > HALF_HEIGHT));
    }

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
        //g.setColor(Color.cyan);
        //g.fill(hitArea);
    }

    public Rectangle getBounds(){
        return hitBounds;
    }

    public Area getArea(){
        return hitArea;
    }

}
