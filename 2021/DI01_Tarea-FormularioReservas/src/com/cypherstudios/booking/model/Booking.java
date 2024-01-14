package com.cypherstudios.booking.model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * Clase Booking
 *
 * Implementa la interface Comparable que permite ordenar un objeto
 *
 * @author Victor
 */
public abstract class Booking implements Comparable<Booking> {

    protected String customerName;
    protected Date eventDate;
    protected int guest;
    protected String typeCuisine;

    /* Constructores */
    public Booking() {
    }

    public Booking(String customerName, Date reservation, int attendees, String typeCuisine) {
        this.customerName = customerName;
        this.eventDate = reservation;
        this.guest = attendees;
        this.typeCuisine = typeCuisine;
    }

    /* Getter and Setters */
    public String getCustomerName() {
        return this.customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Date getEventDate() {
        return this.eventDate;
    }

    /**
     * Formatea la fecha a un estilo más legible
     *
     * @return
     */
    public String getEventDateString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        return sdf.format(this.eventDate);
    }

    public void setReservation(Date reservation) {
        this.eventDate = reservation;
    }

    public int getGuest() {
        return this.guest;
    }

    public void setGuest(int guest) {
        this.guest = guest;
    }

    public String getTypeCuisine() {
        return this.typeCuisine;
    }

    public void setTypeCuisine(String typeCuisine) {
        this.typeCuisine = typeCuisine;
    }

    /* Método toString */
    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        return "/**** Booking ****/"
                + "\nNombre: " + customerName
                + "\nFecha: " + sdf.format(eventDate)
                + "\nAsistentes: " + guest
                + "\nTipo de cocina: " + typeCuisine;
    }

    /* Método compareTo por si quiero ordenar el las reservas del ArrayList */
    @Override
    public abstract int compareTo(Booking b);

}
