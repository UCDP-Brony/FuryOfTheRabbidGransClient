/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thefuryoftherabbidgransclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TheDoctor
 */
class ClientReception implements Runnable {

    private boolean connected;
    private final ClientGame cGame;
    private BufferedReader in;

    public ClientReception(ClientGame cGame, BufferedReader in) {        
        this.in = in;
        this.connected = true;
        this.cGame = cGame;
    }

    @Override
    public void run() {
        while(connected){
            try{
                String message = in.readLine();
                if(message.equals("exit")){
                    throw new IOException();
                }
                cGame.getMessageFromServer(message);                
            } catch (IOException ex) {
                connected = false;
                System.out.println("Lost connection.");
            }
        }
    }
    
}
