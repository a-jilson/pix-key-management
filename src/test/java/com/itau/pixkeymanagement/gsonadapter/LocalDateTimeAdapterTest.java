package com.itau.pixkeymanagement.gsonadapter;

import com.google.gson.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.lang.reflect.Type;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LocalDateTimeAdapterTest {

    @Test
    void serialize_LocalDateTime_Success() {
        // Given
        LocalDateTime localDateTime = LocalDateTime.of(2022, 4, 30, 12, 30, 15);
        LocalDateTimeAdapter adapter = new LocalDateTimeAdapter();
        JsonSerializationContext context = Mockito.mock(JsonSerializationContext.class);

        // When
        JsonElement jsonElement = adapter.serialize(localDateTime, LocalDateTime.class, context);

        // Then
        assertEquals("2022-04-30T12:30:15", jsonElement.getAsString());
    }

    @Test
    void deserialize_JsonElementToLocalDateTime_Success() {
        // Given
        String dateTimeString = "2022-04-30T12:30:15";
        JsonElement jsonElement = new JsonPrimitive(dateTimeString);
        LocalDateTimeAdapter adapter = new LocalDateTimeAdapter();
        JsonDeserializationContext context = Mockito.mock(JsonDeserializationContext.class);

        // When
        LocalDateTime localDateTime = adapter.deserialize(jsonElement, LocalDateTime.class, context);

        // Then
        LocalDateTime expectedDateTime = LocalDateTime.of(2022, 4, 30, 12, 30, 15);
        assertEquals(expectedDateTime, localDateTime);
    }
}