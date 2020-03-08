package utilities;

import java.awt.*;

// mutable 2D vectors
public final class Vector2D {
    public double x, y;

    // constructor for zero vector
    public Vector2D() {
        this.x = 0;
        this.y = 0;
    }

    // constructor for vector with given coordinates
    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // constructor that copies the argument vector
    public Vector2D(Vector2D v) {
        double tempX = v.x;
        double tempY = v.y;
        this.x = tempX;
        this.y = tempY;
    }


    // set coordinates based on argument vector
    public Vector2D set(Vector2D v) {
        this.x = v.x;
        this.y = v.y;
        return this;
    }

    // compare for equality (note Object type argument)
    public boolean equals(Object o) {
        if (o instanceof Vector2D){
            //if the other object is a Vector2D, compares x and y of this Vector2D and that Vector2D
            Vector2D v =(Vector2D) o;
            return (this.x == v.x && this.y == v.y);
        }
        return false;

    }

    // String for displaying vector as text
    public String toString() {
        return(x + ", "  + y);

    }


    // angle between vector and horizontal axis in radians in range [-PI,PI]
// can be calculated using Math.atan2
    public double angle() {
        return Math.atan2(y,x);
    }


    // add argument vector
    public Vector2D add(Vector2D v) {
        this.x += v.x;
        this.y += v.y;
        return this;
    }

    // weighted add - surprisingly useful (adds v but multiplied by the factor)
    public Vector2D addScaled(Vector2D v, double fac) {
        this.x += (v.x*fac);
        this.y += (v.y*fac);
        return this;
    }


    // distance to argument vector
    //Euclidean distance formula (which, for 2d planes, is pretty much pythagoras' theorem)
    public double dist(Vector2D v) {
        return (Math.hypot((x-v.x),(y-v.y)));

    }


    public Vector2D setMag(double newMag) {
        this.set(polar(this.angle(),newMag));
        return this;
    }


    // construct vector with given polar coordinates
    public static Vector2D polar(double angle, double mag) {
        return new Vector2D(mag*Math.cos(angle),mag*Math.sin(angle));
    }


    public double getAngleBetween(Vector2D v){
        double xAngle = v.x - x;
        double yAngle = v.y - y;
        return Math.atan2(yAngle,xAngle);
    }

    public Vector2D getVectorBetween(Vector2D v){
        return Vector2D.polar(getAngleBetween(v),dist(v));
    }


    public Vector2D flip(){
        x *= -1;
        y *= -1;
        return this;
    }


    public Vector2D(Point p){
        this.x = p.x;
        this.y = p.y;
    }



}