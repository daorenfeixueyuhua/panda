package com.daoren.json.ser;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 自定义LocalDateTime序列化
 *
 * @author peng_da
 * @version :
 * @date 2022/2/18 9:10
 * @since :
 */
@Component
public class CLocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        String datetime = DATE_TIME_FORMATTER.format(value);
        final JsonStreamContext outputContext =
                gen.getOutputContext();
        final String currentName = outputContext.getCurrentName();
        gen.writeString(datetime);
    }
}
