package com.cypherstudios.booking.model;

import java.util.Date;

/**
 *
 * @author Victor
 */
public class Workshop extends Booking {

    private final String eventType = "Jornada";

    public Workshop() {
        super();
    }

    public Workshop(String customerName, Date reservation, int attendees, String typeCuisine) {
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
