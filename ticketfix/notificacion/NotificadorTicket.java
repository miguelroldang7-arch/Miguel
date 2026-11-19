public class NotificadorTicket {
    private EstrategiaNotificacion estrategia;

    public NotificadorTicket(EstrategiaNotificacion estrategia) {
        this.estrategia = validarEstrategia(estrategia);
    }

    public void setEstrategia(EstrategiaNotificacion estrategia) {
        this.estrategia = validarEstrategia(estrategia);
    }

    public void notificar(String mensaje) {
        if (mensaje == null || mensaje.trim().isEmpty()) {
            throw new IllegalArgumentException("El mensaje de notificacion no puede estar vacio.");
        }
        estrategia.enviarNotificacion(mensaje);
    }
