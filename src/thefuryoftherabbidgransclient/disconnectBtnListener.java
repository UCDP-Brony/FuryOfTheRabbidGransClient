/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thefuryoftherabbidgransclient;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author TheDoctor
 */
class disconnectBtnListener implements ActionListener {

    private final ConnectionGui gui;

    public disconnectBtnListener(ConnectionGui gui) {
        this.gui = gui;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.gui.disconnect();
    }    
}
