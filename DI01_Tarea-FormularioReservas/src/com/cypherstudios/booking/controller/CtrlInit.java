package com.cypherstudios.booking.controller;

import com.cypherstudios.booking.dao.BookingDAO;
import com.cypherstudios.booking.dao.BookingsArrayList;
import com.cypherstudios.booking.view.AppInit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Victor
 */
public class CtrlInit implements ActionListener {

    //Inicializa un atributo con el ArrayList
    protected BookingsArrayList publicBookingList = new BookingsArrayList();

    //Instancia un Objeto de la clase que gestiona los datos
    protected final BookingDAO op = new BookingDAO();

    //Instancia la vista JFrame, del panel principal de la app
    protected final AppInit appInit = new AppInit();

    /**
     * Constructor en el que se inician las "escuchas" a los botones del panel
     */
    public CtrlInit() {

        /* Listener para opciones de menú */
        //this.AppInit.navItemBooking.addActionListener(this);
        //this.AppInit.navItemBookingList.addActionListener(this);
        //this.AppInit.navItemExit.addActionListener(this);

        /* Listener para botones */
        this.appInit.btnBooking.addActionListener(this);
        this.appInit.btnBookingList.addActionListener(this);
        this.appInit.btnExit.addActionListener(this);

    }

    /**
     * Arranca el panel de inicio
     */
    public void appRun() {

        appInit.setVisible(true);

        appInit.setTitle("CypherBookingSystem");
        appInit.setLocationRelativeTo(null);

    }

    /**
     * Sobreescribe el método actionPerformed, de la clase abstacta que
     * implementa, con el código que interesa para el JFrame de la app
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        /* Acciones para botones y opciones de menú */
        if (e.getSource() == appInit.btnExit) {
            //Cierra la aplicación
            System.exit(0);
        }
        // Inicia el sistema de booking
        if (e.getSource() == appInit.btnBooking) {
            //Crea una instancia del controller del JDialog de reservas: CtrlBooking
            CtrlBooking openBooking = new CtrlBooking();
            //Lanza el JDialog - le envia el ArrayList
            openBooking.runBooking(publicBookingList);
        }
        // Abre el panel donde se listan las reservas "guardadas"
        if (e.getSource() == appInit.btnBookingList) {
            //Crea una instancia del controller CtrflBookingList
            CtrlBookingList openList = new CtrlBookingList();
            //Inicia el JDialog BookingList - le envia el ArrayList
            openList.runListWindow(publicBookingList);
        }
    }

}
