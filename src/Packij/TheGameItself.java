package Packij;

import utilities.Vector2D;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import static Packij.Constants.MIDDLE_VECTOR;

public class TheGameItself extends BasicGame {

    Snoo snoo;

    int survivedFor;

    Stack<IncomingRon> rons;


    TheGameItself(){

        snoo = new Snoo();

        survivedFor = 0;

        rons = new Stack<>();
        for (int i = 0; i < 30; i++) {
            rons.add(new IncomingRon());
        }

        objectList.add(snoo);

        objectList.add(new MiddleCircle());
        objectList.add(new MiddleCircle(true));
    }


    public void update() {


        ArrayList<GameObject> aliveList = new ArrayList<>();
        ArrayList<GameObject> dedList = new ArrayList<>();

        //synchronized (BasicGame.class) {



        for (GameObject o : objectList) {
            o.update();

            if (o instanceof IncomingRon){
            }


            if (o.dead){
                dedList.add(o);
            } else{
                aliveList.add(o);
            }

            //o.drawBoundingRect(g);
            //basically calls the draw method of each gameObject
        }

        if (snoo.dead){
            gameOver = true;
        } else{
            survivedFor++;
        }

        //}

        for (GameObject d: dedList) {
            if (d instanceof IncomingRon){
                rons.push((IncomingRon) d);
            }

        }


        if (!rons.isEmpty()) {
            if (Math.random() > 0.75) {
                IncomingRon newRon = rons.pop();
                Vector2D newRonOffset = Vector2D.polar(Math.toRadians(Math.random() * 360), (Math.random() * 1024) + 512);
                Vector2D newRonLocation = new Vector2D(newRonOffset).add(MIDDLE_VECTOR);
                double ronAngle = newRonOffset.flip().angle();
                newRon.ronPaulCanStillWin(newRonLocation, ronAngle);
                aliveList.add(newRon);
            }
        }

        synchronized (BasicGame.class) {
            objectList.clear();
            objectList.addAll(aliveList);
        }



    }

    //}
}

