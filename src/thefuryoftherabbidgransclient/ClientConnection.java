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
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
    private boolean messageWaiting, connectedToRoom, connectedToServer;
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
            String m = in.readLine();
            gui.getInfo(sg.getTextFromSignalCode("C211_2"));
            gui.setLabelText(sg.getTextFromSignalCode(m),m.equals("C211"));
                
            while(!connectedToServer){
                while(messageWaiting){
                    sleep(10);
                } 
                String received = in.readLine();
                switch (received) {
                    case "C211_1":
                        gui.getInfo(sg.getTextFromSignalCode(received));
                        gui.setDiscBtnEnabled(true);
                        connectedToServer = true;
                        break;
                    case "C411":
                    case "C411_1":
                        gui.getError(sg.getTextFromSignalCode(received));
                        break;
                    default:
                        gui.setLabelText(sg.getTextFromSignalCode(received), received.equals("C211"));
                        break;
                }
            }
            messageWaiting = true;
            gui.setLabelText(sg.getTextFromSignalCode(in.readLine()), false);                
            while(!connectedToRoom){
                while(messageWaiting){
                    sleep(10);
                }
                String received = in.readLine();
                switch (received) {
                    case "C213_P1":
                        String txt = sg.getTextFromSignalCode(received);
                        txt += in.readLine();
                        txt += sg.getTextFromSignalCode(in.readLine());
                        txt += in.readLine();
                        txt += sg.getTextFromSignalCode(in.readLine());
                        gui.getInfo(txt);
                        connectedToRoom = true;
                        break;
                    case "C219":
                        gui.dispose();
                        System.exit(0);
                    case "C212":
                        messageWaiting = true;
                        gui.getError(sg.getTextFromSignalCode("C413_1"));
                        gui.setLabelText(sg.getTextFromSignalCode(received), false);
                        break;
                    default:
                        gui.getError(sg.getTextFromSignalCode(received));
                        break;
                }
            }
        } catch (IOException | InterruptedException ex) {
                Logger.getLogger(ClientConnection.class.getName()).log(Level.SEVERE, null, ex);        
        }
        tgame = new Thread(new ClientGame(in, out, gui));
        tgame.start();
    }
    
    public void sendMessage(String message){
        out.println(message);
        out.flush();          
        this.messageWaiting = false;
    }            
    
    public void sendPassword(String uncryptedPass){
         
        try {
            MessageDigest md = MessageDigest.getInstance("SHA1");
            md.update(uncryptedPass.getBytes());
            byte[] output = md.digest();
            this.sendMessage(bytesToHex(output));
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ClientConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    private String bytesToHex(byte[] b) {
        char hexDigit[] = {'0', '1', '2', '3', '4', '5', '6', '7',
                         '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        StringBuilder buf = new StringBuilder();
        for (int j=0; j<b.length; j++) {
            buf.append(hexDigit[(b[j] >> 4) & 0x0f]);
            buf.append(hexDigit[b[j] & 0x0f]);
        }
        return buf.toString();
    }

}
