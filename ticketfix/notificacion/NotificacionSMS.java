package ticketfix.notificacion;

public class NotificacionSMS implements EstrategiaNotificacion {
    @Override
    public void enviarNotificacion(String mensaje) {
        System.out.println("Enviando SMS: " + mensaje);
    }
}
