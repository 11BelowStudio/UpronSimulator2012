package Packij;

import utilities.Vector2D;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

import static Packij.Constants.*;

public class Snoo extends GameObject implements Ronnable {

    //int radius;

    //public Rectangle objRect = new Rectangle((int)position.x - 128, (int)position.y-128,256,256);

    private int karma;

    private int karmaDecay;

    private String karmaString;

    private String soBrave;

    private String itsHappening;

    private Rectangle hitBounds;


    private final Font epicFont = new Font("Impact", Font.BOLD,40);


    Snoo(){
        super(new Vector2D(HALF_WIDTH,HALF_HEIGHT),new Vector2D());

        karma = 128;

        karmaDecay = 5;

        karmaString = String.valueOf(karma);


        hitArea = new Area(new Ellipse2D.Double(position.x-karma,position.y-karma,2*karma, 2*karma));

        hitBounds = hitArea.getBounds();

        img = SNOO;

        soBrave = "So Brave!";

        itsHappening = "It's Happening!";

    }

    public void update() {


        if (karmaDecay > 0){
            karmaDecay--;
        } else if (karmaDecay == 0){
            if (karma > 1){
                karma--;
            }
            karmaDecay = 5;
        }

        if (karma < 1){
            dead = true;
        }
        updateArea();

    }

    private void updateKarma(int karmaChange){

        karma += karmaChange;
        if (karma > 256){
            karma = 256;
        }

        updateArea();
    }

    private void updateArea(){
        if (karma < 1) {
            dead = true;
        }
        hitBounds = hitArea.getBounds();
        karmaString = String.valueOf(karma);
    }

    /*
    int getKarma(){
        return karma;
    }*/

    @Override
    public void draw(Graphics2D g) {

        AffineTransform at = g.getTransform();

        g.translate(position.x,position.y);
        g.drawImage(img,-karma,-karma,2* karma,2* karma,null);

        hitArea = new Area(new Ellipse2D.Double(position.x-karma,position.y-karma,2*karma, 2*karma));
        hitBounds = hitArea.getBounds();

        FontMetrics metrics = g.getFontMetrics(Constants.sans);
        g.setColor(Color.white);
        int w = metrics.stringWidth(karmaString);
        int h = metrics.getHeight();
        g.drawString(karmaString,0-(w/2),karma+h);


        if (karma > 150){
            g.setFont(epicFont);
            g.setColor(Color.BLACK);
            metrics = g.getFontMetrics(epicFont);
            if (karma > 200){
                w = metrics.stringWidth(itsHappening);
                h = metrics.getHeight();
                g.drawString(itsHappening,-(w/2),-(h/2));
            } else{
                w = metrics.stringWidth(soBrave);
                h = metrics.getHeight();
                g.drawString(soBrave,-(w/2),-(h/2));
            }
            g.setFont(sans);
        }

        g.setTransform(at);
    }

    @Override
    public boolean ronned(IncomingRon ron) {
        if (hitBounds.intersects(ron.getBounds())){
            Area temp = hitArea;
            temp.intersect(ron.getArea());
            if (!temp.isEmpty()){
                updateKarma(ron.karmaValue);
                ron.braved();
                return true;
            }
            return false;
        }
        return false;
    }
}
