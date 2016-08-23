/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thefuryoftherabbidgransclient.gameEngine.components;

import thefuryoftherabbidgransclient.gameEngine.core.CoreEngine;
import thefuryoftherabbidgransclient.gameEngine.core.Vector3f;
import thefuryoftherabbidgransclient.gameEngine.rendering.RenderingEngine;
import thefuryoftherabbidgransclient.gameEngine.rendering.Shader;

/**
 *
 * @author TheDoctor
 */
public class BaseLight extends GameComponent {

    private Vector3f color;
    private float intensity;
    private Shader shader;

    public BaseLight(Vector3f color, float intensity) {
        this.color = color;
        this.intensity = intensity;
    }

    @Override
    public void addToEngine(CoreEngine engine){
        engine.getRenderingEngine().addLight(this);
    }
    
    public Shader getShader(){
        return shader;
    }
    
    public void setShader(Shader shader){
        this.shader = shader;
    }

    public Vector3f getColor() {
        return color;
    }

    public void setColor(Vector3f color) {
        this.color = color;
    }

    public float getIntensity() {
        return intensity;
    }

    public void setIntensity(float intensity) {
        this.intensity = intensity;
    }   
}
