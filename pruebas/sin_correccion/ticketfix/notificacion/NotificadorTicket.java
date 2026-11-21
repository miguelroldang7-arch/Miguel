package ticketfix.notificacion;

public class NotificadorTicket {

    private EstrategiaNotificacion estrategia;

    public NotificadorTicket(EstrategiaNotificacion estrategia) {
        if (estrategia == null) {
            throw new IllegalArgumentException("La estrategia de notificación no puede ser nula.");
        }
        this.estrategia = estrategia;
    }

    public void setEstrategia(EstrategiaNotificacion estrategia) {
        if (estrategia == null) {
            throw new IllegalArgumentException("La estrategia de notificación no puede ser nula.");
        }
        this.estrategia = estrategia;
    }

    public void notificar(String mensaje) {
        if (mensaje == null || mensaje.trim().isEmpty()) {
            throw new IllegalArgumentException("El mensaje de notificación no puede estar vacío.");
        }
        estrategia.enviarNotificacion(mensaje);
    }
}
