/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thefuryoftherabbidgransclient;

import java.io.BufferedReader;
import java.io.PrintWriter;
import thefuryoftherabbidgransclient.game.TestGame;
import thefuryoftherabbidgransclient.gameEngine.core.CoreEngine;

/**
 *
 * @author TheDoctor
 */
class ClientGame implements Runnable {

    private final BufferedReader in;
    private final PrintWriter out;
    private Thread tReception;
    private ConnectionGui cg;
    private CoreEngine engine;
    
    ClientGame(BufferedReader in, PrintWriter out, ConnectionGui cg) {
        this.in = in;
        this.out = out;
        this.cg = cg;
    }


    @Override
    public void run() {        
        tReception = new Thread(new ClientReception(this, in));
        tReception.start();
        
        this.engine = new CoreEngine(800,600,60, new TestGame());
        engine.createWindow("The Fury Of The Rabbid Grans !");
        engine.start();
    }
    
    public void getMessageFromServer(String message) {
        cg.getInfo(message);  
        engine.gotInputFromServer(message);
    }

    public void sendMessageToServer(String message) {
        out.println(message);
        out.flush();
    }
}
