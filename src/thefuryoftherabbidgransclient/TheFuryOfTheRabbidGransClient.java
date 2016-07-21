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
import thefuryoftherabbidgransclient.globals.SignalCodes;

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
        SignalCodes sg = SignalCodes.getInstance();
        try{
            //System.out.println("Connecting to server...");
            socket = new Socket("thefuryoftherabbidgrans.tk", 1500);
            //
            //socket = new Socket("192.168.1.28", 1500);
            //socket = new Socket(InetAddress.getLocalHost(), 1500);
            System.out.println(sg.getTextFromSignalCode("C200"));
            t1 = new Thread(new ClientConnection(socket));
            t1.start(); 
        } catch (UnknownHostException ex){
            System.err.println(sg.getTextFromSignalCode("C400"));
        }
        catch (IOException ex) {
            System.err.println(sg.getTextFromSignalCode("C401"));
        }
    }
}
