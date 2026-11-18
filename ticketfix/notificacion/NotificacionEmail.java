package ticketfix.notificacion;

public class NotificacionEmail implements EstrategiaNotificacion {
    @Override
    public void enviarNotificacion(String mensaje) {
        System.out.println("Enviando EMAIL: " + mensaje);
    }
}
