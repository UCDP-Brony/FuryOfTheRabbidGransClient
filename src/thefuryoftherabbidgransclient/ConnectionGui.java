/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thefuryoftherabbidgransclient;

import java.awt.Component;
import java.awt.TextField;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author TheDoctor
 */
public class ConnectionGui extends JFrame implements Runnable{
    private final JPanel panel;
    private final TextField entry;    
    private final JButton okButton;
    private final JLabel label;
    private final ClientConnection cc;
    
    public ConnectionGui(ClientConnection cc){
        this.cc = cc;
        this.panel = new JPanel();
        this.label = new JLabel();
        this.entry = new TextField();
        this.okButton = new JButton("ok");     
        this.initComponents();
    }
    
    private void initComponents(){
        this.okButton.addActionListener(new ConnectionOkBtnListener(this));
        this.panel.add(this.label);
        this.panel.add(this.entry);
        this.panel.add(this.okButton);
        this.add(this.panel);
    }

    public void sendMessage() {
        cc.sendMessage(entry.getText());            
    }

    public void setLabelText(String text) {
        label.setText(text);
        this.pack();
    }

    @Override
    public void run() {
        this.pack();
        this.setVisible(true);
    }
}
