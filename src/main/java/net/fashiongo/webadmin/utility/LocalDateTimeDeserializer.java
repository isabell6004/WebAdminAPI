package net.fashiongo.webadmin.utility;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    @Override
    public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        if(jsonParser.getText().length() == 19) {
            return LocalDateTime.parse(jsonParser.getText(), DATE_FORMAT);
        } else {
            return LocalDateTime.parse(jsonParser.getText().substring(0, 19), DATE_FORMAT);
        }
    }
}
