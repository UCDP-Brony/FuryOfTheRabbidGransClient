/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thefuryoftherabbidgransclient;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author TheDoctor
 */
class ClientGame implements Runnable {

    private final BufferedReader in;
    private final PrintWriter out;
    private Thread tReception;

    ClientGame(BufferedReader in, PrintWriter out) {
       this.in = in;
        this.out = out;
    }


    @Override
    public void run() {        
        tReception = new Thread(new ClientReception(this, in));
        tReception.start();
        boolean connected = true;
        Scanner sc = new Scanner(System.in);
        while(connected){
            String message = sc.nextLine();
            if(message.equals("quit")||message.equals("exit")){
                connected = false;                
            }   
            sendMessageToServer(message);            
        }
    }
    
    public void getMessageFromServer(String message) {
        System.out.println("got message from server : "+message);
        
    }

    public void sendMessageToServer(String message) {
        out.println(message);
        out.flush();
    }
}
