package Packij;


import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class BasicGame {

    public List<GameObject> objectList;

    public boolean gameOver;

    public boolean stopThis;


    public BasicGame(){

        objectList = new ArrayList<>();
        stopThis = false;
        gameOver = false;
    }

    protected void uLostLol(){
        gameOver = true;
    }

    protected void thisIsGettingVerySilly(){
        stopThis = true;
    }

    public void clicked(Point clickLocation){
        System.out.println("clicked at" + clickLocation);
    }


    public void update() {


        ArrayList<GameObject> aliveList = new ArrayList<>();
        ArrayList<GameObject> dedList = new ArrayList<>();

        //synchronized (BasicGame.class) {




        for (GameObject o : objectList) {
            o.update();
            if (o.dead){
                dedList.add(o);

            } else{
                aliveList.add(o);
            }

            //o.drawBoundingRect(g);
            //basically calls the draw method of each gameObject
        }
        //}

        synchronized (BasicGame.class) {
            objectList.clear();
            objectList.addAll(aliveList);
        }



    }

    public void mouseMoved(Point location){

    }

    //}
}