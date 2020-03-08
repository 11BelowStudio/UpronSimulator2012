package Packij;

import utilities.Vector2D;

import java.awt.*;
import java.util.ArrayList;
import java.util.Stack;

import static Packij.Constants.*;

public class TheGameItself extends BasicGame {

    private Snoo snoo;

    private BraveryStick braveryStick;

    private int survivedFor;

    private Stack<IncomingRon> rons;

    private StringObject score;


    private HighScoreHandler highscores;


    TheGameItself(HighScoreHandler highscores){

        snoo = new Snoo();

        this.highscores = highscores;

        survivedFor = 0;

        rons = new Stack<>();
        for (int i = 0; i < 30; i++) {
            rons.add(new IncomingRon());
        }

        objectList.add(braveryStick = new BraveryStick());

        objectList.add(snoo = new Snoo());

        objectList.add(score = new StringObject(new Vector2D(HALF_WIDTH,20),new Vector2D(),String.valueOf(survivedFor)));


    }


    public void update() {


        ArrayList<GameObject> aliveList = new ArrayList<>();
        ArrayList<GameObject> dedList = new ArrayList<>();




        for (GameObject o : objectList) {


            o.update();


            if (o.dead){
                dedList.add(o);
            } else{
                aliveList.add(o);
            }

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
        }

        for (GameObject d: dedList) {
            if (d instanceof IncomingRon){
                rons.push((IncomingRon) d);
            }

        }


        if (!rons.isEmpty()) {
            if (Math.random() > 0.5) {
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

    protected void uLostLol(){
        gameOver = true;
        highscores.recordHighScore(survivedFor);
        thisIsGettingVerySilly();
    }

    public void mouseMoved(Point location){
        braveryStick.setMouseLocation(location);
    }

    //}
}

