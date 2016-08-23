/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thefuryoftherabbidgransclient.gameEngine.components;

import thefuryoftherabbidgransclient.gameEngine.core.Input;
import thefuryoftherabbidgransclient.gameEngine.core.Vector2f;
import thefuryoftherabbidgransclient.gameEngine.core.Vector3f;
import thefuryoftherabbidgransclient.gameEngine.rendering.Window;

/**
 *
 * @author TheDoctor
 */
public class FreeLook extends GameComponent{
    private static final Vector3f yAxis = new Vector3f(0,1,0);
    private boolean mouseLocked = false;
    private float sensitivity;
    private int unlockMouseKey;

    public FreeLook(float sensitivity){
        this(sensitivity, Input.KEY_ESCAPE);
    }
    
    public FreeLook(float sensitivity, int unlockMouseKey) {
        this.sensitivity = sensitivity;
        this.unlockMouseKey = unlockMouseKey;
    }
    
    @Override
    public void userInput(float delta){
        Vector2f centerPosition = new Vector2f(Window.getWidth()/2, Window.getHeight()/2);
        Input in = Input.getInstance();
        
        if(in.getKey(unlockMouseKey)){
            in.SetCursor(true);
            mouseLocked = false;
        }
        if(in.GetMouseDown(0)){
            in.SetMousePosition(centerPosition);
            in.SetCursor(false);
            mouseLocked = true;
        }        
        if(mouseLocked){
            Vector2f deltaPos = in.GetMousePosition().sub(centerPosition);
            
            boolean rotX = deltaPos.getX() != 0;
            boolean rotY = deltaPos.getY() != 0;
            
         
            if(rotY){
                getTransform().rotate(yAxis, (float) Math.toRadians(deltaPos.getX() * sensitivity));
            }
            if(rotX){
                getTransform().rotate(getTransform().getRot().getRight(),(float) Math.toRadians(-deltaPos.getY() * sensitivity));
            }
            if(rotX || rotY){
                in.SetMousePosition(centerPosition);
            }
        }
    }
}
