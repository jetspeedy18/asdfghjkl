package core.game;


import static org.lwjgl.opengl.GL20.*;

import java.util.HashMap;
import java.util.Map;

public class ShaderLoader {

	private final int programId;
	
	private final Map<String, Integer> uniforms;
	
	public ShaderLoader(int id){
		this.programId = id;
		uniforms = new HashMap<String, Integer>();
		
	}
	
	public static int createShaderLoader() throws Exception {
		int id = glCreateProgram();
		if(id == 0){
			throw new Exception("dudvidricketsranddrommenrandutistandhenselkipolkkaa");
		}
		return id;
	}
	
	public void createUniform(String name) throws Exception {
		int loc = glGetUniformLocation(programId, name);
		if(loc<0) throw new Exception("crabbapples in late may");
		uniforms.put(name, loc);
	}
	
	public void setUniform(String name, int value){
		glUniform1i(uniforms.get(name), value);
	}
	
	public void bind(){
		glUseProgram(programId);
	}
	
	public void unbind(){
		glUseProgram(0);
	}
	
	public int createShader(String code, int type) throws Exception{
		int shaderId = glCreateShader(type);
		
		if(shaderId == 0) throw new Exception("Alvin york sitting on a cornflake");
		
		glShaderSource(shaderId, code);
		glCompileShader(shaderId);
		
		glAttachShader(programId, shaderId);
		
		return shaderId;
		
	}
	
	public void link() throws Exception{
		glLinkProgram(programId);
		if(glGetProgrami(programId, GL_LINK_STATUS) == 0) throw new Exception(glGetProgramInfoLog(programId, 1024));
		glValidateProgram(programId);
		if(glGetProgrami(programId, GL_VALIDATE_STATUS) == 0) throw new Exception(glGetProgramInfoLog(programId, 1024));
	}
	
	public void cleanup(){
		unbind();
		if(programId != 0) glDeleteProgram(programId);
	}
	
}
