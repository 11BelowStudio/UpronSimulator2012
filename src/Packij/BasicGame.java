package Packij;


import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class BasicGame {

    List<GameObject> objectList;

    boolean gameOver;

    boolean stopThis;


    BasicGame(){

        objectList = new ArrayList<>();
        stopThis = false;
        gameOver = false;
    }

    protected void uLostLol(){
        gameOver = true;
    }

    void thisIsGettingVerySilly(){
        stopThis = true;
    }

    public void clicked(Point clickLocation){
        System.out.println("clicked at" + clickLocation);
    }


    public abstract void update();

    public void mouseMoved(Point location){

    }

    //}
}