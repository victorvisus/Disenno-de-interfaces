package com.cypherestudios.booking.exceptions;

/**
 *
 * @author Victor
 */
public class BookingExceptions extends Exception {

    private int errorCode;

    public BookingExceptions(int errorCode) {
        super();
        this.errorCode = errorCode;
    }

    /**
     *
     * @return errorMsg : Mensaje de error, pudiendo ser por los siguientes
     * motivos:
     * <ul>
     * <li>case 1: Congreso - No se han introducido datos referentes al Hotel
     * pero se ha indicado que es necesario.</li>
     * <li>case 2: Se ha producido un error al guardar la reserva.</li>
     * <li>case 3: No se ha seleccionado el tipo de evento.</li>
     * <li>case 4: No se ha introducido información sobre la reserva de
     * habitaciones.</li>
     * <li>case 5: El ArrayList de reservas esta vacio.</li>
     * <li>case 6: El objeto Booking reserva es null, esta vacio.</li>
     * <li>case 7: El objeto Booking reserva, extraido del ArrayList de
     * reservas, no coincide con ningún tipo de reserva establecido.</li>
     * </ul>
     */
    public String getMessage() {
        String errorMsg = "";
        System.out.println(errorCode);

        switch (errorCode) {

            case 1:
                //Registro de nueva reserva:
                errorMsg = "Los datos para realizar la reserva son insuficientes";
                break;
            case 2:
                //Registro de nueva reserva:
                errorMsg = "Error al realizar la reserva";
                break;
            case 3:
                //No se ha seleccionado una opción en el JComboBox
                errorMsg = "Por favor, tienes que elejir un tipo de evento y de cocina";
                break;
            case 4:
                //No se ha seleccionado una opción en el JComboBox
                errorMsg = "¿Necesitas reservar habitaciones en el hotel?";
                break;
            case 5:
                //No existen reservas
                errorMsg = "No existen reservas";
                break;
            case 6:
                //El objeto reserva es nulo
                errorMsg = "La reservas no es correcta";
                break;
            case 7:
                //El objeto reserva no coincide con ningún tipo de reserva establecido
                errorMsg = "Error al listar la reserva";
                break;
            case 8:
                //El objeto reserva no coincide con ningún tipo de reserva establecido
                errorMsg = "Error al establecer la reserva de habitaciones";
                break;
            case 9:
                //El objeto reserva no coincide con ningún tipo de reserva establecido
                errorMsg = "Abre Book Dialog";
                break;
            case 10:
                //El objeto reserva no coincide con ningún tipo de reserva establecido
                errorMsg = "Abre Book List";
                break;
            default:
                //Cualquier otro error no establecido en este switch
                errorMsg = "Error de ejecución";
                break;
        }
        return errorMsg;
    }

}
