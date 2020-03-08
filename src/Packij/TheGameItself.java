package Packij;

import utilities.Vector2D;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import static Packij.Constants.*;

public class TheGameItself extends BasicGame {

    Snoo snoo;

    BraveryStick braveryStick;

    int survivedFor;

    Stack<IncomingRon> rons;

    StringObject score;

    int timeToUpdateScore;


    TheGameItself(){

        snoo = new Snoo();

        survivedFor = 0;

        rons = new Stack<>();
        for (int i = 0; i < 30; i++) {
            rons.add(new IncomingRon());
        }

        objectList.add(braveryStick = new BraveryStick());

        objectList.add(snoo = new Snoo());

        objectList.add(score = new StringObject(new Vector2D(HALF_WIDTH,20),new Vector2D(),String.valueOf(survivedFor)));

        timeToUpdateScore = DELAY;



        //objectList.add(new MiddleCircle());
        //objectList.add(new MiddleCircle(true));
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

        for (GameObject a: aliveList){
            if (a instanceof IncomingRon){
                if (snoo.ronned((IncomingRon) a) || braveryStick.ronned((IncomingRon) a)){
                    a.dead = true;
                }
            }
        }

        if (snoo.dead){
            uLostLol();
        } else{
            survivedFor++;
            score.showValue(survivedFor);
            /*
            if (timeToUpdateScore == 0) {
                survivedFor++;
                score.showValue(survivedFor);
                timeToUpdateScore = DELAY;
            } else{
                timeToUpdateScore--;
            }*/
        }

        //}

        for (GameObject d: dedList) {
            if (d instanceof IncomingRon){
                rons.push((IncomingRon) d);
                System.out.println("ron back in stack");
            }

        }


        if (!rons.isEmpty()) {
            if (Math.random() > 0.85) {
                IncomingRon newRon = rons.pop();
                Vector2D newRonOffset = Vector2D.polar(Math.toRadians(Math.random() * 360), (Math.random() * 1024) + 512);
                Vector2D newRonLocation = new Vector2D(newRonOffset).add(MIDDLE_VECTOR);
                double ronAngle = newRonOffset.flip().angle();
                newRon.ronPaulCanStillWin(newRonLocation, ronAngle);
                aliveList.add(newRon);
                System.out.println("another ron bites the ron");
            }
        }

        synchronized (BasicGame.class) {
            objectList.clear();
            objectList.addAll(aliveList);
        }



    }

    protected void uLostLol(){
        gameOver = true;
        JOptionPane.showMessageDialog(null,"Ron \"Ron Paul\" Paul can still win!");
        thisIsGettingVerySilly();
    }

    public void mouseMoved(Point location){
        braveryStick.setMouseLocation(location);
    }

    //}
}

