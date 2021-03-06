package core.engine.graphics;

import static org.lwjgl.opengl.GL20.*;

import org.joml.Matrix4f;

import core.engine.Camera;
import core.engine.ItemHandler;
import core.utils.Utils;

public class Renderer {
	
	private ShaderLoader program;
	
	private ItemHandler handler;
	
	private Camera camera;
	
	public Renderer(Camera camera, ItemHandler handler){
		this.handler = handler;
		this.camera = camera;
		try{
			program = new ShaderLoader(ShaderLoader.createShaderLoader());
			
			program.createShader(Utils.load("/shaders/fragment.fs"), GL_FRAGMENT_SHADER);
			program.createShader(Utils.load("/shaders/vertex.vs"), GL_VERTEX_SHADER);
			
			program.link();
			
			program.createUniform("texture_sampler");
			program.createUniform("trans");
			program.createUniform("proj");
			
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void bind(){
		program.bind();
	}
	
	public void render(){
		
		handler.render(program);
	}
	
	public void unbind(){
		program.unbind();
	}
	
	public void cleanup(){
		program.cleanup();
	}

	public void resetUniforms(int s) {
		program.setUniform("texture_sampler", 0);
		
		program.setUniform("proj", camera.getCameraPos());
		program.setUniform("trans", new Matrix4f().identity().scale(s));
	}
	
	public void resetUniforms(Matrix4f mat, int s){

		program.setUniform("texture_sampler", 0);
		
		program.setUniform("proj", camera.getCameraPos());
		program.setUniform("trans", mat.scale(s));
		
	}
}
