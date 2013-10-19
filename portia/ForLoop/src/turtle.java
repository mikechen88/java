import acm.graphics.GTurtle;
import acm.program.*;
import acm.graphics.*;

public class turtle extends GraphicsProgram {
	
	
	
    public void run() {
        this.setSize(400, 200);
        GTurtle turtle = new GTurtle(50, 100);
         add(turtle);

        turtle.setDirection(0);
        while (true) {
            if (turtle.getDirection() == 0 && turtle.getX() > getWidth()) {
                turtle.setDirection(180);
             }
            else if (turtle.getDirection() == 180 && turtle.getX() < 0) {
                turtle.setDirection(0);
            }
            turtle.forward(20);
        }

    }
 }

