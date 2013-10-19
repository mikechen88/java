// Adapted from the UW
// A drawing surface that draws the state of all critters in the simulation.
//
import java.awt.*;
import java.util.*;
import javax.swing.*;

public class CritterPanel extends JPanel implements Observer {
    // class constants
    private static final long serialVersionUID = 0;
    private static final boolean ANTI_ALIAS = true;
    private static final int FONT_SIZE = 13;
    private static final Font FONT = new Font("Monospaced", Font.BOLD, FONT_SIZE);
    
    // fields
    private CritterModel model;
    
    // Constucts a new panel to display the given model.
    public CritterPanel(CritterModel model) {
        this.model = model;
        model.addObserver(this);
        
        this.setFont(FONT);
        setBackground(Color.CYAN);
        setPreferredSize(new Dimension(FONT_SIZE * CritterConstants.WIDTH,
                                       FONT_SIZE * CritterConstants.HEIGHT + FONT_SIZE/2));
    }

    // Paints the critters on the panel.
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // anti-aliasing
        if (ANTI_ALIAS) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                    RenderingHints.VALUE_ANTIALIAS_ON);
        }
        
        // because font is monospaced, all widths should be the same;
        // so we can get char width from any char (in this case x)
        
        // draw all critters
        for (int x = 0; x < CritterConstants.WIDTH; x++) {
            for (int y = 0; y < CritterConstants.HEIGHT; y++) {
                int drawX = x * FONT_SIZE + 2;
                int drawY = (y + 1) * FONT_SIZE;
                
                Color color = model.getColor(x, y);
                drawShadowedString(g, model.getString(x, y), color, drawX, drawY);
            }
        }
    }
    
    // Responds to Observable updates to the model.
    public void update(Observable o, Object arg) {
        repaint();
    }
    
    // Draws the given text with a dark shadow beneath it.
    private void drawShadowedString(Graphics g, String s, Color c, int x, int y) {
        g.setColor(Color.BLACK);
        g.drawString(s, x + 1, y + 1);
        
        if (c != null) {
            g.setColor(c);
        }
        g.drawString(s, x, y);
    }
    
    // Returns the RGB opposite of the given color.
    public Color getReverseColor(Color c) {
        return new Color(~c.getRGB());
    }
}
