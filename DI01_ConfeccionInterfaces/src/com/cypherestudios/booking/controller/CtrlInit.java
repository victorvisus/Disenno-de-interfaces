/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cypherestudios.booking.controller;

import com.cypherestudios.booking.view.AppInit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Victor
 */
public class CtrlInit implements ActionListener {

    protected final AppInit appInit = new AppInit(); //vista

    /**
     * Constructor en el que se inician las "escuchas" a los botones del panel
     */
    public CtrlInit() {

        /* Listener para botones */
        this.appInit.btnBook.addActionListener(this);
        this.appInit.btnBookList.addActionListener(this);
        this.appInit.btnExit.addActionListener(this);
    }

    /**
     * Arranca el panel de inicio
     */
    public void appRun() {
        appInit.setTitle("CypherBookingSystem");
        appInit.setVisible(true);
        appInit.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        /* Acciones para botones y opciones de menú */
        if (e.getSource() == appInit.btnExit) {
            //Cierra la aplicación
            System.exit(0);
        }
        // Inicia el sistema de booking
        if (e.getSource() == appInit.btnBook) {
            //Crea una instancia del controller del JDialog de reservas: CtrlBooking
            CtrlBooking openBooking = new CtrlBooking();
            //Lanza el JDialog - le envia el ArrayList
            openBooking.runBooking();

        }

        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
