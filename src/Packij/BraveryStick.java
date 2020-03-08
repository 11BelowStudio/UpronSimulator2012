package Packij;

import utilities.Vector2D;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;

import static Packij.Constants.*;

public class BraveryStick extends GameObject implements Ronnable {

    private Image rightStick = BRAVERYSTICK;
    private Image leftStick = BRAVERYSTICKFLIPPED;

    private Vector2D mouseLocation;
    private double mouseAngle;

    private Rectangle hitBounds;

    private Area currentArea;



    BraveryStick(){
        super(Vector2D.polar(Math.toRadians(270),128).add(MIDDLE_VECTOR),new Vector2D());
        objRect = new Rectangle(-96,-16,224,32);

        mouseLocation = new Vector2D(HALF_WIDTH,0);
        mouseAngle = Math.toRadians(270);



        hitArea = new Area(objRect);
        hitBounds = hitArea.getBounds();

        currentArea = hitArea;


    }

    void setMouseLocation(Point location){
        mouseLocation = new Vector2D(location);
        //Vector2D distFromMiddle = MIDDLE_VECTOR.getVectorBetween(mouseLocation).setMag(128);
        //mouseAngle = distFromMiddle.angle();
        //position = distFromMiddle.add(MIDDLE_VECTOR);
    }

    public void update(){

        Vector2D distFromMiddle = MIDDLE_VECTOR.getVectorBetween(mouseLocation).setMag(128);
        position = distFromMiddle.add(MIDDLE_VECTOR);
        mouseAngle = MIDDLE_VECTOR.getAngleBetween(mouseLocation);

    }

    @Override
    public void draw(Graphics2D g) {
        AffineTransform at = g.getTransform();
        g.translate(position.x,position.y);
        g.rotate(mouseAngle);
        if (position.x < HALF_WIDTH){
            img = leftStick;
        } else{
            img = rightStick;
        }
        g.drawImage(img,-96,-16,224,32,null);
        Area temp = currentArea;
        currentArea = new Area(g.getTransform().createTransformedShape(objRect));
        temp.add(currentArea);
        hitArea = temp;
        hitBounds = hitArea.getBounds();
        g.setTransform(at);
        /*
        g.setColor(Color.yellow);
        g.fill(hitBounds);
        g.setColor(Color.red);
        g.fill(hitArea);*/
    }

    @Override
    public boolean ronned(IncomingRon ron) {
        if (hitBounds.intersects(ron.getBounds())){
            Area temp = hitArea;
            temp.intersect(ron.getArea());
            if (!temp.isEmpty()){
                ron.braved();
                return true;
            }
            return false;
        }
        return false;
    }
}
