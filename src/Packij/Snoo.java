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

    int karma;

    int karmaDecay;

    public boolean oneHit;

    String karmaString;

    Rectangle hitBounds;

    Area currentArea;


    public Snoo(){
        super(new Vector2D(HALF_WIDTH,HALF_HEIGHT),new Vector2D());

        karma = 128;

        karmaDecay = 5;

        karmaString = String.valueOf(karma);


        hitArea = new Area(new Ellipse2D.Double(position.x-karma,position.y-karma,2*karma, 2*karma));

        hitBounds = hitArea.getBounds();

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
        if (karma > 0) {
            hitArea = new Area(new Ellipse2D.Double(position.x - karma, position.y - karma, 2 * karma, 2 * karma));
        } else{
            hitArea = new Area(new Ellipse2D.Double(position.x - 1, position.y - 1, 2, 2));
            dead = true;
        }
        hitBounds = hitArea.getBounds();
        karmaString = String.valueOf(karma);
    }

    public int getKarma(){
        return karma;
    }

    @Override
    public void draw(Graphics2D g) {

        AffineTransform at = g.getTransform();

        //g.setPaint(Color.cyan);
        //g.fill(hitArea);

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
        /*
        g.fill(hitBounds);
        g.setColor(Color.lightGray);
        g.fill(hitArea);*/
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
