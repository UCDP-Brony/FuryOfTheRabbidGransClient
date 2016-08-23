/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thefuryoftherabbidgransclient.gameEngine.core;

import thefuryoftherabbidgransclient.gameEngine.rendering.Window;
import java.util.logging.Level;
import java.util.logging.Logger;
import thefuryoftherabbidgransclient.game.TestGame;
import thefuryoftherabbidgransclient.gameEngine.rendering.RenderingEngine;

/**
 *
 * @author TheDoctor
 */
public class CoreEngine {    
    private boolean isRunning;
    private final Game game;
    private RenderingEngine renderingEngine;
    private int width, height;
    private double frameTime;
    
    public CoreEngine(int width, int height, int frameRate, Game game){        
        isRunning = false;
        this.game = game;     
        this.width = width;
        this.height = height;
        this.frameTime = 1.0/frameRate;
        game.setEngine(this);
    }

    
    public void createWindow(String title){
        Window.createWindow(width, height, title);
        this.renderingEngine = new RenderingEngine();
    }
    
    public void start(){
        if(isRunning){
            return;
        }        
        run();
    }
    
    public void stop(){
        if(!isRunning){
            return;
        }
        isRunning = false;
    }
    
    public void run(){
        isRunning = true;
        
        int frames = 0;
        int frameCounter = 0;       
        
        game.init();
        
        double lastTime = Time.getTime();
        double unprocessedTime = 0.0;
        
        
        while(isRunning){
            
            boolean render = false;
            
            double startTime = Time.getTime();
            double passedTime = startTime - lastTime;
            lastTime = startTime;
            
            unprocessedTime+=passedTime;
            frameCounter+=passedTime;
            
            while(unprocessedTime > frameTime){
                render = true;
                unprocessedTime -= frameTime;                                
                if(Window.isCloseRequested()){
                    stop();
                }         

                game.update((float) frameTime);                
                game.userInput((float) frameTime);
                if(frameCounter>=1){
                    System.out.println(frames);
                    frames = 0;
                    frameCounter = 0;
                }
            }
            if(render){
                game.render(renderingEngine);
                Window.render();
                //render();
                frames++;
            } else {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException ex) {
                    Logger.getLogger(CoreEngine.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        cleanUp();
    }
    
    public void gotInputFromServer(String in){
        game.gotInputFromServer(in, (float) frameTime);
    }

    public void cleanUp(){
        Window.dispose();
    }

    public RenderingEngine getRenderingEngine() {
        return renderingEngine;
    }
}
