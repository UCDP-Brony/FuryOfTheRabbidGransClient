/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thefuryoftherabbidgransclient.gameEngine.components;

import thefuryoftherabbidgransclient.gameEngine.core.Vector3f;
import thefuryoftherabbidgransclient.gameEngine.rendering.Shader;

/**
 *
 * @author TheDoctor
 */
public class DirectionalLight extends BaseLight{
    public DirectionalLight(Vector3f color, float intensity) {
        super(color, intensity);        
        setShader(new Shader("forward-directional"));
    }
        
    
    public Vector3f getDirection() {
        return getTransform().getTransformedRot().getForward();
    }

}
