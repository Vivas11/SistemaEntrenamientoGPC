package co.edu.unbosque.service;

import com.google.gson.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Adaptador Gson para serializar y deserializar {@link LocalDate} usando el formato ISO (yyyy-MM-dd).
 */
public class LocalDateAdapter implements JsonSerializer<LocalDate>, JsonDeserializer<LocalDate> {

    /** Formateador ISO estándar. */
    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;

    /**
     * Serializa un {@link LocalDate} a cadena ISO.
     * @param date Fecha a serializar.
     * @param typeOfSrc Tipo fuente.
     * @param context Contexto de serialización.
     * @return Elemento JSON con la fecha formateada.
     */
    @Override
    public JsonElement serialize(LocalDate date, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(date.format(formatter));
    }

    /**
     * Deserializa una cadena ISO (yyyy-MM-dd) a {@link LocalDate}.
     * @param json Elemento JSON.
     * @param typeOfT Tipo destino.
     * @param context Contexto de deserialización.
     * @return Instancia de LocalDate.
     * @throws JsonParseException Si el formato no es válido.
     */
    @Override
    public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        return LocalDate.parse(json.getAsString(), formatter);
    }
}
