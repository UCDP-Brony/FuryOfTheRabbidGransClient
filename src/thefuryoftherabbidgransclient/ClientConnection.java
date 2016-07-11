/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thefuryoftherabbidgransclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import static java.lang.Thread.sleep;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import thefuryoftherabbidgransclient.globals.SignalCodes;

/**
 *
 * @author TheDoctor
 */
class ClientConnection implements Runnable {
    
    private final Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private Scanner sc;
    private String name, room;
    private boolean messageWaiting, connectedToRoom;
    private final ConnectionGui gui;
    private Thread tgame;

    public ClientConnection(Socket socket) {
        this.socket = socket;
        this.connectedToRoom = false;
        this.gui = new ConnectionGui(this);
        SwingUtilities.invokeLater(this.gui);
        this.messageWaiting = true;
    }

    @Override
    public void run() {
        SignalCodes sg = SignalCodes.getInstance();
        try{
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());
            sc = new Scanner(System.in);
            
            gui.setLabelText(sg.getTextFromSignalCode(in.readLine()));
            while(messageWaiting){
                sleep(10);
            }
            while(!connectedToRoom){
                messageWaiting = true;
                gui.setLabelText(sg.getTextFromSignalCode(in.readLine()));
                while(messageWaiting){
                    sleep(10);
                }
                String received = in.readLine();
                if(received.equals("C213_P1")){
                    System.out.print(sg.getTextFromSignalCode(received));
                    System.out.print(in.readLine());
                    System.out.print(sg.getTextFromSignalCode(in.readLine()));
                    System.out.print(in.readLine());
                    System.out.println(sg.getTextFromSignalCode(in.readLine()));
                } else {
                    System.out.println(sg.getTextFromSignalCode(received));
                }
                connectedToRoom = !received.equals("C413");
            }
            gui.close();
        } catch (IOException ex) {
            System.err.println(sg.getTextFromSignalCode("C500"));
        } catch (InterruptedException ex) {
            Logger.getLogger(ClientConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        tgame = new Thread(new ClientGame(in, out));
        tgame.start();
    }
    
    public void sendMessage(String message){
        out.println(message);
        out.flush();          
        this.messageWaiting = false;
    }            
}
