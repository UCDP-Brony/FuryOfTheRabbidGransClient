/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thefuryoftherabbidgransclient;

import java.awt.TextField;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

/**
 *
 * @author TheDoctor
 */
public class ConnectionGui extends JFrame implements Runnable{
    private final JPanel panel;
    private final TextField entry;    
    private final JPasswordField pEntry;
    private final JButton okButton;
    private final JLabel label;
    private final ClientConnection cc;
    
    /**
     *
     * @param cc
     */
    public ConnectionGui(ClientConnection cc){
        this.cc = cc;
        this.panel = new JPanel();
        this.label = new JLabel();
        this.entry = new TextField();
        this.pEntry = new JPasswordField();
        this.okButton = new JButton("ok");     
        this.initComponents();
    }
    
    private void initComponents(){
        this.okButton.addActionListener(new ConnectionOkBtnListener(this));
        this.entry.setColumns(15);
        this.pEntry.setColumns(15);
        this.panel.add(this.label);
        this.panel.add(this.entry);
        this.panel.add(this.pEntry);
        this.pEntry.setVisible(false);
        this.panel.add(this.okButton);
        this.add(this.panel);
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
     * @param hidden
     */
    public void setLabelText(String text, boolean hidden) {
        label.setText(text);
        if(hidden){
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

}
