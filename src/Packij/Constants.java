package Packij;

import utilities.ImageManager;
import utilities.Vector2D;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Constants {
    public static final int FRAME_HEIGHT = 600;
    public static final int HALF_HEIGHT = FRAME_HEIGHT/2;

    public static final int FRAME_WIDTH = 800;
    public static final int HALF_WIDTH = FRAME_WIDTH/2;

    public static final Vector2D MIDDLE_VECTOR = new Vector2D(HALF_WIDTH,HALF_HEIGHT);

    public static final Font sans = new Font("Comic Sans MS",  Font.PLAIN , 20);

    public static final Dimension FRAME_SIZE = new Dimension(
            Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT);

    public static final int MAX_SPEED = 200;


    public static Image UPRON, DOWNRON, SNOO, BRAVERYSTICK, BRAVERYSTICKFLIPPED;
    //public static BufferedImage BUF_UPRON, BUF_DOWNRON, BUF_SNOO, BUF_BRAVERYSTICK, BUF_BRAVERYSTICKFLIPPED;
    static {
        try {
            UPRON = ImageManager.loadImage("upvote");
            //BUF_UPRON = ImageManager.loadBufferedImage("upvote");
            DOWNRON = ImageManager.loadImage("downvote");
            //BUF_DOWNRON = ImageManager.loadBufferedImage("downvote");
            SNOO = ImageManager.loadImage("snoo");
            //BUF_SNOO = ImageManager.loadBufferedImage("snoo");
            BRAVERYSTICK = ImageManager.loadImage("BraveryStick");
            //BUF_BRAVERYSTICK = ImageManager.loadBufferedImage("BraveryStick");
            BRAVERYSTICKFLIPPED = ImageManager.loadImage("BraveryStickFlipped");
            //BUF_BRAVERYSTICKFLIPPED = ImageManager.loadBufferedImage("BraveryStickFlipped");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static StringObject weDidItReddit = new StringObject(MIDDLE_VECTOR,new Vector2D(),"WE DID IT REDDIT!");

    // sleep time between two frames
    public static final int DELAY = 20;  // time between frames in milliseconds
    //public static final int DELAY = 20; //delay of 20: 50fps
    public static final double DT = DELAY / 1000.0;  // DELAY in seconds
    //public static final double DT = DELAY / 1000.0;



}