package ticketfix.notificacion;
public class MainNotificacion {
    public static void main(String[] args) {
        NotificadorTicket notificador = NotificadorTicket.porCanal(Canal.EMAIL);
        notificador.notificar("Su ticket ha sido creado exitosamente.");
    }
}
