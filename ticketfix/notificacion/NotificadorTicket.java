package ticketfix.notificacion;

public class NotificadorTicket {
    private EstrategiaNotificacion estrategia;

    public NotificadorTicket(EstrategiaNotificacion estrategia) {
        this.estrategia = validarEstrategia(estrategia);
    }

    public static NotificadorTicket porCanal(Canal canal) {
        return new NotificadorTicket(estrategiaDe(canal));
    }

    public void setEstrategia(EstrategiaNotificacion estrategia) {
        this.estrategia = validarEstrategia(estrategia);
    }

    public void setCanal(Canal canal) {
        this.estrategia = estrategiaDe(canal);
    }

    public void notificar(String mensaje) {
        if (mensaje == null || mensaje.trim().isEmpty()) {
            throw new IllegalArgumentException("El mensaje de notificacion no puede estar vacio.");
        }
        estrategia.enviarNotificacion(mensaje);
    }

    private static EstrategiaNotificacion estrategiaDe(Canal canal) {
        if (canal == null) {
            throw new IllegalArgumentException("El canal de notificacion no puede ser nulo.");
        }
        return canal.estrategia();
    }

    private static EstrategiaNotificacion validarEstrategia(EstrategiaNotificacion estrategia) {
        if (estrategia == null) {
            throw new IllegalArgumentException("La estrategia de notificacion no puede ser nula.");
        }
        return estrategia;
    }
}
