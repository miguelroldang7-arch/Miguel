package ticketfix.notificacion;

public class MainNotificacion {
    public static void main(String[] args) {
        NotificadorTicket notificador = new NotificadorTicket(new NotificacionEmail());
        notificador.notificar("Su ticket ha sido creado exitosamente.");
    }
}
