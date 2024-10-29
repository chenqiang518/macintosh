package com.vernon.poppy.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.ValidationMessage;
import com.vernon.poppy.util.SchemaUtil;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Set;


public class TestJsonSchema {

    @Test
    void testJsonSchema() throws IOException {
        SchemaUtil schemaUtil = new SchemaUtil();
        JsonSchema jsonSchema = schemaUtil.getJsonSchema("schema/simple_list.json");

        String jsonData = "{\n" +
                "    \"errcode\": 0,\n" +
                "    \"errmsg\": \"ok\",\n" +
                "    \"department_id\": [\n" +
                "        {\n" +
                "            \"id\": 1,\n" +
                "            \"parentid\": 0,\n" +
                "            \"order\": 100000000\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 328251,\n" +
                "            \"parentid\": 1,\n" +
                "            \"order\": 1\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 478258,\n" +
                "            \"parentid\": 1,\n" +
                "            \"order\": 1\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 942215,\n" +
                "            \"parentid\": 1,\n" +
                "            \"order\": 1\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 331035,\n" +
                "            \"parentid\": 1,\n" +
                "            \"order\": 1\n" +
                "        }\n" +
                "    ]\n" +
                "}";

        JsonMapper mapper = new JsonMapper();
        JsonNode node = mapper.readTree(jsonData.getBytes());

        Set<ValidationMessage> errors = jsonSchema.validate(node);

        if (errors.isEmpty()) {
            System.out.println("JSON数据符合Schema规定的格式。");
        } else {
            System.out.println("JSON数据不符合Schema规定的格式。");
            errors.forEach(System.out::println);
        }

    }
}
