
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.*;

public class ReservaSalonApp extends JFrame {

    private ArrayList<Reserva> reservas = new ArrayList<>();

    public ReservaSalonApp() {
        // Configuración de la ventana principal
        setTitle("Gestión de Reservas - Salón Habana");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Menú principal
        JMenuBar menuBar = new JMenuBar();
        JMenu menuReservas = new JMenu("Reservas");
        JMenuItem menuItemVerReservas = new JMenuItem("Ver Reservas");
        menuItemVerReservas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarReservas();
            }
        });
        menuReservas.add(menuItemVerReservas);
        menuBar.add(menuReservas);
        setJMenuBar(menuBar);

        // Botón para abrir el diálogo de reserva
        JButton btnNuevaReserva = new JButton("Nueva Reserva");
        btnNuevaReserva.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirDialogoReserva();
            }
        });

        // Colocar componentes en la ventana principal
        setLayout(new FlowLayout());
        add(btnNuevaReserva);
    }

    private void abrirDialogoReserva() {
        // Crear el diálogo de reserva
        JDialog dialogoReserva = new JDialog(this, "Reserva de Salón Habana", true);
        dialogoReserva.setSize(300, 400);
        dialogoReserva.setLayout(new GridLayout(7, 2));

        // Componentes del diálogo
        JLabel lblNombre = new JLabel("Nombre de contacto:");
        JTextField txtNombre = new JTextField();
        JLabel lblTelefono = new JLabel("Teléfono de contacto:");
        JTextField txtTelefono = new JTextField();

        JLabel lblFecha = new JLabel("Fecha del evento:");
        JSpinner spinnerFecha = new JSpinner(new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_MONTH));
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(spinnerFecha, "dd/MM/yyyy");
        spinnerFecha.setEditor(dateEditor);

        JLabel lblTipo = new JLabel("Tipo de evento:");
        String[] tiposEvento = {"Banquete", "Jornada", "Congreso"};
        JComboBox<String> cmbTipo = new JComboBox<>(tiposEvento);

        JLabel lblNumPersonas = new JLabel("Número de personas:");
        JSpinner spinnerNumPersonas = new JSpinner(new SpinnerNumberModel(1, 1, 1000, 1));

        JLabel lblTipoCocina = new JLabel("Tipo de cocina:");
        String[] tiposCocina = {"Buffet", "Carta", "Pedir cita con el chef", "No precisa"};
        JComboBox<String> cmbTipoCocina = new JComboBox<>(tiposCocina);

        // Componentes para congreso
        JLabel lblNumDias = new JLabel("Número de días (Congreso):");
        JSpinner spinnerNumDias = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
        spinnerNumDias.setEnabled(false);

        JLabel lblNumHabitaciones = new JLabel("Número de habitaciones (Congreso):");
        JSpinner spinnerNumHabitaciones = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
        spinnerNumHabitaciones.setEnabled(false);

        // Botón de reserva
        JButton btnReservar = new JButton("Reservar");
        btnReservar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Guardar la reserva
                Reserva reserva = new Reserva(
                        txtNombre.getText(),
                        txtTelefono.getText(),
                        (Date) spinnerFecha.getValue(),
                        (String) cmbTipo.getSelectedItem(),
                        (int) spinnerNumPersonas.getValue(),
                        (String) cmbTipoCocina.getSelectedItem(),
                        (int) spinnerNumDias.getValue(),
                        (int) spinnerNumHabitaciones.getValue()
                );
                reservas.add(reserva);

                // Cerrar el diálogo
                dialogoReserva.dispose();
            }
        });

        // Configurar la interfaz del diálogo
        dialogoReserva.add(lblNombre);
        dialogoReserva.add(txtNombre);
        dialogoReserva.add(lblTelefono);
        dialogoReserva.add(txtTelefono);
        dialogoReserva.add(lblFecha);
        dialogoReserva.add(spinnerFecha);
        dialogoReserva.add(lblTipo);
        dialogoReserva.add(cmbTipo);
        dialogoReserva.add(lblNumPersonas);
        dialogoReserva.add(spinnerNumPersonas);
        dialogoReserva.add(lblTipoCocina);
        dialogoReserva.add(cmbTipoCocina);
        dialogoReserva.add(lblNumDias);
        dialogoReserva.add(spinnerNumDias);
        dialogoReserva.add(lblNumHabitaciones);
        dialogoReserva.add(spinnerNumHabitaciones);
        dialogoReserva.add(btnReservar);

        // Hacer visible el diálogo
        dialogoReserva.setVisible(true);
    }

    private void mostrarReservas() {
        // Mostrar las reservas en una ventana emergente o en la consola
        StringBuilder mensaje = new StringBuilder("Reservas Realizadas:\n");
        for (Reserva reserva : reservas) {
            mensaje.append(reserva.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(this, mensaje.toString(), "Reservas", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ReservaSalonApp().setVisible(true);
            }
        });
    }
}

class Reserva {

    private String nombre;
    private String telefono;
    private Date fecha;
    private String tipoEvento;
    private int numPersonas;
    private String tipoCocina;
    private int numDiasCongreso;
    private int numHabitacionesCongreso;

    public Reserva(String nombre, String telefono, Date fecha, String tipoEvento, int numPersonas, String tipoCocina, int numDiasCongreso, int numHabitacionesCongreso) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.fecha = fecha;
        this.tipoEvento = tipoEvento;
        this.numPersonas = numPersonas;
        this.tipoCocina = tipoCocina;
        this.numDiasCongreso = numDiasCongreso;
        this.numHabitacionesCongreso = numHabitacionesCongreso;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre
                + ", Teléfono: " + telefono
                + ", Fecha: " + new SimpleDateFormat("dd/MM/yyyy").format(fecha)
                + ", Tipo Evento: " + tipoEvento
                + ", Número de Personas: " + numPersonas
                + ", Tipo Cocina: " + tipoCocina
                + ", Número de Días (Congreso): " + numDiasCongreso
                + ", Número de Habitaciones (Congreso): " + numHabitacionesCongreso;
    }
}
