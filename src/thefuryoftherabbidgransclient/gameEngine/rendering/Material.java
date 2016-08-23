/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thefuryoftherabbidgransclient.gameEngine.rendering;

import java.util.HashMap;
import thefuryoftherabbidgransclient.gameEngine.rendering.resourceManagement.MappedValues;

/**
 *
 * @author TheDoctor
 */
public class Material extends MappedValues {

    private HashMap<String, Texture> textureHashMap;

    public Material(){
        super();
        textureHashMap = new HashMap<>();
    }

    public void addTexture(String name, Texture texture){textureHashMap.put(name, texture);}
    
    public Texture getTexture(String name){
        Texture result = textureHashMap.get(name);
        if(result != null){
            return result;
        } 
        return new Texture("alien_ground_texture_05-1024x1024.png");
    }
}
