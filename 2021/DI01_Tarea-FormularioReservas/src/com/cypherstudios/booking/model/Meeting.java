package com.cypherstudios.booking.model;

import com.cypherstudios.booking.exceptions.BookingExceptions;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Victor
 */
public class Meeting extends Booking {

    private final String eventType = "Congreso";

    private int journeys;
    private char hosting; //valores 'Y' para si, 'N' para no
    private HostingRoom rooms;

    /* Constructores */
    public Meeting() {
        super();
    }

    /**
     * Constructor a falta de los valores para la reserva de habitaciones
     *
     * @param reservation
     * @param attendees
     * @param typeCuisine
     * @param journeys
     * @param hosting
     */
    public Meeting(String customerName, Date reservation, int attendees, String typeCuisine,
            int journeys, char hosting) {
        super(customerName, reservation, attendees, typeCuisine);

        this.journeys = journeys;
        this.hosting = hosting;

    }

    /**
     * Constructor de la clase
     *
     * Llama al constructor de la clase padre Booking, pasándole los atributos
     * necesarios, además de asignar los valores a los atributos propios de la
     * clase, evaluando si este tipo de clase precisa de alojamiento (hosting) o
     * no, en caso afirmativo (hosting == 'Y') instancia el objeto que almacena
     * los datos del alojamiento, enviándole a través de su constructor los
     * atributos para ello.
     *
     * @param reservation
     * @param attendees
     * @param typeCuisine
     * @param journeys
     * @param hosting
     * @param numDays
     * @param numRooms
     */
    public Meeting(String customerName, Date reservation, int attendees, String typeCuisine,
            int journeys, char hosting, int numDays, int numRooms) {
        super(customerName, reservation, attendees, typeCuisine);

        this.journeys = journeys;
        this.hosting = hosting;

        if (hosting == 'Y') {
            try {
                HostingRoom.evaluateRoomsData(numDays, numRooms); // Evalua que tenemos los datos

                HostingRoom rooms = new HostingRoom(numDays, numRooms);

                this.rooms = rooms;
            } catch (BookingExceptions ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(),
                        "Los datos recibidos no son correctos", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /* Getter and Setters */
    public String getEventType() {
        return eventType;
    }

    public int getJourneys() {
        return journeys;
    }

    public void setJourneys(int days) {
        this.journeys = days;
    }

    public char getHosting() {
        return hosting;
    }

    public void setHosting(char hosting) {
        this.hosting = hosting;
    }

    public HostingRoom getHostingRoom() {
        return rooms;
    }

    @Override
    public String toString() {
        String roomsData;

        if (hosting == 'Y') {
            roomsData = rooms.toString();
        } else {
            roomsData = " ,No son necesarias habitaciones de hotel.";
        }

        String meeting = "\nTipo de Evento: " + eventType
                + "\nNº de Jornadas: " + journeys
                + "\n¿Necesita hotel? " + hosting
                + roomsData;

        return meeting + "\n" + super.toString();
    }

    @Override
    public int compareTo(Booking b) {
        throw new UnsupportedOperationException("Método compareTo no implementado.");
    }

    /**
     * Crea el objeto HostingRooms, enviandole los datos necesarios, referentes
     * a la reserva de hotel
     *
     * @param numDays
     * @param numRooms
     */
    public void roomsValues(int numDays, int numRooms) {
        try {
            HostingRoom.evaluateRoomsData(numDays, numRooms); // Evalua que tenemos los datos

            HostingRoom rooms = new HostingRoom(numDays, numRooms);

            this.rooms = rooms;
        } catch (BookingExceptions ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(),
                    "Error al establecer la reserva de habitaciones", JOptionPane.ERROR_MESSAGE);
        }
    }
}
