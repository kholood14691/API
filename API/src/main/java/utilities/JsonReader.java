package utilities;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class JsonReader {
    private static final String FILE_PATH = "src/test/resources/users.json";

    public static List<Map<String, String>> readJsonData() {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(FILE_PATH);

        try {
            return objectMapper.readValue(file, List.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read JSON file: " + " - " + e.getMessage());
        }
    }

    public static Map<String, String> getLoginData() {
        List<Map<String, String>> jsonData = readJsonData();
        return jsonData.stream()
                .filter(data -> data.containsKey("email") && data.containsKey("password"))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No valid login data found in JSON file"));
    }
}
