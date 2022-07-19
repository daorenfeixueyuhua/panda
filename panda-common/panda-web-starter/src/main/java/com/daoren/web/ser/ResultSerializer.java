package com.daoren.web.ser;

import com.daoren.web.entity.Result;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Result序列化
 *
 * @author peng_da
 * @version :
 * @date 2022/2/18 9:10
 * @since :
 */
@Component
public class ResultSerializer extends JsonSerializer<Result> {
    @Override
    public void serialize(Result value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeObjectField(Result.FIELD_NAME_CODE, value.get(Result.FIELD_NAME_CODE));
        gen.writeObjectField(Result.FIELD_NAME_MSG, value.get(Result.FIELD_NAME_MSG));
        gen.writeObjectField(Result.FIELD_NAME_DATA, value.get(Result.FIELD_NAME_DATA));
        gen.writeObjectField(Result.FIELD_NAME_TIMESTAMP, value.get(Result.FIELD_NAME_TIMESTAMP));
        gen.writeEndObject();
    }
}
