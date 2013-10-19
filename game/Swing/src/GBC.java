/*
 * gbc - a convenience class to tame the gridbaglayout 
 * 
 */
import java.awt.*;

//this class simplifies the use of gridBagConstraints
public class GBC extends GridBagConstraints{
	//construct a GBC with a given gridx and gridy position and all other grid bag constraint values set to the default
	//gridx     the gridx position
	//gridy      the gridy position
	public GBC ( int gridx, int gridy){
		this .gridx=gridx;
		this.gridy=gridy;
	}
	
	
	/*//construct a GBC with given gridx, gridy, gridwidth, gridheight
	 * and all other grid bag constraint values set to the default
	 * gridx      the grid position
	 * gridy       the gridy position
	 * gridwidth      the cell spanx in x-direction
	 * gridheight       the cell span in y-direction 
	 */
	
	public GBC ( int gridx, int gridy, int gridwidth, int gridheight){
		this.gridx=gridx;
		this.gridy=gridy;
		this.gridwidth=gridwidth;
		this.gridheight=gridheight;
		
	}
	
	//set the anchor 
	//anchor    is the anchor value
	// return this object for further modification
	public GBC setAnchor ( int anchor){
		this.anchor=anchor;
		return this;
	}
	
	//sets the fill direction
	// fill         the fill direction
	// return this object 
	public GBC setFill( int fill){
		this.fill=fill;
		return this;
	}
	
	//sets the cell weights
	//weightx      the cell weight in x-direction
	// weighty     the cell weight in y-direction 
	public GBC setWeight ( double weightx, double weighty){
		this.weightx=weightx;
		this.weighty=weighty;
		return this;
	}
	
	//sets the insets of this cell
	// distance      the spacing to use in all directions
	public GBC setInsets ( int distance ){
		this.insets=new Insets( distance, distance, distance, distance);
		return this;
	}
	
	//set the insets of this cell
	// top      the spacing to use on top
	//left      the spacing to use to the left
	//bottom     the spacing to use on the bottom
	//right      the spacing to use to the right 
	
	public  GBC setInsets ( int top, int left, int bottom,int right){
		this.insets=new Insets( top,left, bottom,right);
		return this;
	}
	
	//sets the internal padding
	//ipadx the internal padding in x-direction
	//ipady    the internal padding in y-dirction
	public GBC setIpad ( int ipadx, int ipady){
		this.ipadx=ipadx;
		this.ipady=ipady;
		return this;
	}
	
	
	
	
	
	
}
