package Packij;

import utilities.Vector2D;

import java.awt.*;
import java.awt.geom.AffineTransform;

import static Packij.Constants.*;

public class Snoo extends GameObject {

    //int radius;

    //public Rectangle objRect = new Rectangle((int)position.x - 128, (int)position.y-128,256,256);

    int karma;

    int karmaDecay;

    public boolean oneHit;

    String karmaString;


    public Snoo(){
        super(new Vector2D(HALF_WIDTH,HALF_HEIGHT),new Vector2D());

        karma = 128;

        karmaDecay = 5;

        karmaString = String.valueOf(karma);

        //objRect = new Rectangle((int)position.x - health, (int)position.y-health,2*health,2*health);

        //health = 128;

        img = SNOO;

        System.out.println("snoo made");

    }

    public void update() {

        oneHit = false;

        if (karmaDecay > 0){
            karmaDecay--;
        } else if (karmaDecay == 0){
            if (karma > 1){
                karma--;
                if (karma == 1){
                    oneHit = true;
                }
            }
            karmaDecay = 5;
        }

        if (karma == 0){
            dead = true;
        }
        karmaString = String.valueOf(karma);

    }

    public int getKarma(){
        return karma;
    }

    @Override
    public void draw(Graphics2D g) {

        AffineTransform at = g.getTransform();
        g.translate(position.x,position.y);
        //g.setPaint(new TexturePaint(BUF_SNOO,objRect));
        //g.fillOval
        g.drawImage(img,-karma,-karma,2* karma,2* karma,null);

        FontMetrics metrics = g.getFontMetrics(Constants.sans);
        int w = metrics.stringWidth(karmaString);
        int h = metrics.getHeight();
        g.setColor(Color.white);
        g.drawString(karmaString,0-(w/2),karma+h);

        g.setTransform(at);
    }
}
