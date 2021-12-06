package core.game;

import java.util.ArrayList;
import java.util.List;

public class ItemHandler {
	
	private List<GameItem> items;
	
	public ItemHandler(){
		clear();
	}
	
	public void addItem(GameItem item){
		items.add(item);
	}
	
	public List<GameItem> getItems(){
		return items;
	}
	
	public void clear(){
		items = new ArrayList<GameItem>();
	}

}
