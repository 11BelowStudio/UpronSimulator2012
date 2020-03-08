package utilities;

import java.awt.Font;
import java.awt.GraphicsEnvironment;

//http://www.java2s.com/Tutorial/Java/0261__2D-Graphics/Togetallavailablefontsinyoursystem.htm

public class FontsWhatIHave {

    public static void main(String[] a) {
        GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Font[] fonts = e.getAllFonts(); // Get the fonts
        for (Font f : fonts) {
            System.out.println(f.getFontName());
        }
    }
}


