/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thefuryoftherabbidgransclient;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author TheDoctor
 */
public class TheFuryOfTheRabbidGransClient {

    private static Thread t1;
    private static Socket socket;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {        
        try{
            System.out.println("Connecting to server...");
            socket = new Socket(InetAddress.getLocalHost(), 1500);
            System.out.println("connected !");
            t1 = new Thread(new ClientConnection(socket));
            t1.start(); 
        } catch (UnknownHostException ex){
            System.err.println("Impossible to connect to the address "+socket.getLocalAddress()+".");
        }
        catch (IOException ex) {
            System.err.println("No server listening on port "+socket.getLocalPort()+".");
        }
    }    
}
