package core.engine.graphics;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.stb.STBImage.*;

import java.io.FileNotFoundException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import org.lwjgl.system.MemoryStack;

public class Texture {

	private final int id;
	
	public Texture(int id) {
		this.id = id;
	}
	
	public void bind() {
		glBindTexture(GL_TEXTURE_2D, id);
	}
	
	public int getId(){
		return id;
	}
	
	public static int loadTex(String name) throws Exception {
		ByteBuffer buf;
		int width, height;
		
		try (MemoryStack stack = MemoryStack.stackPush()){
			IntBuffer w = stack.mallocInt(1);
			IntBuffer h = stack.mallocInt(1);
			IntBuffer channels = stack.mallocInt(1);
			
			buf = stbi_load(name, w, h, channels, 4);
			if(buf == null){
				throw new FileNotFoundException(name + ":" + stbi_failure_reason());
			}
			
			width = w.get();
			height = h.get();
		}
		
		int textureId = glGenTextures();
		glBindTexture(GL_TEXTURE_2D, textureId);
		
		glPixelStorei(GL_UNPACK_ALIGNMENT, 1);
		
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, buf);
		
		glGenerateMipmap(GL_TEXTURE_2D);
		
		stbi_image_free(buf);
		
		return textureId;
	}
	
	public void cleanup(){
		glDeleteTextures(id);
	}

	private void glDeleteTextures(int id2) {
		// TODO Auto-generated method stub
		
	}

	public static int safeLoadTex(String string) {
		try{
			 return loadTex(string);
		} catch(Exception e){
			e.printStackTrace();
		}
		return -1;
	}
}
