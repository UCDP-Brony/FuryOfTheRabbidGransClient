/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thefuryoftherabbidgransclient.gameEngine.rendering.resourceManagement;

import static org.lwjgl.opengl.GL15.glDeleteBuffers;
import static org.lwjgl.opengl.GL15.glGenBuffers;

/**
 *
 * @author TheDoctor
 */
public class MeshResource {    
	private int vbo;
	private int ibo;
        private int size;
        private int refCount;
        
        public MeshResource(int size){
            vbo = glGenBuffers();
            ibo = glGenBuffers();
            this.size = size;
            this.refCount = 1;
        }
        
        @Override
        protected void finalize(){
            glDeleteBuffers(vbo);
            glDeleteBuffers(ibo);            
        }
        
        public void addReference(){
            refCount++;
        }
        
        public boolean removeReference(){
            refCount--;
            return refCount == 0;
        }
        
        public int getVbo(){
            return vbo;
        }
        
        public int getIbo(){
            return ibo;
        }
        
        public int getSize(){
            return size;
        }
        
        public void setSize(int size){
            this.size = size;
        }
}
