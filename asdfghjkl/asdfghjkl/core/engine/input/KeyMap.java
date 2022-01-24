package core.engine.input;

import java.util.HashMap;
import java.util.Map;
import static org.lwjgl.glfw.GLFW.*;

public class KeyMap {

	public enum ACTIONS{
		MOVE_UP,
		MOVE_DOWN,
		MOVE_LEFT,
		MOVE_RIGHT,
		SPACE_BAR;
	}
	
	private boolean[] keyPos;
	
	private Map<ACTIONS, Integer> actionMap;
	
	public KeyMap(){
		keyPos = new boolean[ACTIONS.values().length];
		restoreDefaults();
		
	}
	
	public void restoreDefaults(){
		actionMap = new HashMap<ACTIONS, Integer>();
		actionMap.put(ACTIONS.MOVE_UP, GLFW_KEY_W);
		actionMap.put(ACTIONS.MOVE_DOWN, GLFW_KEY_S);
		actionMap.put(ACTIONS.MOVE_LEFT, GLFW_KEY_A);
		actionMap.put(ACTIONS.MOVE_RIGHT, GLFW_KEY_D);
		actionMap.put(ACTIONS.SPACE_BAR, GLFW_KEY_SPACE);
		
	}
	
	public boolean getKeyPos(ACTIONS action){
		return keyPos[action.ordinal()];
	}
	
	public void setCallbacks(int key, int action){
		for(ACTIONS act : ACTIONS.values()){
			if(key == actionMap.get(act)){
				if(action == GLFW_PRESS){
					keyPos[act.ordinal()] = true;
				} else if(action == GLFW_RELEASE){
					keyPos[act.ordinal()] = false;
				}
			}
		}
	}
	
}
