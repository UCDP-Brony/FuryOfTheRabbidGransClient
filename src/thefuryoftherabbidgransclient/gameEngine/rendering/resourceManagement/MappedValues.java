/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thefuryoftherabbidgransclient.gameEngine.rendering.resourceManagement;

import java.util.HashMap;
import thefuryoftherabbidgransclient.gameEngine.core.Vector3f;

/**
 *
 * @author TheDoctor
 */
public abstract class MappedValues {
    private HashMap<String, Vector3f> vector3fHashMap;
    private HashMap<String, Float> floatHashMap;
    
    public MappedValues(){
        vector3fHashMap = new HashMap<>();
        floatHashMap = new HashMap<>();
    }
    
    public void addVector3f(String name, Vector3f vector){vector3fHashMap.put(name, vector);}    
    public void addFloat(String name, float floatValue){floatHashMap.put(name, floatValue);}
    
    public Vector3f getVector3f(String name){
        
        Vector3f result = vector3fHashMap.get(name);
        if(result != null){
            return result;
        }
        return new Vector3f(0,0,0);
    }
    
    
    public float getFloat(String name){
        Float result = floatHashMap.get(name);
        if(result != null){
            return result;
        }
        return 0;
    }
}
