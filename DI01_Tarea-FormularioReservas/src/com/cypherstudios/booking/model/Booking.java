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
    protected Date reservation;
    protected int attendees;
    protected String typeCuisine;

    /* Constructores */
    public Booking() {
    }

    public Booking(String customerName, Date reservation, int attendees, String typeCuisine) {
        this.customerName = customerName;
        this.reservation = reservation;
        this.attendees = attendees;
        this.typeCuisine = typeCuisine;
    }

    /* Getter and Setters */
    public String getCustomerName() {
        return this.customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Date getReservation() {
        return this.reservation;
    }

    /**
     * Formatea la fecha a un estilo más legible
     *
     * @return
     */
    public String getReservationString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        return sdf.format(this.reservation);
    }

    public void setReservation(Date reservation) {
        this.reservation = reservation;
    }

    public int getAttendees() {
        return this.attendees;
    }

    public void setAttendees(int attendees) {
        this.attendees = attendees;
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
                + "\nFecha: " + sdf.format(reservation)
                + "\nAsistentes: " + attendees
                + "\nTipo de cocina: " + typeCuisine;
    }

    /* Método compareTo por si quiero ordenar el las reservas del ArrayList */
    @Override
    public abstract int compareTo(Booking b);

}
