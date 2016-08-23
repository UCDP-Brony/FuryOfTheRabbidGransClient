/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thefuryoftherabbidgransclient.gameEngine.rendering;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import thefuryoftherabbidgransclient.gameEngine.core.Vector2f;

/**
 *
 * @author TheDoctor
 */
public class Window {
    public static void createWindow(int width, int height, String title){
        Display.setTitle(title);
        try {
            Display.setDisplayMode(new DisplayMode(width, height));
            Display.create();
            Keyboard.create();
            Mouse.create();
        } catch (LWJGLException ex) {
            Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void render(){
        Display.update();
    }
    
    public static void dispose(){
        Display.destroy();
        Keyboard.destroy();
        Mouse.destroy();
    }
    
    public static boolean isCloseRequested(){
        return Display.isCloseRequested();
    }
    
    public static int getWidth(){
        return Display.getWidth();
    }
    
    public static int getHeight(){
        return Display.getHeight();
    }
    
    public static String getTitle(){
        return Display.getTitle();
    }
    
    public Vector2f getCenter(){
        return new Vector2f(getWidth()/2, getHeight()/2);
    }
}
