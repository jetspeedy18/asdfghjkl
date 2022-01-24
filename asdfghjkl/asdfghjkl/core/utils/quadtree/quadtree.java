package core.utils.quadtree;

import java.util.ArrayList;

public class quadtree {
	private rectangle boundary;
	private int capacity;
	private ArrayList<point> points;
	private boolean divided;
	private quadtree northwest;
	private quadtree northeast;
	private quadtree southwest;
	private quadtree southeast;
	
	public quadtree(rectangle boundary, int n) {
		this.boundary = boundary;
		this.capacity = n;
		this.points = new ArrayList<>();
		this.divided = false;
		
		//this.northwest = null;
		//this.northeast = null;
		//this.southwest = null;
		//this.southeast = null;
	}
	
	public String toStringTest() {
		String res = null;
		res = "nw: " + northwest.getPoints() + "  ne: " + northeast.getPoints() + "  sw: " + southwest.getPoints() + "  se: " + southeast.getPoints();
		return res;
	}
	
	private String getPoints() {
		String f=null;
		for (int i = 0; i<this.points.size(); i++) {
			f += this.points.get(i).toString();
		}
		return f;
	}

	private void subdivide() {
		rectangle nw = new rectangle(boundary.getX()-boundary.getW()/2, boundary.getY()+boundary.getH()/2, boundary.getW()/2, boundary.getH()/2);
		this.northwest = new quadtree(nw, this.capacity);
		rectangle ne = new rectangle(boundary.getX()+boundary.getW()/2, boundary.getY()+boundary.getH()/2, boundary.getW()/2, boundary.getH()/2);
		this.northeast = new quadtree(ne, this.capacity);
		rectangle sw = new rectangle(boundary.getX()-boundary.getW()/2, boundary.getY()-boundary.getH()/2, boundary.getW()/2, boundary.getH()/2);
		this.southwest = new quadtree(sw, this.capacity);
		rectangle se = new rectangle(boundary.getX()+boundary.getW()/2, boundary.getY()-boundary.getH()/2, boundary.getW()/2, boundary.getH()/2);
		this.southeast = new quadtree(se, this.capacity);
		this.divided = true;
	}

	
	public void insert(point p) {
		if(!this.boundary.contains(p)) {
			return;
		}
		
		if(this.points.size() < this.capacity) {
			points.add(p);
		}
		else {
			if(!this.divided) {
				subdivide();
			}
			this.northwest.insert(p);
			this.northeast.insert(p);
			this.southwest.insert(p);
			this.southeast.insert(p);
		}
	}
	
	public ArrayList<point> query(rectangle range) {
		ArrayList<point> found = new ArrayList<>();
		if (!this.boundary.intersects(range)) {
			return found;
		}
		else {
			for (int i=0; i<this.points.size(); i++) {
				if (range.contains(points.get(i))) {
					found.add(this.points.get(i));
				}
			}
		}
		if (this.divided) {
			found.addAll(this.northwest.query(range));
			found.addAll(this.northeast.query(range));
			found.addAll(this.southwest.query(range));
			found.addAll(this.southeast.query(range));
		}
		
		return found;
	}
}
