/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cypherestudios.booking.controller;

import com.cypherestudios.booking.view.BookDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Victor
 *
 * Extiende de CtrlInit e implementa ActionListener
 */
public class CtrlBooking extends CtrlInit implements ActionListener {

    //Instancia el JDialog BookDialog
    protected final BookDialog bookingDialogWindow = new BookDialog(appInit, true);

    public CtrlBooking() {

        this.bookingDialogWindow.btnReturn.addActionListener(this);

    }

    public void runBooking() {
        bookingDialogWindow.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        /* Acciones para botones y opciones de menú */

        if (e.getSource() == bookingDialogWindow.btnReturn) {
            //Cierra el JDialog y vuelve al JFrame principal
            this.bookingDialogWindow.setVisible(false);
        }

    }

}
