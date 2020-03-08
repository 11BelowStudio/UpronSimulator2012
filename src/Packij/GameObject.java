package Packij;

import utilities.Vector2D;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;
import java.util.List;

import static Packij.Constants.*;

public abstract class GameObject {

    //https://docs.oracle.com/javase/8/docs/api/java/awt/Polygon.html

    public Vector2D position, velocity;

    public boolean dead;

    public double RADIUS; //kept for collision stuff


    public int pointValue;

    public Polygon objectPolygon;

    //public Shape transformedShape;

    public Area hitArea;

    public Rectangle objRect;

    public Rectangle backupRect;


    public BufferedImage texture;

    protected Image img;

    protected Color objectColour;

    public static final double DRAG = 0.015;



    //public static final double MAX_SPEED = 100;



    public GameObject(Vector2D p, Vector2D v) {
        position = p;
        velocity = v;
        dead = false;
        img = null;
        texture = null;
        //texture = (BufferedImage)AN_TEXTURE;
        objectColour = new Color(255, 255, 255, 32);
    }

    public GameObject(){
        dead = false;
    }

    public void update(){
        //collided = false;
        if (!dead) {
            position.addScaled(velocity, DT);
            /*
            if (position.x > HALF_WIDTH){
                position.x -= FRAME_WIDTH;
            }
            if (position.y > HALF_HEIGHT){
                position.y -= FRAME_HEIGHT;
            }*/
        }
    }

    public abstract void draw(Graphics2D g);


}