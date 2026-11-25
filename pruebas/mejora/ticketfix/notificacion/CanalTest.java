package ticketfix.notificacion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertThrows;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.Test;

public class CanalTest {

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

    @Test
    public void mt01_cadaCanalResuelveSuEstrategia() {
        assertTrue(Canal.EMAIL.estrategia() instanceof NotificacionEmail);
        assertTrue(Canal.SMS.estrategia() instanceof NotificacionSMS);
        assertTrue(Canal.WHATSAPP.estrategia() instanceof NotificacionWhatsApp);
        assertEquals(3, Canal.values().length);
    }

    @Test
    public void mt02_mismaSalidaQueConClasesConcretas() {
        String conClases = capturarConsola(() ->
            new NotificadorTicket(new NotificacionSMS()).notificar("Aviso de prueba."));
        String conCanal = capturarConsola(() ->
            NotificadorTicket.porCanal(Canal.SMS).notificar("Aviso de prueba."));

        assertEquals("Enviando SMS: Aviso de prueba.\n", conClases);
        assertEquals(conClases, conCanal);
    }

    @Test
    public void mt03_cambioDeCanalEnEjecucion() {
        NotificadorTicket notificador = NotificadorTicket.porCanal(Canal.EMAIL);

        String salida = capturarConsola(() -> {
            notificador.notificar("Ticket #16 en progreso.");
            notificador.setCanal(Canal.WHATSAPP);
            notificador.notificar("Ticket #16 resuelto.");
        });

        assertEquals("Enviando EMAIL: Ticket #16 en progreso.\n"
                   + "Enviando WhatsApp: Ticket #16 resuelto.\n", salida);
    }

    @Test
    public void mt04_canalNuloSeRechaza() {
        assertThrows(IllegalArgumentException.class, () -> NotificadorTicket.porCanal(null));

        NotificadorTicket notificador = NotificadorTicket.porCanal(Canal.EMAIL);
        assertThrows(IllegalArgumentException.class, () -> notificador.setCanal(null));

        String salida = capturarConsola(() -> notificador.notificar("Sigue en EMAIL."));
        assertEquals("Enviando EMAIL: Sigue en EMAIL.\n", salida);
    }
}
