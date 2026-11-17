package ticketfix.notificacion;

public class NotificacionWhatsApp implements EstrategiaNotificacion {
    @Override
    public void enviarNotificacion(String mensaje) {
        System.out.println("Enviando WhatsApp: " + mensaje);
    }
}
