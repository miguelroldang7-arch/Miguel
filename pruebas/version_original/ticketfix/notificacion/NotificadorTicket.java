package ticketfix.legado;

// Copia de la version original (Unidad II, antes de Strategy).
// Solo se le agrego el paquete y se renombro para compararla en la prueba de regresion.
public class NotificadorTicketOriginal {
    public void notificar(String canal, String mensaje) {
        if (canal.equals("EMAIL")) {
            System.out.println("Enviando EMAIL: " + mensaje);
        } else if (canal.equals("SMS")) {
            System.out.println("Enviando SMS: " + mensaje);
        } else {
            throw new IllegalArgumentException("Canal no soportado: " + canal);
        }
    }
}
