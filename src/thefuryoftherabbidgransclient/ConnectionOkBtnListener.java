/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thefuryoftherabbidgransclient;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TheDoctor
 */
class ConnectionOkBtnListener implements ActionListener {

    private final ConnectionGui gui;

    public ConnectionOkBtnListener(ConnectionGui gui) {
        this.gui = gui;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if(this.gui.shouldSendPassword()){
                this.gui.sendPassword();
            } else {
                this.gui.sendMessage();
            }
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ConnectionOkBtnListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
}
