package com.cypherstudios.booking.app;

import com.cypherstudios.booking.controller.CtrlInit;

/**
 *
 * @author Victor
 */
public class CypherBookingSystem {
    /**
     * Instancia el controlador que se encarga de las funciones del panel de
     * inicio de la aplicación
     */
    public static CtrlInit goBooking = new CtrlInit();

    public static void main(String[] args) {
        goBooking.appRun();
    }

}
