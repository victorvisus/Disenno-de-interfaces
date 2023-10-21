package com.cypherstudios.booking.model;

import java.util.Date;

/**
 *
 * @author Victor
 */
public class Banquet extends Booking {

    private final String eventType = "Banquete";

    public Banquet() {
        super();
    }

    public Banquet(String customerName, Date reservation, int attendees, String typeCuisine) {
        super(customerName, reservation, attendees, typeCuisine);
    }

    public String getEventType() {
        return eventType;
    }

    @Override
    public String toString() {
        return "\nTipo de Evento: " + eventType + "\n" + super.toString();
    }

    @Override
    public int compareTo(Booking b) {
        throw new UnsupportedOperationException("Método compareTo no implementado.");
    }
}
