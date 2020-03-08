package Packij;

import javax.swing.event.MouseInputAdapter;
import java.awt.event.MouseEvent;


public class MouseListener extends MouseInputAdapter {

    //yes, this was pretty much taken from the sample code

    MouseListener() {
        //nothing to really construct tbh
    }


    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() instanceof BasicView){
            ((BasicView)e.getSource()).clicked(e.getPoint());
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (e.getSource() instanceof BasicView){
            ((BasicView) e.getSource()).mouseMoved(e.getPoint());
        }
    }
}