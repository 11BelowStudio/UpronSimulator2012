package Packij;

import javax.swing.*;

public class MainClassIsHere {

    BasicGame game;
    BasicView view;

    public static void main(String[] args) throws InterruptedException {
        //BasicGame game = new BasicGame();
        BasicGame game = new Intro();
        BasicView view = new BasicView(game);
        new JEasyFrame(view, "Basic Game");


    }

}
