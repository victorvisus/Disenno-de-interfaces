/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cypherestudios.booking.model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Victor * Atributos * - customerName: String - telName: String -
 * dateEvent: Date - attendees: int - typeCuisine: String * metodos * +
 * Booking() + Booking(String customerName,telName: String, Date reservation,
 * int attendees, String typeCuisine)
 *
 */
public class Booking {

    private String customerName;
    private String customerTel;
    private Date eventDate;
    private int guests;
    private String cuisine;

    public Booking() {
    }

    public Booking(String customerName, String customerTel, Date eventDate, int guests, String cuisine) {
        this.customerName = customerName;
        this.customerTel = customerTel;
        this.eventDate = eventDate;
        this.guests = guests;
        this.cuisine = cuisine;
    }

    public String getCustomerName() {
        return this.customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerTel() {
        return this.customerTel;
    }

    public void setCustomerTel(String customerTel) {
        this.customerTel = customerTel;
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

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public int getGuests() {
        return this.guests;
    }

    public void setGuests(int guests) {
        this.guests = guests;
    }

    public String getCuisine() {
        return this.cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        return "/**** Booking ****/"
                + "\nNombre: " + customerName
                + "\nFecha: " + sdf.format(eventDate)
                + "\nAsistentes: " + guests
                + "\nTipo de cocina: " + cuisine;
    }

}
