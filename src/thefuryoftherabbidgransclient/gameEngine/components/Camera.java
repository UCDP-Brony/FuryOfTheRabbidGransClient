/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thefuryoftherabbidgransclient.gameEngine.components;

import thefuryoftherabbidgransclient.gameEngine.core.CoreEngine;
import thefuryoftherabbidgransclient.gameEngine.core.Input;
import thefuryoftherabbidgransclient.gameEngine.core.Matrix4f;
import thefuryoftherabbidgransclient.gameEngine.core.Quaternion;
import thefuryoftherabbidgransclient.gameEngine.core.Vector2f;
import thefuryoftherabbidgransclient.gameEngine.core.Vector3f;
import thefuryoftherabbidgransclient.gameEngine.rendering.RenderingEngine;
import thefuryoftherabbidgransclient.gameEngine.rendering.Window;

/**
 *
 * @author TheDoctor
 */
public class Camera extends GameComponent {
    private Vector3f pos;
    private Vector3f forward;
    private Vector3f up;
    private Matrix4f projection;
    
    public Camera(float fov, float aspect, float zNear, float zFar){
        this.pos = new Vector3f(0,0,0);
        this.forward = new Vector3f(0,0,1).normalized();
        this.up = new Vector3f(0,1,0).normalized();
        this.projection = new Matrix4f().initPerspective(fov, aspect, zNear, zFar);
    }
    
    public Matrix4f getViewProjection(){
        Matrix4f cameraRotation = getTransform().getTransformedRot().conjugate().toRotationMatrix();
        Vector3f cameraPos = getTransform().getTransformedPos().mul(-1);
        Matrix4f cameraTranslation = new Matrix4f().initTranslation(cameraPos.getX(), cameraPos.getY(), cameraPos.getZ());
        
        return projection.mul(cameraRotation.mul(cameraTranslation));        
    }
    
    @Override 
    public void addToEngine(CoreEngine engine){
        engine.getRenderingEngine().addCamera(this);
    }
}
