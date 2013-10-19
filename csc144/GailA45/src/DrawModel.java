import java.awt.Color;
import java.util.ArrayList;
import java.util.List;


public class DrawModel {
	// instance variables
	ArrayList<AbstractShape> things;

	ArrayList<DrawView> viewers;
	
	//View view;
	// constructor
	public DrawModel() {
		// Start with no Shapes and no new ones to add
		things = new ArrayList<AbstractShape>();
		
	
		viewers = new ArrayList<DrawView>();
		
	}

	public void add(AbstractShape s) {
		things.add(s);
		
	}

	public  void cycle(){
		for (DrawView v : viewers) {
			v.notify(this);
		}
	}
	

	// Add the given DrawView to the list of viewers to be notified when a change is made.	
	public void addView(DrawView dv) {
		viewers.add(dv);
	dv.notify(this);
	}

	// Returns a copy of the list of Shapes in the view.
	public List<AbstractShape> getShapes() {
		return things;
	}

	
}
