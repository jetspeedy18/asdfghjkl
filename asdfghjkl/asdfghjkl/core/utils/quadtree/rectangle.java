package core.utils.quadtree;

public class rectangle {
	private int x;
	private int y;
	private int w;
	private int h;

	public rectangle(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	
	public int getX() {
		return this.x;
	}
	public int getY() {
		return this.x;
	}
	public int getW() {
		return this.x;
	}
	public int getH() {
		return this.x;
	}
	public boolean contains(point p) {
		return (p.getX()>this.x-this.w &&
				p.getX()<this.x+this.w &&
				p.getY()>this.y-this.h &&
				p.getY()<this.y+this.w);
	}

	public boolean intersects(rectangle range) {
		return !(range.getX()-range.getW()>this.x+this.w || 
				range.getX()+range.getW()<this.x-this.w ||
				range.getY()-range.getH()>this.y+this.h || 
				range.getY()+range.getH()<this.y-this.h);
	}
	public String prntbounds() {
		String res = null;
		res = "top left: (" + Integer.toString(this.x-this.w) + ", " + Integer.toString(this.y+this.h) + ") top right: (" + Integer.toString(this.x+this.w) + ", " + Integer.toString(this.y+this.h) + ") \n";
		res += "bottom left: (" + Integer.toString(this.x-this.w) + ", " + Integer.toString(this.y-this.h) + ") bottom right: (" + Integer.toString(this.x+this.w) + ", " + Integer.toString(this.y-this.h) + ") \n";
		return res;
	}
}

