/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thefuryoftherabbidgransclient;

import java.io.BufferedReader;
import java.io.IOException;
import thefuryoftherabbidgransclient.globals.SignalCodes;

/**
 *
 * @author TheDoctor
 */
class ClientReception implements Runnable {

    private boolean connected;
    private final ClientGame cGame;
    private final BufferedReader in;

    public ClientReception(ClientGame cGame, BufferedReader in) {        
        this.in = in;
        this.connected = true;
        this.cGame = cGame;
    }

    @Override
    public void run() {
        SignalCodes sg = SignalCodes.getInstance();
        while(connected){
            try{
                String message = in.readLine();
                switch (message) {
                    case "C219":
                        throw new IOException();
                    case "C215_P1":
                    case "C218_P1":
                        System.out.print(sg.getTextFromSignalCode(message));
                        System.out.print(in.readLine());
                        System.out.print(sg.getTextFromSignalCode(in.readLine()));
                        System.out.print(in.readLine());
                        System.out.println(sg.getTextFromSignalCode(in.readLine()));
                        break;
                    case "C201":                
                        System.out.print(in.readLine());
                        break;
                    default:
                        cGame.getMessageFromServer(message);
                        break;
                }
            } catch (IOException ex) {
                connected = false;
                System.out.println(sg.getTextFromSignalCode("C410"));
            }
        }
    }
    
}
