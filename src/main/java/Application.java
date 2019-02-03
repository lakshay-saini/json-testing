import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Application {

    public static void main(String[] args) {
        String input = "{\n" +
                "    \"result\": {\n" +
                "        \"ledger_hash\": \"8B5A0C5F6B198254A6E411AF55C29EE40AA86251D2E78DD0BB17647047FA9C24\",\n" +
                "        \"ledger_index\": 8696231,\n" +
                "        \"status\": \"success\"\n" +
                "    }\n" +
                "}";

        Map<String, Object> objectMap = loadJson(input);
        Map<String, Object> result = (Map) objectMap.get("result");
        System.out.println("objectMap = " + result.get("ledger_index"));
    }

    public static <T> List<T> loadJsonArray(final JsonNode jsonNode, final Class<T> type) {
        final ObjectMapper mapper = new ObjectMapper(new JsonFactory());
        try {
            return mapper.readerFor(mapper.getTypeFactory().constructCollectionType(List.class, type)).readValue(jsonNode);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> T loadJson(final JsonNode jsonNode, final Class<T> clazz) {
        final ObjectMapper mapper = new ObjectMapper(new JsonFactory());
        try {
            return mapper.readerFor(clazz).readValue(jsonNode);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> T loadJson(final String json, final Class<T> clazz, final T obj) {
        final ObjectMapper mapper = new ObjectMapper(new JsonFactory());
        try {
            return mapper.readerForUpdating(obj).forType(clazz).readValue(json);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> T loadJson(final String json, final Class<T> clazz) {
        final ObjectMapper mapper = new ObjectMapper(new JsonFactory());
        try {
            return mapper.readerFor(clazz).readValue(json);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Map<String, Object> loadJson(final String json) {
        final ObjectMapper mapper = new ObjectMapper(new JsonFactory());
        try {
            return mapper.readValue(json, new TypeReference<Map<String, Object>>() {});
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static JsonNode getJsonNode(final String json) {
        final ObjectMapper mapper = new ObjectMapper(new JsonFactory());
        try {
            return mapper.readTree(json);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String toJson(final Object o){
        final ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(o);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
