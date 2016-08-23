/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thefuryoftherabbidgransclient.gameEngine.core;

import thefuryoftherabbidgransclient.gameEngine.rendering.RenderingEngine;


/**
 *
 * @author TheDoctor
 */
public abstract class Game {
    
    private GameObject root = new GameObject();
    
    private GameObject getRootObject(){
        if(root == null){
            root = new GameObject();
        }
        return root;
    }
    
    public void init(){}
    
    public void userInput(float delta){
        getRootObject().userInputAll(delta);
    }

    public void gotInputFromServer(String in, float delta){
        getRootObject().serverInputAll(in, delta);
    }
    
    public void update(float delta){
        getRootObject().updateAll(delta);
    }

    public void render(RenderingEngine renderingEngine){
        renderingEngine.render(getRootObject());
    }
    
    public void addObject(GameObject object){
        getRootObject().addChild(object);
    }
    
    public void setEngine(CoreEngine engine){
        getRootObject().setEngine(engine);
    }
}
