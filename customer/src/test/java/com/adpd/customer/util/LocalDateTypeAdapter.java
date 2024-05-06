package com.adpd.customer.util;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateTypeAdapter implements JsonSerializer<LocalDate>, JsonDeserializer<LocalDate> {

    public static final DateTimeFormatter LOCAL_DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Override
    public JsonElement serialize(final LocalDate date, final Type type, final JsonSerializationContext context) {
        return new JsonPrimitive(date.format(LOCAL_DATE_FORMATTER));
    }

    @Override
    public LocalDate deserialize(final JsonElement json, final Type type, final JsonDeserializationContext context) {
        return LocalDate.parse(json.getAsString(), LOCAL_DATE_FORMATTER);
    }
}
