/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thefuryoftherabbidgransclient.gameEngine.components;

import thefuryoftherabbidgransclient.gameEngine.core.CoreEngine;
import thefuryoftherabbidgransclient.gameEngine.core.GameObject;
import thefuryoftherabbidgransclient.gameEngine.core.Transform;
import thefuryoftherabbidgransclient.gameEngine.rendering.RenderingEngine;
import thefuryoftherabbidgransclient.gameEngine.rendering.Shader;

/**
 *
 * @author TheDoctor
 */
public abstract class GameComponent {
    
    private GameObject parent;
    
    public void userInput(float delta){};        
    public void serverInput(String in, float delta){};        
    public void update(float delta){};        
    
    public void render(Shader shader, RenderingEngine renderingEngine){};  
    
    public void setParent(GameObject parent){
        this.parent = parent;
    }
    
    public Transform getTransform(){
        return parent.getTransform();
    }
    
    public void addToEngine(CoreEngine engine){};
    
}
