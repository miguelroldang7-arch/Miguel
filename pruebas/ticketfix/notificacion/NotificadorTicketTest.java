package ticketfix.notificacion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.Test;

// Pruebas del modulo de notificaciones de TicketFix (patron Strategy).
// Un caso de prueba (CP) por cada caso de uso del sistema que usa el modulo.
public class NotificadorTicketTest {

    // Captura lo que se imprime en consola mientras se ejecuta una accion.
    private static String capturarConsola(Runnable accion) {
        PrintStream original = System.out;
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        System.setOut(new PrintStream(buffer));
        try {
            accion.run();
        } finally {
            System.setOut(original);
        }
        return buffer.toString().replace("\r", "");
    }

    // CP-01 / Registrar solicitud de soporte (unitaria, escenario correcto)
    @Test
    public void cp01_registrarSolicitud_mensajeValido() {
        EstrategiaEspia espia = new EstrategiaEspia();
        NotificadorTicket notificador = new NotificadorTicket(espia);

        notificador.notificar("Su ticket ha sido creado exitosamente.");

        assertEquals(1, espia.recibidos.size());
        assertEquals("Su ticket ha sido creado exitosamente.", espia.recibidos.get(0));
    }

    // CP-02 / Consultar estado de ticket (unitaria, datos invalidos)
    @Test
    public void cp02_consultarEstado_mensajeInvalido() {
        EstrategiaEspia espia = new EstrategiaEspia();
        NotificadorTicket notificador = new NotificadorTicket(espia);

        assertThrows(IllegalArgumentException.class, () -> notificador.notificar(null));
        assertThrows(IllegalArgumentException.class, () -> notificador.notificar(""));
        assertThrows(IllegalArgumentException.class, () -> notificador.notificar("   "));
        assertEquals("Ninguna notificacion invalida debe llegar al canal", 0, espia.recibidos.size());
    }

    // CP-03 / Recibir notificacion de cambio de estado (integracion: contexto + estrategias reales)
    @Test
    public void cp03_recibirNotificacion_cambioDeCanal() {
        NotificadorTicket notificador = new NotificadorTicket(new NotificacionEmail());

        String salida = capturarConsola(() -> {
            notificador.notificar("Su ticket #16 cambio a EN PROGRESO.");
            notificador.setEstrategia(new NotificacionSMS());
            notificador.notificar("Su ticket #16 cambio a EN PROGRESO.");
            notificador.setEstrategia(new NotificacionWhatsApp());
            notificador.notificar("Su ticket #16 fue resuelto.");
        });

        assertEquals("Enviando EMAIL: Su ticket #16 cambio a EN PROGRESO.\n"
                   + "Enviando SMS: Su ticket #16 cambio a EN PROGRESO.\n"
                   + "Enviando WhatsApp: Su ticket #16 fue resuelto.\n", salida);
    }

    // CP-04 / Consultar historial de tickets resueltos (unitaria, situacion limite)
    @Test
    public void cp04_consultarHistorial_valoresLimite() {
        EstrategiaEspia espia = new EstrategiaEspia();
        NotificadorTicket notificador = new NotificadorTicket(espia);
        String unCaracter = "A";
        String muyLargo = "x".repeat(10000);

        notificador.notificar(unCaracter);
        notificador.notificar(muyLargo);

        assertEquals(2, espia.recibidos.size());
        assertEquals(unCaracter, espia.recibidos.get(0));
        assertEquals(10000, espia.recibidos.get(1).length());
    }

    // CP-05 / Ver panel resumen de tickets (unitaria, datos invalidos: estrategia nula)
    @Test
    public void cp05_verPanel_estrategiaNula() {
        assertThrows(IllegalArgumentException.class, () -> new NotificadorTicket(null));

        EstrategiaEspia espia = new EstrategiaEspia();
        NotificadorTicket notificador = new NotificadorTicket(espia);
        assertThrows(IllegalArgumentException.class, () -> notificador.setEstrategia(null));

        notificador.notificar("Resumen: 3 tickets abiertos.");
        assertEquals("El canal anterior debe seguir activo", 1, espia.recibidos.size());
    }
}
