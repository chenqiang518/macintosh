package com.vernon.poppy.util;

import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import java.io.InputStream;
import static com.vernon.poppy.util.FileUtil.getText;

public class SchemaUtil {

    public JsonSchema getJsonSchema(String schemaPath) {
        InputStream inputStream = SchemaUtil.class.getClassLoader().getResourceAsStream(schemaPath);
        String text = getText(inputStream);
        return JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V6).getSchema(text);
    }
}
