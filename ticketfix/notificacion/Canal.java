package ticketfix.notificacion;
public enum Canal {
    EMAIL(new NotificacionEmail()),
    SMS(new NotificacionSMS()),
    WHATSAPP(new NotificacionWhatsApp());

    private final EstrategiaNotificacion estrategia;

    Canal(EstrategiaNotificacion estrategia) {
        this.estrategia = estrategia;
    }

    public EstrategiaNotificacion estrategia() {
        return estrategia;
    }
}
