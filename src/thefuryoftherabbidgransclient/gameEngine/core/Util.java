/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thefuryoftherabbidgransclient.gameEngine.core;

import java.nio.ByteBuffer;
import thefuryoftherabbidgransclient.gameEngine.rendering.Vertex;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import org.lwjgl.BufferUtils;

/**
 *
 * @author TheDoctor
 */
public class Util {
    private static Util instance = null;
    
    public static Util getInstance(){
        if(instance == null){
            instance = new Util();
        }
        return instance;
    }
    
    private Util(){
        
    }
    
    private FloatBuffer createFloatBuffer(int size){
        return BufferUtils.createFloatBuffer(size);
    }

    private IntBuffer createIntBuffer(int size){
        return BufferUtils.createIntBuffer(size);
    }
    
    public FloatBuffer createFlippedBuffer(Vertex[] vertices){
        FloatBuffer buffer = createFloatBuffer(vertices.length * Vertex.SIZE);
        
        for (Vertex vertice : vertices) {
            buffer.put(vertice.getPos().getX());
            buffer.put(vertice.getPos().getY());
            buffer.put(vertice.getPos().getZ());
            buffer.put(vertice.getTexCoord().getX());
            buffer.put(vertice.getTexCoord().getY());
            buffer.put(vertice.getNormal().getX());
            buffer.put(vertice.getNormal().getY());
            buffer.put(vertice.getNormal().getZ());            
        }
        buffer.flip();
        return buffer;
    }
    
    public FloatBuffer createFlippedBuffer(Matrix4f value){
        FloatBuffer buffer = createFloatBuffer(4*4);
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                buffer.put(value.get(i,j));
            }
        }
        buffer.flip();
        return buffer;
    }

    public IntBuffer createFlippedBuffer(int... values) {
        IntBuffer buffer = createIntBuffer(values.length);
        buffer.put(values);
        buffer.flip();
        return buffer;
    }
    
    public String[] removeEmptyStrings(String[] data){
        ArrayList<String> result = new ArrayList<>();
        
        for(int i = 0; i < data.length; i++){
            if(!data[i].equals("")){
                result.add(data[i]);
            }
        }
        String[] res = new String[result.size()];
        result.toArray(res);
        
        return res;
    }
    
    public int[] toIntArray(Integer[] data){
        int[] result = new int[data.length];
        for(int i = 0; i < data.length; i++){
            result[i] = data[i].intValue();
        }
        return result;
    }

    public ByteBuffer createByteBuffer(int size) {
        return BufferUtils.createByteBuffer(size);
    }
}
