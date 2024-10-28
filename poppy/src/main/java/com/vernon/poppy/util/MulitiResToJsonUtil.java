package com.vernon.poppy.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MulitiResToJsonUtil {

    // 返回响应体转化为 Json 通用格式
    public String mulitiResToJson(String originRes) {

        if (originRes.startsWith("<?xml")) {
            XmlMapper xmlMapper = new XmlMapper();
            try {
                JsonNode node = xmlMapper.readTree(originRes.getBytes());
                ObjectMapper jsonMapper = new ObjectMapper();
                originRes = jsonMapper.writeValueAsString(node);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return originRes;
    }

}
