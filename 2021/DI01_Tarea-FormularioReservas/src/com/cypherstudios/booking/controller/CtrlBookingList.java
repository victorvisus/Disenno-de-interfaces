/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cypherstudios.booking.controller;

import com.cypherstudios.booking.dao.BookingsArrayList;
import com.cypherstudios.booking.model.*;
import com.cypherstudios.booking.view.BookingList;
import java.awt.event.ActionEvent;
import java.util.Date;

/**
 *
 * @author Victor
 */
public class CtrlBookingList extends CtrlInit {

    /**
     * Instancia el objeto JDialog - BookingList. Le envia el formulario "padre"
     */
    private final BookingList bookingListWindows = new BookingList(appInit, true);

    //private BookingsArrayList publicBookingList;

    public CtrlBookingList() {
        /* Listener para opciones de menú */
        this.bookingListWindows.navItemReturn.addActionListener(this);
        this.bookingListWindows.navItemExit.addActionListener(this);

        /* Listener para botones */
        this.bookingListWindows.btnReturn.addActionListener(this);
        this.bookingListWindows.btnBookingList.addActionListener(this);
    }

    /**
     * Lanza la ventana de dialogo
     */
    public void runListWindow(BookingsArrayList publicBookingList) {
        this.publicBookingList = publicBookingList;

        bookingListWindows.setVisible(true);
    }

    /**
     * Sobreescribe el método actionPerformed con el código que interesa para el
     * JDialog donde se piden datos para realizar la reserva, de la clase padre
     * CtrlInit de la que hereda, que a su vez sobreescribe el método de la
     * clase abstracta que implementa
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        /* Acciones para botones y opciones de menú */
        if (e.getSource() == bookingListWindows.navItemExit) {
            //Cierra la aplicación
            System.exit(0);
        }
        if (e.getSource() == bookingListWindows.btnReturn || e.getSource() == bookingListWindows.navItemReturn) {
            //Cierra el JDialog y vuelve a al JFrame principal
            this.bookingListWindows.setVisible(false);
        }
        /* Botón listar las Reservas */
        if (e.getSource() == bookingListWindows.btnBookingList) {

            addExamples(); //Creo objetos de ejemplo
            op.tableBookingList(bookingListWindows.jtBookingList, publicBookingList);
        }
    }

    /**
     * Añade datos de ejemplo al ArrayList para hacer las pruebas
     */
    private void addExamples() {
        System.out.println("Añadido Victor - Workshop");
        publicBookingList.attach(new Workshop("Victor", new Date(), 5, "Bufé"));
        System.out.println("Añadido Jeny - Banquet");
        publicBookingList.attach(new Banquet("Jeny", new Date(), 7, "Carta"));
        System.out.println("Añadido Angel - Workshop");
        publicBookingList.attach(new Workshop("Angel", new Date(), 15, "No precisa"));
        System.out.println("Añadido Luis - Meeting");
        publicBookingList.attach(new Meeting("Luis", new Date(), 20, "No precisa", 5, 'Y', 3, 2));

    }
}
