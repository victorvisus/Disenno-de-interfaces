package com.cypherstudios.booking.dao;

import com.cypherstudios.booking.model.Booking;
import java.util.ArrayList;

/**
 *
 * @author Victor
 */
public class BookingsArrayList {

    //Inicializa un atributo con el ArrayList
    private ArrayList<Booking> publicBookingList;

    //Constructor de la Clase
    public BookingsArrayList() {
        publicBookingList = new ArrayList<>();
    }

    //Agrega un elemento al ArrayList
    public void attach(Booking re) {
        publicBookingList.add(re);
    }

    //Lee la posición de un elemento en el ArrayList
    public Booking getBooking(int pos) {
        return publicBookingList.get(pos);
    }

    //Devuelve el tamaño del ArrayList
    public int bookingCount() {
        return publicBookingList.size();
    }

}
