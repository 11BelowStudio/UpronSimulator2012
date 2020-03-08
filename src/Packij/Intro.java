package Packij;

import utilities.Vector2D;

import java.awt.*;
import java.util.ArrayList;

import static Packij.Constants.*;

public class Intro extends BasicGame {


    private StringObject startButtonString;
    private StringObject pressTheArrowString;

    private IntroUpron upron;

    private int clarificationCountdown;

    private int startCountdown;

    private boolean goingToStart;

    private boolean clearWords;




    Intro(){

        super();

        //Font font = sans;
        String[] wordsToSpawn = new String[]{
                "It is 2012.",
                "Ron \"Ron Paul\" Paul is losing.",
                "Reddit is sad.",
                "But there is hope",
                "One thing you can do to save him.",
                "",
                "",
                "",
                "",
                "",
                "",
                "Uprons to the left."
        };

        upron = new IntroUpron();

        objectList.add(upron);

        int distFromBottom = 0;
        for (String s: wordsToSpawn) {
            objectList.add(new StringObject(new Vector2D(HALF_WIDTH,FRAME_HEIGHT+distFromBottom),Vector2D.polar(Math.toRadians(270),100),s));
            distFromBottom += 20;
        }


        gameOver = false;

        clearWords = false;

        goingToStart = false;

        startButtonString = new StringObject(new Vector2D(HALF_WIDTH,HALF_HEIGHT),new Vector2D(),"Upron to start the game");
        pressTheArrowString = new StringObject(new Vector2D(HALF_WIDTH,HALF_HEIGHT+20),new Vector2D(),"(that means press the orange arrow)");

        clarificationCountdown = -1;

        startCountdown = 50;



    }

    @Override
    public void clicked(Point clickLocation){
       upron.upron(clickLocation);
    }

    @Override
    public void update(){
        ArrayList<GameObject> aliveList = new ArrayList<>();
        //ArrayList<GameObject> dedList = new ArrayList<>();

        //synchronized (BasicGame.class) {

        boolean wordsExist = false;

        for (GameObject o : objectList) {
            o.update();
            //if (o.dead){
            if (!o.dead){
                //dedList.add(o);

            //} else{
                if (o instanceof StringObject){
                    if (!wordsExist){
                        wordsExist = true;
                    }
                    if (!clearWords){
                        aliveList.add(o);
                    }
                } else{
                    aliveList.add(o);
                }
            }

            //o.drawBoundingRect(g);
            //basically calls the draw method of each gameObject
        }

        if (clearWords){
            aliveList.add(weDidItReddit);
            clearWords = false;
            startCountdown = 51;
        }

        if (!goingToStart) {
            if (!wordsExist) {
                aliveList.add(startButtonString);
                clarificationCountdown = 101;
            }

            if (clarificationCountdown > 0) {
                clarificationCountdown--;
            } else if (clarificationCountdown == 0) {
                aliveList.add(pressTheArrowString);
            }
        } else{
            if (startCountdown > 0){
                startCountdown--;
            } else if (startCountdown == 0){
                thisIsGettingVerySilly();
            }
        }

        switch (upron.upronCount){
            case 0:
                break;
            case 1:
                if (!goingToStart){
                    goingToStart = true;
                    clearWords = true;
                }
                break;
            default:
                thisIsGettingVerySilly();
        }
        //}

        synchronized (BasicGame.class) {
            objectList.clear();
            objectList.addAll(aliveList);
        }

        if (objectList.isEmpty()){
            uLostLol();
        }


    }


}
