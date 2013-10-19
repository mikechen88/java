import java.awt.Graphics;


public abstract class AbstractShape implements Shape{

	int height;
	int width;
	int x;
	int y;
	int xx,yy;
	public AbstractShape ( int xx,int yy,int height ,int x, int y){
		this.height=height;
		this.x=x;
		this.y=y;
		this.width=height;
		this.xx=xx;
		this.yy=yy;
	}
	
	public void paintMe(Graphics g){};
	
	public int setHeight(int height){
		this.height=height;
		return this.height;
	}
	
	public int getHeight(){
		return this.height;
	}
}
