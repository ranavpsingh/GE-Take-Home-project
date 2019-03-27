package com.web.crawler.util;

import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class JsonUtility {
	private static ObjectMapper objMapper = new ObjectMapper();

    private static ObjectWriter objWritter = objMapper.writer().withDefaultPrettyPrinter();
    
    public static String toJson(Object pojo) throws JsonMappingException, JsonGenerationException, IOException {

        return objWritter.writeValueAsString(pojo);

    }
    
    public static <T> Object fromJson(InputStream json, Class<T> pojoClass)
            throws JsonMappingException, JsonParseException, IOException {
        return objMapper.readValue(json, pojoClass);
    }
    
}
