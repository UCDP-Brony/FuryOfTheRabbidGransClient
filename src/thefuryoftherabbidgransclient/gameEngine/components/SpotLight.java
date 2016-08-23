/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thefuryoftherabbidgransclient.gameEngine.components;

import thefuryoftherabbidgransclient.gameEngine.core.Vector3f;
import thefuryoftherabbidgransclient.gameEngine.rendering.Attenuation;
import thefuryoftherabbidgransclient.gameEngine.rendering.Shader;

/**
 *
 * @author TheDoctor
 */
public class SpotLight extends PointLight{
    private float cutoff;

    public SpotLight(Vector3f color, float intensity, Attenuation attenuation,float cutoff) {
        super(color, intensity, attenuation);
        this.cutoff = cutoff;
        setShader(new Shader("forward-spot"));
    }

    public Vector3f getDirection() {
        return getTransform().getTransformedRot().getForward();
    }

    public float getCutoff() {
        return cutoff;
    }

    public void setCutoff(float cutoff) {
        this.cutoff = cutoff;
    }        
}
