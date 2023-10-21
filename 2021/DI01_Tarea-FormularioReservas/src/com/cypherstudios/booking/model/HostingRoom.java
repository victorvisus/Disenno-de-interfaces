package com.cypherstudios.booking.model;

import com.cypherstudios.booking.exceptions.BookingExceptions;
import javax.swing.JOptionPane;

/**
 *
 * @author Victor
 */
public class HostingRoom {

    private int numDays;
    private int numRooms;

    /* Constructores */
    public HostingRoom() {
    }

    public HostingRoom(int numDays, int numRooms) {

        try {
            evaluateRoomsData(numDays, numRooms);

            this.numDays = numDays;
            this.numRooms = numRooms;
        } catch (BookingExceptions ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(),
                    "Los datos recibidos no son correctos", JOptionPane.ERROR_MESSAGE);
        }
    }

    /* Getter and Setters */
    public int getNumDays() {
        return numDays;
    }

    public void setNumDays(int numDays) {
        this.numDays = numDays;
    }

    public int getNumRooms() {
        return numRooms;
    }

    public void setNumRooms(int numRooms) {
        this.numRooms = numRooms;
    }

    /* Método toString */
    @Override
    public String toString() {
        return "\nDatos de reserva de Habitaciones"
                + "\nNº de días: " + numDays
                + "\nNº de habitaciones: " + numRooms;
    }

    /**
     * Evalua que los datos referentes a la reserva de habitaciones son
     * correctos, distintos de 0
     *
     * @param numDays
     * @param numRooms
     * @throws BookingExceptions
     */
    public static void evaluateRoomsData(int numDays, int numRooms) throws BookingExceptions {
        if (numDays == 0 || numRooms == 0) {
            throw new BookingExceptions(1);
        }
    }
}
