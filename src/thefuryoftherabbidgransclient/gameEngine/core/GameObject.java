/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thefuryoftherabbidgransclient.gameEngine.core;

import thefuryoftherabbidgransclient.gameEngine.components.GameComponent;
import java.util.ArrayList;
import java.util.function.Consumer;
import thefuryoftherabbidgransclient.gameEngine.rendering.RenderingEngine;
import thefuryoftherabbidgransclient.gameEngine.rendering.Shader;

/**
 *
 * @author TheDoctor
 */
public class GameObject {
    private ArrayList<GameObject> children;
    private ArrayList<GameComponent> components;
    private Transform transform;
    private CoreEngine engine;
    
    public GameObject(){
        children = new ArrayList<>();
        components = new ArrayList<>();
        transform = new Transform();
        engine = null;
    }    
    
    public void addChild(GameObject child){
        children.add(child);
        child.setEngine(engine);
        child.getTransform().setParent(transform);
    }
    
    public GameObject addComponent(GameComponent component){
        components.add(component);
        component.setParent(this);
        return this;
    }
    
    public void userInputAll(float delta){
        userInput(delta);
        
        children.stream().forEach((child) -> {
            child.userInputAll(delta);
        });
    }
    
    public void serverInputAll(String in, float delta){
        serverInput(in, delta);
        
        children.stream().forEach((child) -> {
            child.serverInputAll(in, delta);
        });
    }
    
    public void updateAll(float delta){
        update(delta);
        
        children.stream().forEach((child) -> {
            child.updateAll(delta);
        });
    }
    
    public void renderAll(Shader shader, RenderingEngine renderingEngine){
        render(shader, renderingEngine);
        
        children.stream().forEach((GameObject child) -> {
            child.renderAll(shader, renderingEngine);
        });
    }
    
    public void userInput(float delta){
        transform.update();
        components.stream().forEach((component) -> {
            component.userInput(delta);
        });
    }
    
    public void serverInput(String in, float delta){
        transform.update();
        components.stream().forEach((component) -> {
            component.serverInput(in, delta);
        });
    }
    
    public void update(float delta){
        components.stream().forEach((component) -> {
            component.update(delta);
        });
    }
    
    public void render(Shader shader, RenderingEngine renderingEngine){
        components.stream().forEach((component) -> {
            component.render(shader, renderingEngine);
        });
    }
    
    public ArrayList<GameObject> getAllAttached(){
        ArrayList<GameObject> result = new ArrayList<>();
        
        children.stream().forEach((child) -> {
            result.addAll(child.getAllAttached());
        });
        result.add(this);
        return result;
    }
    
    public Transform getTransform(){
        return transform;
    }
    
    public void setEngine(CoreEngine engine){
        if(this.engine != engine){
            this.engine = engine;
            components.stream().forEach((component) -> {
                component.addToEngine(engine);
            });
            children.stream().forEach((GameObject child) -> {
                child.setEngine(engine);
            });
        }
    }
}
