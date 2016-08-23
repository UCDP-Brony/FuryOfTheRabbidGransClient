/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thefuryoftherabbidgransclient.gameEngine.rendering.resourceManagement;

import static org.lwjgl.opengl.GL11.glGenTextures;
import static org.lwjgl.opengl.GL15.glDeleteBuffers;
import static org.lwjgl.opengl.GL15.glGenBuffers;

/**
 *
 * @author TheDoctor
 */
public class TextureResource {
    private int id;
    private int refCount;

    public TextureResource(){
        this.id = glGenTextures();
        this.refCount = 1;
    }

    @Override
    protected void finalize(){
        glDeleteBuffers(id);
    }

    public void addReference(){
        refCount++;
    }

    public boolean removeReference(){
        refCount--;
        return refCount == 0;
    }

    public int getId(){
        return id;
    }
}
