/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thefuryoftherabbidgransclient.gameEngine.rendering;

import thefuryoftherabbidgransclient.gameEngine.core.Vector2f;
import thefuryoftherabbidgransclient.gameEngine.core.Vector3f;

/**
 *
 * @author TheDoctor
 */
public class Vertex {
    
    public static final int SIZE = 8;
    
    private Vector3f pos;
    private Vector2f texCoord;
    private Vector3f normal;
    
    public Vertex(Vector3f pos){
        this(pos, new Vector2f(0,0));
    }
        
    public Vertex(Vector3f pos, Vector2f texCoord){
        this(pos, texCoord, new Vector3f(0,0,0));
    }
    
    public Vertex(Vector3f pos, Vector2f texCoord, Vector3f normal){
        this.pos = pos;
        this.texCoord = texCoord;
        this.normal = normal;
    }

    public Vector3f getPos() {
        return pos;
    }

    public void setPos(Vector3f pos) {
        this.pos = pos;
    }

    public Vector2f getTexCoord() {
        return texCoord;
    }

    public void setTexCoord(Vector2f texCoord) {
        this.texCoord = texCoord;
    }

    public Vector3f getNormal() {
        return normal;
    }

    public void setNormal(Vector3f normal) {
        this.normal = normal;
    }
    
    
        
}
