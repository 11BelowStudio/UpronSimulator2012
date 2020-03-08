package Packij;

import javax.swing.event.MouseInputAdapter;
import java.awt.event.MouseEvent;

import static javax.swing.SwingUtilities.isLeftMouseButton;
import static javax.swing.SwingUtilities.isRightMouseButton;

public class MouseListener extends MouseInputAdapter {

    //yes, this was pretty much taken from the sample code

    public MouseListener() {
        //nothing to really construct tbh
    }

    @Override
    public void mouseClicked(MouseEvent e) {


    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() instanceof BasicView){
            ((BasicView)e.getSource()).clicked(e.getPoint());
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

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (e.getSource() instanceof BasicView){
            ((BasicView) e.getSource()).mouseMoved(e.getPoint());
        }
    }
}