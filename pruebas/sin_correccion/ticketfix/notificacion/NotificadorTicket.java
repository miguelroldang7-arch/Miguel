package ticketfix.notificacion;

public class NotificadorTicket {

    private EstrategiaNotificacion estrategia;

    public NotificadorTicket(EstrategiaNotificacion estrategia) {
        this.estrategia = estrategia;
    }

    public void setEstrategia(EstrategiaNotificacion estrategia) {
        this.estrategia = estrategia;
    }

    public void notificar(String mensaje) {
        estrategia.enviarNotificacion(mensaje);
    }
}
