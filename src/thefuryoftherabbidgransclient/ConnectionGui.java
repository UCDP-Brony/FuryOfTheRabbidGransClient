/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thefuryoftherabbidgransclient;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.TextField;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.BevelBorder;
import thefuryoftherabbidgransclient.globals.SignalCodes;

/**
 *
 * @author TheDoctor
 */
public class ConnectionGui extends JFrame implements Runnable{
    private final JPanel infoPanel;
    private final JPanel inputPanel;
    private final JPanel errorPanel;
    private final TextField entry;    
    private final JPasswordField pEntry;
    private final JButton okButton;
    private final JLabel infoLabel;
    private final JLabel inputLabel;
    private final JLabel errorLabel;
    private final ClientConnection cc;
    private final JButton disconnectBtn;
    
    /**
     *
     * @param cc
     */
    public ConnectionGui(ClientConnection cc){
        this.cc = cc;
        
        this.infoPanel = new JPanel();
        this.infoPanel.setBackground(Color.CYAN);
        this.infoPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        this.infoLabel = new JLabel();
        
        this.errorPanel = new JPanel();
        this.errorPanel.setBackground(Color.PINK);
        this.errorPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));        
        this.errorLabel = new JLabel();
        
        this.inputPanel = new JPanel();
        this.inputPanel.setBackground(Color.LIGHT_GRAY);
        this.inputPanel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        this.inputLabel = new JLabel();
        
        this.entry = new TextField();
        this.pEntry = new JPasswordField();
        this.okButton = new JButton("ok");     
        this.disconnectBtn = new JButton(SignalCodes.getInstance().getTextFromSignalCode("discBtn"));
        this.initComponents();
    }
    
    private void initComponents(){
        this.okButton.addActionListener(new ConnectionOkBtnListener(this));
        this.disconnectBtn.addActionListener(new disconnectBtnListener(this));
        this.entry.setColumns(15);
        this.pEntry.setColumns(15);
        
        this.infoPanel.add(this.infoLabel);
        
        this.inputPanel.add(this.inputLabel);
        this.inputPanel.add(this.entry);
        this.inputPanel.add(this.pEntry);
        this.pEntry.setVisible(false);
        this.inputPanel.add(this.okButton);
        this.inputPanel.add(this.disconnectBtn);
        this.disconnectBtn.setEnabled(false);
        
        this.errorPanel.add(this.errorLabel);
        
        this.infoPanel.setSize(800, 400);
        this.inputPanel.setSize(800, 200);
        this.errorPanel.setSize(800, 400);        
        this.errorPanel.setVisible(false);
        
        this.add(this.infoPanel, BorderLayout.PAGE_START);
        this.add(this.errorPanel, BorderLayout.PAGE_START);
        this.add(this.inputPanel, BorderLayout.SOUTH);
        
                
        this.setMinimumSize(new Dimension(800,600));
        this.setResizable(false);
        this.pack();
    }

    public void sendMessage() throws NoSuchAlgorithmException {
        if(entry.isVisible()){
            cc.sendMessage(entry.getText());
        } else{
            String res = "";
            for(int i = 0; i < pEntry.getPassword().length;i++){
                res+=pEntry.getPassword()[i];
            }
            MessageDigest md = MessageDigest.getInstance("SHA1");
                md.update(res.getBytes()); 
                byte[] output = md.digest();                
            cc.sendMessage(bytesToHex(output));
        }
    }

    /**
     *
     * @param text
     * @param password
     */
    public void setLabelText(String text, boolean password) {
        inputLabel.setText(text);
        if(password){
            entry.setVisible(false);
            pEntry.setVisible(true);
        } else {
            pEntry.setVisible(false);
            entry.setVisible(true);
        }        
        this.pack();
    }

    @Override
    public void run() {
        this.pack();
        this.setVisible(true);
    }

    public void close() {
        this.setVisible(false);
        this.dispose();
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

    public void getInfo(String message) {
        this.errorPanel.setVisible(false);
        this.infoPanel.setVisible(true);
        this.infoLabel.setText(message);
    }
    
    public void getError(String message) {
        this.infoPanel.setVisible(false);
        this.errorPanel.setVisible(true);
        this.errorLabel.setText(message);
    }

    public void disconnect() {
        cc.sendMessage("exit");
        this.dispose();
        System.exit(0);        
    }
    
    public void setDiscBtnEnabled(boolean b){
        this.disconnectBtn.setEnabled(b);        
    }
}
