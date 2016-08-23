/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thefuryoftherabbidgransclient.gameEngine.rendering.resourceManagement;

import java.util.ArrayList;
import java.util.HashMap;
import static org.lwjgl.opengl.GL15.glDeleteBuffers;
import static org.lwjgl.opengl.GL20.glCreateProgram;

/**
 *
 * @author TheDoctor
 */
public class ShaderResource {
    private int program;
    private int refCount;
    private HashMap<String, Integer> uniforms;
    private ArrayList<String> uniformNames;
    private ArrayList<String> uniformTypes;

    public ShaderResource(){
        program = glCreateProgram();
        refCount = 1;
        if(program == 0){
            System.err.println("Shader creation failed. Could not find valid memory location in constructor.");
            System.exit(1);
        }
        uniforms = new HashMap<>();
        uniformNames = new ArrayList<>();
        uniformTypes = new ArrayList<>();
    }

    @Override
    protected void finalize(){
        glDeleteBuffers(program);
    }

    public void addReference(){
        refCount++;
    }

    public boolean removeReference(){
        refCount--;
        return refCount == 0;
    }

    public int getProgram(){
        return program;
    }

    public HashMap<String, Integer> getUniforms() {
        return uniforms;
    }

    public ArrayList<String> getUniformNames() {
        return uniformNames;
    }

    public ArrayList<String> getUniformTypes() {
        return uniformTypes;
    }

}
