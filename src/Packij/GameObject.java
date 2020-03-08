package Packij;

import utilities.Vector2D;

import java.awt.*;
import java.awt.geom.Area;

import static Packij.Constants.*;

public abstract class GameObject {


    Vector2D position, velocity;

    boolean dead;

    Area hitArea;

    Rectangle objRect;



    Image img;

    Color objectColour;


    public GameObject(Vector2D p, Vector2D v) {
        position = p;
        velocity = v;
        dead = false;
        img = null;
        objectColour = new Color(255, 255, 255, 32);
    }

    public GameObject(){
        dead = false;
    }

    public void update(){
        if (!dead) {
            position.addScaled(velocity, DT);
        }
    }

    public abstract void draw(Graphics2D g);


}