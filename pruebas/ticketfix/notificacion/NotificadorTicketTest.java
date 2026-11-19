package ticketfix.notificacion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

// PRUEBAS UNITARIAS del contexto NotificadorTicket con una estrategia simulada (CP-04, CP-05).
public class NotificadorTicketTest {

    // CP-04 / CU-04: el contexto delega en la estrategia activa y permite cambiarla
    @Test
    public void cp04_notificar_delegaUnaSolaVezEnLaEstrategia() {
        EstrategiaEspia espia = new EstrategiaEspia();
        new NotificadorTicket(espia).notificar("Ticket #20 creado.");
        assertEquals(1, espia.recibidos.size());
        assertEquals("Ticket #20 creado.", espia.recibidos.get(0));
    }

    @Test
    public void cp04_setEstrategia_redirigeElSiguienteEnvio() {
        EstrategiaEspia primera = new EstrategiaEspia();
        EstrategiaEspia segunda = new EstrategiaEspia();
        NotificadorTicket notificador = new NotificadorTicket(primera);
        notificador.notificar("uno");
        notificador.setEstrategia(segunda);
        notificador.notificar("dos");
        assertEquals(1, primera.recibidos.size());
        assertEquals("dos", segunda.recibidos.get(0));
    }

    // CP-05 / CU-05: datos invalidos deben rechazarse antes de enviar
    @Test
    public void cp05_constructorConEstrategiaNula_lanzaIllegalArgumentException() {
        try {
            new NotificadorTicket(null);
            fail("Se esperaba IllegalArgumentException al crear el notificador sin estrategia");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("estrategia"));
        }
    }

    @Test
    public void cp05_setEstrategiaNula_lanzaIllegalArgumentException() {
        NotificadorTicket notificador = new NotificadorTicket(new EstrategiaEspia());
        try {
            notificador.setEstrategia(null);
            fail("Se esperaba IllegalArgumentException al asignar una estrategia nula");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("estrategia"));
        }
    }

    @Test
    public void cp05_mensajeNulo_seRechazaYNoSeEnvia() {
        verificarMensajeRechazado(null);
    }

    @Test
    public void cp05_mensajeVacio_seRechazaYNoSeEnvia() {
        verificarMensajeRechazado("");
    }

    @Test
    public void cp05_mensajeSoloEspacios_seRechazaYNoSeEnvia() {
        verificarMensajeRechazado("   ");
    }

    private void verificarMensajeRechazado(String mensaje) {
        EstrategiaEspia espia = new EstrategiaEspia();
        NotificadorTicket notificador = new NotificadorTicket(espia);
        try {
            notificador.notificar(mensaje);
            fail("Se esperaba IllegalArgumentException para el mensaje [" + mensaje + "]");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("mensaje"));
        }
        assertEquals("No debe enviarse nada", 0, espia.recibidos.size());
    }
}
