package core.game;

import org.lwjgl.system.MemoryUtil;

import core.engine.graphics.Texture;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glBufferData;
import static org.lwjgl.opengl.GL15.glDeleteBuffers;
import static org.lwjgl.opengl.GL15.glGenBuffers;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glDeleteVertexArrays;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

public class Mesh {

    private final int vaoId;

    private final int posVboId;

    private final int texCoordVboId;

    private final int idxVboId;

    private final int vertexCount;
    
    private final Texture texture;
    
    private static final float[] position = new float[]{
            -32f,  32f, 0.0f,
            -32f, -32f, 0.0f,
             32f, -32f, 0.0f,
             32f,  32f, 0.0f
        };
    private static final float[] textureCoord = new float[]{
    		0.0f, 0.0f,
    		0.0f, 1.0f,
    		1.0f, 1.0f,
    		1.0f, 0.0f
        };
    private static final int[] indice = new int[]{
            0, 1, 3, 3, 1, 2,
        };
    
    public Mesh(Texture texture){
    	this(position, textureCoord, indice, texture);
    }
    

    public Mesh(float[] positions, float[] textureCoords, int[] indices, Texture texture) {
        FloatBuffer posBuffer = null;
        FloatBuffer textCoordBuffer = null;
        IntBuffer indicesBuffer = null;
        try {
        	this.texture = texture;
            vertexCount = indices.length;

            vaoId = glGenVertexArrays();
            glBindVertexArray(vaoId);

            // Position VBO
            posVboId = glGenBuffers();
            posBuffer = MemoryUtil.memAllocFloat(positions.length);
            posBuffer.put(positions).flip();
            glBindBuffer(GL_ARRAY_BUFFER, posVboId);
            glBufferData(GL_ARRAY_BUFFER, posBuffer, GL_STATIC_DRAW);
            glEnableVertexAttribArray(0);
            glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);

            // tex VBO
            texCoordVboId = glGenBuffers();
            textCoordBuffer = MemoryUtil.memAllocFloat(textureCoords.length);
            textCoordBuffer.put(textureCoords).flip();
            glBindBuffer(GL_ARRAY_BUFFER, texCoordVboId);
            glBufferData(GL_ARRAY_BUFFER, textCoordBuffer, GL_STATIC_DRAW);
            glEnableVertexAttribArray(1);
            glVertexAttribPointer(1, 2, GL_FLOAT, false, 0, 0);

            // Index VBO
            idxVboId = glGenBuffers();
            indicesBuffer = MemoryUtil.memAllocInt(indices.length);
            indicesBuffer.put(indices).flip();
            glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, idxVboId);
            glBufferData(GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL_STATIC_DRAW);

            glBindBuffer(GL_ARRAY_BUFFER, 0);
            glBindVertexArray(0);
        } finally {
            if (posBuffer != null) {
                MemoryUtil.memFree(posBuffer);
            }
            if (textCoordBuffer != null) {
                MemoryUtil.memFree(textCoordBuffer);
            }
            if (indicesBuffer != null) {
                MemoryUtil.memFree(indicesBuffer);
            }
        }
    }

    public int getVaoId() {
        return vaoId;
    }

    public int getVertexCount() {
        return vertexCount;
    }
    
    public void render(){
 
    	glActiveTexture(GL_TEXTURE0);
		glBindTexture(GL_TEXTURE_2D, getTextureId());
		
		glBindVertexArray(getVaoId());
		glEnableVertexAttribArray(0);
		glDrawElements(GL_TRIANGLES, getVertexCount(), GL_UNSIGNED_INT, 0);
		glDisableVertexAttribArray(0);
		glBindVertexArray(0);
    }

    public void cleanUp() {
        glDisableVertexAttribArray(0);

        // Delete the VBOs
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glDeleteBuffers(posVboId);
        glDeleteBuffers(texCoordVboId);
        glDeleteBuffers(idxVboId);
        
        texture.cleanup();

        // Delete the VAO
        glBindVertexArray(0);
        glDeleteVertexArrays(vaoId);
    }
    
    public int getTextureId(){
    	return texture.getId();
    }
    
}