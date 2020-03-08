package Packij;


public class MainClassIsHere {


    public static void main(String[] args) throws InterruptedException {
        BasicGame game = new Intro();
        BasicView view = new BasicView(game);
        new BraveFrame(view);
    }

}
