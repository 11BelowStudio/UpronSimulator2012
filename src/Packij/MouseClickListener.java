package Packij;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static javax.swing.SwingUtilities.isLeftMouseButton;
import static javax.swing.SwingUtilities.isRightMouseButton;

public class MouseClickListener implements MouseListener {

    //yes, this was pretty much taken from the sample code

    public MouseClickListener() {
        //nothing to really construct tbh
    }

    @Override
    public void mouseClicked(MouseEvent e) {


    }

    @Override
    public void mousePressed(MouseEvent e) {
        //yeah basically nothing happens for this
        if (isLeftMouseButton(e)){
            //if something is left-clicked
            //Object sourceObject = e.getSource();
            if (e.getSource() instanceof BasicView){
                System.out.println("ayy lmao");
                ((BasicView)e.getSource()).clicked(e.getPoint());
            }



        } else if (isRightMouseButton(e)){


        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //same
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //that tbh
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //I think you can probably see a pattern here
    }
}