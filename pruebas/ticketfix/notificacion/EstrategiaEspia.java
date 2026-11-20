package ticketfix.notificacion;

import java.util.ArrayList;
import java.util.List;

public class EstrategiaEspia implements EstrategiaNotificacion {
    private final List<String> mensajesEnviados = new ArrayList<>();
    public final List<String> recibidos = new ArrayList<>();
    private int llamadas = 0;

    @Override
    public void enviarNotificacion(String mensaje) {
        llamadas++;
        mensajesEnviados.add(mensaje);
        recibidos.add(mensaje);
    }

    public int getLlamadas() {
        return llamadas;
    }

    public String getUltimoMensaje() {
        if (mensajesEnviados.isEmpty()) {
            return null;
        }
        return mensajesEnviados.get(mensajesEnviados.size() - 1);
    }

    public List<String> getMensajesEnviados() {
        return mensajesEnviados;
    }
}
