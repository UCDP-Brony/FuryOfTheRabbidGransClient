/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thefuryoftherabbidgransclient.gameEngine.core;

/**
 *
 * @author TheDoctor
 */
public class Time {
    
    private static double delta;
    private static final long SECOND = 1000000000L;
    
    public static double getTime(){
        return (double)System.nanoTime()/(double)SECOND;
    }
}
