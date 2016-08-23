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
public class PointLight extends BaseLight{
    private static final int COLOR_DEPTH = 256;
    
    private Attenuation attenuation;
    private float range;

    public PointLight(Vector3f color, float intensity, Attenuation attenuation) {
        super(color, intensity);
        this.attenuation = attenuation;
        
        float a = attenuation.getExponent();
        float b = attenuation.getLinear();
        float c = attenuation.getConstant() - COLOR_DEPTH * getIntensity() * getColor().max();
        
        this.range = (float)(-b + Math.sqrt(b*b) - 4 * a * c)/(2*a);
        
        setShader(new Shader("forward-point"));
    }
   
    public Attenuation getAttenuation(){
        return attenuation;
    }

    public float getRange() {
        return range;
    }

    public void setRange(float range) {
        this.range = range;
    }       
}
