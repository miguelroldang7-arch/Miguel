package ticketfix.notificacion;

import org.junit.Test;
import static org.junit.Assert.*;

public class NotificadorTicketTest {

    @Test
    public void debeEnviarNotificacionConEstrategiaConfigurada() {
        EstrategiaEspia espia = new EstrategiaEspia();
        NotificadorTicket notificador = new NotificadorTicket(espia);

        notificador.notificar("Ticket #101 asignado");

        assertEquals(1, espia.getLlamadas());
        assertEquals("Ticket #101 asignado", espia.getUltimoMensaje());
    }

    @Test
    public void debePermitirCambiarEstrategiaDinamicamente() {
        EstrategiaEspia primera = new EstrategiaEspia();
        EstrategiaEspia segunda = new EstrategiaEspia();
        NotificadorTicket notificador = new NotificadorTicket(primera);

        notificador.notificar("Mensaje 1");
        notificador.setEstrategia(segunda);
        notificador.notificar("Mensaje 2");

        assertEquals(1, primera.getLlamadas());
        assertEquals(1, segunda.getLlamadas());
        assertEquals("Mensaje 2", segunda.getUltimoMensaje());
    }

    @Test(expected = IllegalArgumentException.class)
    public void debeFallarCuandoMensajeEsNulo() {
        EstrategiaEspia espia = new EstrategiaEspia();
        NotificadorTicket notificador = new NotificadorTicket(espia);

        notificador.notificar(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void debeFallarCuandoEstrategiaEsNula() {
        new NotificadorTicket(null);
    }
}
