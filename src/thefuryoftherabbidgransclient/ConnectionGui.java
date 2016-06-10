/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thefuryoftherabbidgransclient;

import java.awt.TextField;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author TheDoctor
 */
public class ConnectionGui extends JFrame implements Runnable{
    private JPanel panel;
    private TextField nameField;
    private TextField roomField;
    private JButton okButton;
    private ClientConnection cc;
    
    public ConnectionGui(ClientConnection cc){
        this.cc = cc;
        this.panel = new JPanel();
        this.nameField = new TextField();
        this.nameField.setEnabled(true);
        this.roomField = new TextField();      
        this.roomField.setEnabled(false);
        this.okButton = new JButton("ok");
        this.okButton.addActionListener(new ConnectionOkBtnListener(this));
        this.panel.add(this.nameField);
        this.panel.add(this.roomField);
        this.panel.add(this.okButton);
        this.add(this.panel);
    }

    void sendMessage() {
        if(nameField.isEnabled()){
            cc.sendMessage(nameField.getText());
            nameField.setEnabled(false);
            roomField.setEnabled(true);
        }
        else{
            cc.sendMessage(roomField.getText());
            roomField.setEnabled(false);
        }
            
    }

    void setNameText(String text) {
        nameField.setText(text);
        nameField.setEnabled(true);
        this.pack();
    }
    
    void setRoomText(String text) {
        roomField.setText(text);
        roomField.setEnabled(true);
        this.pack();
    }

    @Override
    public void run() {
        this.pack();
        this.setVisible(true);
    }
}
