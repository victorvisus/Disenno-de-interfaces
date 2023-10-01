package com.cypherstudios.booking.controller;

import com.cypherstudios.booking.dao.BookingsArrayList;
import com.cypherstudios.booking.view.BookingDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase controlador para el JDialog que se encarga de solicitar los datos
 * necesarios para realizar la reserva.
 *
 * @author Victor Visús García
 */
public class CtrlBooking extends CtrlInit implements ActionListener {

    /**
     * Instancia el objeto JDialog - BookingDialog. Le envia el formulario
     * "padre"
     */
    private final BookingDialog bookingDialogWindow = new BookingDialog(appInit, true);

    //private BookingsArrayList publicBookingList;

    public CtrlBooking() {
        /* Listener para opciones de menú */
        this.bookingDialogWindow.navItemSaveBooking.addActionListener(this);
        this.bookingDialogWindow.navItemBookingList.addActionListener(this);
        this.bookingDialogWindow.navItemReturn.addActionListener(this);
        this.bookingDialogWindow.navItemExit.addActionListener(this);

        /* Listener para botones */
        this.bookingDialogWindow.btnSaveBooking.addActionListener(this);
        this.bookingDialogWindow.btnReturn.addActionListener(this);
    }

    /**
     * Lanza la ventana de dialogo
     *
     * Instancia un objeto tipo BookingsArrayList asignandole el ArrayList
     * recibido a su atributo de tipo ArrayList.
     *
     * Hace visible el JDialog
     *
     * @param publicBookingList : recibe el ArrayList de reservas
     * @return Array : Devuelve el ArrayList modificado
     */
    public BookingsArrayList runBooking(BookingsArrayList publicBookingList) {

        this.publicBookingList = publicBookingList;

        bookingDialogWindow.setVisible(true);

        return publicBookingList;
    }

    /**
     * Sobreescribe el método actionPerformed con el código que interesa para el
     * JDialog donde se piden datos para realizar la reserva, de la clase padre
     * CtrlInit de la que hereda, que a su vez sobreescribe el método de la
     * clase abstracta ActionListener que implementa
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        /* Acciones para botones y opciones de menú */
        if (e.getSource() == bookingDialogWindow.navItemExit) {
            //Cierra la aplicación
            System.exit(0);
        }
        if (e.getSource() == bookingDialogWindow.btnReturn || e.getSource() == bookingDialogWindow.navItemReturn) {
            //Cierra el JDialog y vuelve al JFrame principal
            this.bookingDialogWindow.setVisible(false);
        }
        if (e.getSource() == bookingDialogWindow.btnSaveBooking || e.getSource() == bookingDialogWindow.navItemSaveBooking) {
            //Llama al método que se encarga de realizar la operación
            op.saveBooking(bookingDialogWindow, publicBookingList);
            //Cierra el modal
            //this.bookingDialogWindow.setVisible(false);
        }
        if (e.getSource() == bookingDialogWindow.navItemBookingList) {
            //Crea una instancia del controller CtrflBookingList - Sin cerrar la ventana de Reservas
            CtrlBookingList openList = new CtrlBookingList();
            //Iniciar el JDialog BookingList
            openList.runListWindow(publicBookingList);

        }
    }
}
