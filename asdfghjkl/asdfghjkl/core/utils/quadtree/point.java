package core.utils.quadtree;

public class point {
	private int x;
	private int y;
	public point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public int getX() {
		return this.x;
	}
	public int getY() {
		return this.x;

	}
	public String toString() {
		return "(" + Integer.toString(this.x) + ", " + Integer.toString(this.y) + ")";
	}
}

