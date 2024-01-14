package com.cypherstudios.booking.dao;

import com.cypherstudios.booking.exceptions.BookingExceptions;
import com.cypherstudios.booking.model.*;
import com.cypherstudios.booking.view.BookingDialog;
import java.util.Date;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Victor
 */
public class BookingDAO {

    /**
     * Constructor Vacio
     */
    public BookingDAO() {
    }

    /*
     * ********************************************** GUARDAR RESERVAS
     * *************************************************************************
     */
    /**
     * Guarda en el ArrayList la reserva, que manda a gestionar al método
     * actionBtnSaveBooking
     *
     * @param bookingWindow : recibe la ventana JDialog que contiene los datos
     * necesarios para la reserva
     * @param publicBookingList : recibe el ArrayList con las reservas que se
     * han realizado
     * @return : devuelve el arraylist modificado
     */
    public BookingsArrayList saveBooking(BookingDialog bookingWindow, BookingsArrayList publicBookingList) {
        try {
            publicBookingList.attach(actionBtnSaveBooking(bookingWindow));

            //Cierra el modal si la reserva se ha realizado correctamente
            bookingWindow.setVisible(false);

        } catch (BookingExceptions ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(),
                    "Reserva de evento", JOptionPane.ERROR_MESSAGE);
        }
        return publicBookingList;
    }

    /**
     * Recoge los datos del formulario para crear la reserva
     *
     * Este método lo podría dividir en 3:
     * <ul>
     * <li>El primero evalua el tipo de objeto necesario para el evento.
     * Dependiendo del resultado llama a un método u a otro</li>
     * <li>Otro método si la reserva es un "Congreso"</li>
     * <li>Otro método si la reserva es un "Banquete" o una "Jornada"</li>
     * </ul>
     *
     * @param bookingWindow
     * @param reservation
     * @return
     * @throws BookingExceptions
     */
    private Booking actionBtnSaveBooking(BookingDialog bookingWindow) throws BookingExceptions {

        Booking reservation = null;

        //Manda a evaluar que los combo box (Tipo de evento y Tipo de cocina) haya una opción correcta seleccionada
//        dataEvaluate(bookingWindow.cbEventType, (String) bookingWindow.cbEventType.getSelectedItem());
        dataEvaluate(bookingWindow.cbTypeCuisine, (String) bookingWindow.cbTypeCuisine.getSelectedItem());

        /*
        Hacer un switch para este código, usando el método dataEvaluateOption para seleccionar la opción.
         */
        if (dataEvaluate(bookingWindow.cbEventType, "Congreso")) {
            reservation = new Meeting();

            //Pone los valores a los atributos heredados estandar del objeto Booking
            buildStandardBooking(bookingWindow, reservation);

            //Casteo el objeto para establecer los valores caracteristicos del propio objeto
            if (reservation instanceof Meeting) {
                ((Meeting) reservation).setJourneys((int) bookingWindow.spJourneys.getValue());

                if (bookingWindow.rbtnHostingYes.isSelected() && !bookingWindow.rbtnHostingNo.isSelected()) {
                    //Estable el valor de 'Y' al atributo
                    ((Meeting) reservation).setHosting('Y');
                    //Recojo los valores para los datos de HostingRoom
                    int numDays = (int) bookingWindow.numDays.getValue();
                    int numRooms = (int) bookingWindow.numRooms.getValue();

                    //Le mando los valores al método de objeto Meeting, que construye el "atributo/objeto" HostingRoom
                    ((Meeting) reservation).roomsValues(numDays, numRooms);

                    JOptionPane.showMessageDialog(null, "Reserva registrado correctamente",
                            "Reserva de evento", JOptionPane.INFORMATION_MESSAGE);

                } else if (bookingWindow.rbtnHostingNo.isSelected() && !bookingWindow.rbtnHostingYes.isSelected()) {
                    ((Meeting) reservation).setHosting('N');

                    JOptionPane.showMessageDialog(null, "Reserva registrado correctamente",
                            "Reserva de evento", JOptionPane.INFORMATION_MESSAGE);

                } else {
                    throw new BookingExceptions(4);
                }
            }

//            JOptionPane.showMessageDialog(null, "Reserva registrado correctamente",
//                    "Reserva de evento", JOptionPane.INFORMATION_MESSAGE);
            //Establece valores por defecto en el formulario
            cleanForm(bookingWindow);

        } else if (dataEvaluate(bookingWindow.cbEventType, "Banquete")) {
            reservation = new Banquet();

            //Pone los valores a los atributos heredados del objeto Booking
            buildStandardBooking(bookingWindow, reservation);

            JOptionPane.showMessageDialog(null, "Reserva registrado correctamente",
                    "Reserva de evento", JOptionPane.INFORMATION_MESSAGE);

            //Establece valores por defecto en el formulario
            cleanForm(bookingWindow);

        } else if (dataEvaluate(bookingWindow.cbEventType, "Jornada")) {
            reservation = new Workshop();

            //Pone los valores a los atributos heredados del objeto Booking
            buildStandardBooking(bookingWindow, reservation);

            JOptionPane.showMessageDialog(null, "Reserva registrado correctamente",
                    "Reserva de evento", JOptionPane.INFORMATION_MESSAGE);

            //Establece valores por defecto en el formulario
            cleanForm(bookingWindow);

        } else {
            throw new BookingExceptions(2);
        }

        return reservation;
    }

    /**
     * Construye el objeto Booking "estandar", el que corresponda a Workshop y a
     * Banquet, para los otros tipos de Booking los completa dentro de su if
     *
     * @param bookingWindow
     * @param reservation
     * @return reservation
     */
    private Booking buildStandardBooking(BookingDialog bookingWindow, Booking reservation) {
        reservation.setCustomerName(bookingWindow.txtCustomerName.getText());
        reservation.setReservation((Date) bookingWindow.dateReservation.getValue());
        reservation.setGuest((int) bookingWindow.spAttendees.getValue());
        reservation.setTypeCuisine((String) bookingWindow.cbTypeCuisine.getSelectedItem());

        return reservation;
    }

    /*
     * ********************************************** LISTAR RESERVAS
     * *************************************************************************
     */
    public void tableBookingList(JTable jtBookingList, BookingsArrayList publicBookingList) {

        DefaultTableModel bookingTable = new DefaultTableModel();
        jtBookingList.setModel(bookingTable);

        try {
            existBooking(publicBookingList); //Evalua que el arralist no este vacio

            bookingTable.addColumn("Tipo");//0
            bookingTable.addColumn("Nombre");//1
            bookingTable.addColumn("Fecha");//2
            bookingTable.addColumn("Asistentes");//3
            bookingTable.addColumn("Tipo Cocina");//4
            bookingTable.addColumn("Nº Jornadas");//5
            // OJO, tiene que acceder al objeto HostingRoom rooms
            bookingTable.addColumn("Nº Habitaciones");//6
            bookingTable.addColumn("Nº de Noches");//7

            //Coje el num. de columnas que tiene el jtable
            int numColumns = bookingTable.getColumnCount();
            //Asigna un ancho a las columnas
            for (int y = 0; y < numColumns; y++) {
                jtBookingList.getColumnModel().getColumn(y).setPreferredWidth(20);
            }

            Object[] fila = new Object[numColumns];

            //Crea tantas filas como objetos hay en el ArrayList
            for (int x = 0; x < publicBookingList.bookingCount(); x++) {

                //Agregamos al modelo una fila
                bookingTable.addRow(fila);

                /* Instancia un objeto Booking del que va a extraer los datos
                para mostrarlos en las columnas de la fila, en la tabla */
                Booking getBooking = (Booking) publicBookingList.getBooking(x);

                //Manda a rellenar las columnas de la fila
                fillColumns(bookingTable, x, getBooking);

            }
        } catch (BookingExceptions ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(),
                    "Listar Reservas de evento", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * ********************************************** MÉTODOS AUXILIARIES
     * *************************************************************************
     */
    /**
     * Limpia los campos del formulario una vez realizada la reserva
     *
     * @param bookingWindow
     */
    private void cleanForm(BookingDialog bookingWindow) {
        bookingWindow.txtCustomerName.setText(null);
        bookingWindow.cbEventType.setSelectedIndex(0);
        bookingWindow.cbTypeCuisine.setSelectedIndex(0);
        bookingWindow.rbtnHostingNo.setSelected(false);
        bookingWindow.rbtnHostingYes.setSelected(false);
    }

    /**
     * Extrae los atributos del objeto para mostrarlos en la tabla
     *
     * @param bookingTable : El modelo de la tabla
     * @param x : int la posición del bucle for en la que se encuentra
     * @param getBooking : el objeto Booking reserva
     * @throws BookingExceptions : si el objeto no pertenece a ninguno de los
     * tres tipos existentes lanza un error
     */
    private void fillColumns(DefaultTableModel bookingTable, int x, Booking getBooking) throws BookingExceptions {
        //Extrae los datos "básico" de la reserva
        bookingTable.setValueAt(getBooking.getCustomerName(), x, 1);
        bookingTable.setValueAt(getBooking.getEventDateString(), x, 2);
        bookingTable.setValueAt(getBooking.getGuest(), x, 3);
        bookingTable.setValueAt(getBooking.getTypeCuisine(), x, 4);

        // Evalua de que tipo de reserva es el objeto Booking
        switch (valueBookingType(getBooking)) {
            case 1:
                bookingTable.setValueAt(((Workshop) getBooking).getEventType(), x, 0);

                bookingTable.setValueAt(1, x, 5);
                bookingTable.setValueAt(0, x, 6);
                bookingTable.setValueAt(0, x, 7);
                break;
            case 2:
                bookingTable.setValueAt(((Banquet) getBooking).getEventType(), x, 0);

                bookingTable.setValueAt(1, x, 5);
                bookingTable.setValueAt(0, x, 6);
                bookingTable.setValueAt(0, x, 7);
                break;
            case 3:
                bookingTable.setValueAt(((Meeting) getBooking).getEventType(), x, 0);

                //Evalua si se necesitan habitaciones de hotel.
                if (((Meeting) getBooking).getHosting() == 'Y') {
                    bookingTable.setValueAt(((Meeting) getBooking).getJourneys(), x, 5);
                    bookingTable.setValueAt(((Meeting) getBooking).getHostingRoom().getNumRooms(), x, 6);
                    bookingTable.setValueAt(((Meeting) getBooking).getHostingRoom().getNumDays(), x, 7);
                } else {
                    bookingTable.setValueAt(((Meeting) getBooking).getJourneys(), x, 5);
                    bookingTable.setValueAt(0, x, 6);
                    bookingTable.setValueAt(0, x, 7);
                }
                break;
            default:
                //throw new BookingExceptions(7);

                // Si se produce un error, rellena los campos de la fila con el string "error"
                for (int z = 0; z < bookingTable.getColumnCount(); z++) {
                    bookingTable.setValueAt("error", x, z);
                }
                break;
        }
    }

    /* *************************************************** COMPROBACIONES **** */
    /**
     * Evalua si el item seleccionado en un JComboBox coincide con el enviado
     * por parámetro
     *
     * @param cb : el componente JComboBox
     * @param evType : String que contiene la cadena válida
     * @return : true o false dependiendo del resultado
     */
    private boolean dataEvaluate(JComboBox cb, String evType) throws BookingExceptions {
        String tipo = (String) cb.getSelectedItem();

        if ("Elije una opción".equals(tipo)) {
            throw new BookingExceptions(3);
            //} else if (tipo == evType) {
        } else if (evType.equals(tipo)) {
            return true;
        }
        return false;
    }

    /**
     * Comprueba que la reserva no sea nula
     *
     * @return
     * @throws BookingExceptions
     */
    private boolean validReservation(Booking reservation) throws BookingExceptions {
        if (reservation == null) {
            throw new BookingExceptions(6);
        } else if (reservation != null) {
            return true;
        }
        return false;
    }

    /**
     * Comprueba que el ArrayList de reservas no este vacio
     *
     * @return
     * @throws BookingExceptions
     */
    private boolean existBooking(BookingsArrayList publicBookingList) throws BookingExceptions {
        if (publicBookingList.bookingCount() == 0) {
            throw new BookingExceptions(5);
        } else if (publicBookingList.bookingCount() != 0) {
            return true;
        }
        return false;
    }

    /**
     * Evalua la selección del JComboBox
     *
     * @param cb : el componente JComboBox
     * @param evType : String que contiene la cadena válida
     */
    private int dataEvaluateOption(JComboBox cb) throws BookingExceptions {
        String tipo = (String) cb.getSelectedItem();
//        int op;

        if (tipo.equals("Congreso")) {
            return 1;
//            op = 1;
        } else if (tipo.equals("Banquete")) {
            return 2;
//            op = 2;
        } else if (tipo.equals("Jornada")) {
            return 3;
//            op = 3;
        } else {
            throw new BookingExceptions(6);
        }
        //return op;
    }

    /**
     * Evalua el tipo de objeto del evento, dependiendo de si es Banquete,
     * Jornada o Congreso devuelve un valor u otro, para el switch
     *
     * @param getBooking : Objeto reserva Booking
     * @return valor case
     */
    private int valueBookingType(Booking getBooking) throws BookingExceptions {
        if (getBooking instanceof Workshop) {
            return 1;
        } else if (getBooking instanceof Banquet) {
            return 2;
        } else if (getBooking instanceof Meeting) {
            return 3;
        } else {
            throw new BookingExceptions(7);
            //return 0;
        }
    }
}
