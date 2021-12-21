package core.game;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import core.engine.graphics.Texture;

public class MapHandler {

	public static enum TileType {
		AIR("null"),
		
		PRESSURE_PLATE("res/images/test.jpg"),
		
		INSIDE_1("res/images/test.jpg"),
		INSIDE_2("res/images/test.jpg"),
		
		OUTSIDE_1("res/images/test.jpg"),
		OUTSIDE_2("res/images/test.jpg");
		
		//private String path;
		private int texId;
		
		
		private TileType(String path){
			//this.path = path;
			try {
				texId = Texture.loadTex(path);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		public int getTexId(){
			return texId;
		}
		
	}
	
	private final int xMax, yMax;
	private int[][] tiles;
	
	private MapHandler(int mapSizeX, int mapSizeY){
		tiles = new int[mapSizeX][mapSizeY];
		xMax = mapSizeX;
		yMax = mapSizeY;
	}
	
	public void render(){
		// TODO
		System.out.println(tiles);
	}
	
	
	public static MapHandler loadMap(String path) throws NumberFormatException, IOException{
		Queue<Integer> in = new LinkedList<Integer>();
		try(BufferedReader reader = new BufferedReader(new FileReader(path))){
			String value;
			while((value = reader.readLine()) != null){
				String[] values = value.split(",");
				for(String val : values){
					in.add(Integer.valueOf(val));
				}
			}
		}
		
		MapHandler out = new MapHandler(in.remove(), in.remove());
		
		for(int i = 0; i < out.xMax; i++){
			for(int j = 0; j < out.yMax; j++){
				out.tiles[i][j] = in.remove();
			}
		}
		
		
		return out;
	}
	
	
}
