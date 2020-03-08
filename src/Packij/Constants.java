package Packij;

import utilities.ImageManager;
import utilities.Vector2D;

import java.awt.*;
import java.io.IOException;

public class Constants {
    static final int FRAME_HEIGHT = 600;
    static final int HALF_HEIGHT = FRAME_HEIGHT/2;

    private static final int FRAME_WIDTH = 800;
    static final int HALF_WIDTH = FRAME_WIDTH/2;

    static final Vector2D MIDDLE_VECTOR = new Vector2D(HALF_WIDTH,HALF_HEIGHT);

    static final Font sans = new Font("Comic Sans MS",  Font.PLAIN , 20);

    static final Dimension FRAME_SIZE = new Dimension(
            Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT);

    static final int MAX_SPEED = 200;


    static Image UPRON, DOWNRON, SNOO, BRAVERYSTICK, BRAVERYSTICKFLIPPED;
    static {
        try {
            UPRON = ImageManager.loadImage("upvote");
            DOWNRON = ImageManager.loadImage("downvote");
            SNOO = ImageManager.loadImage("snoo");
            BRAVERYSTICK = ImageManager.loadImage("BraveryStick");
            BRAVERYSTICKFLIPPED = ImageManager.loadImage("BraveryStickFlipped");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static StringObject weDidItReddit = new StringObject(MIDDLE_VECTOR,new Vector2D(),"WE DID IT REDDIT!");

    // sleep time between two frames
    static final int DELAY = 20;  // time between frames in milliseconds
    static final double DT = DELAY / 1000.0;  // DELAY in seconds


}