/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.cypherestudios.booking.app;

import com.cypherestudios.booking.controller.CtrlInit;

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
