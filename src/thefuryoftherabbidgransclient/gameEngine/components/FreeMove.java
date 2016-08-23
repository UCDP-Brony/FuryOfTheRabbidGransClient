/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thefuryoftherabbidgransclient.gameEngine.components;

import thefuryoftherabbidgransclient.gameEngine.core.Input;
import thefuryoftherabbidgransclient.gameEngine.core.Vector3f;

/**
 *
 * @author TheDoctor
 */
public class FreeMove extends GameComponent{
    private float speed;
    private int forwardKey;
    private int backKey;
    private int leftKey;
    private int rightKey;

    public FreeMove(float speed) {
        this(speed, Input.KEY_Z, Input.KEY_S, Input.KEY_Q, Input.KEY_D);
    }

    public FreeMove(float speed, int forwardKey, int backKey, int leftKey, int rightKey) {
        this.speed = speed;
        this.forwardKey = forwardKey;
        this.backKey = backKey;
        this.leftKey = leftKey;
        this.rightKey = rightKey;
    }
    
    @Override
    public void userInput(float delta){
        float movAmt = speed * delta;
        Input in = Input.getInstance();
                
        if(in.getKey(forwardKey)){
            move(getTransform().getRot().getForward(), movAmt);            
        }
        if(in.getKey(backKey)){
            move(getTransform().getRot().getForward(), -movAmt);
        }
        if(in.getKey(leftKey)){
            move(getTransform().getRot().getLeft(), movAmt);
        }
        if(in.getKey(rightKey)){
            move(getTransform().getRot().getRight(), movAmt);
        }        
    }
    
    public void move(Vector3f dir, float amount){
        getTransform().setPos(getTransform().getPos().add(dir.mul(amount)));
    }
}
