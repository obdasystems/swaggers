package com.obdasystems.swagger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

public class Add400Response {

    public static void main(String[] args) throws Exception {
        Yaml yaml = new Yaml();
        Files.walk(Path.of("apis/paths")).forEach(file -> {
            if (file.toString().endsWith(".yaml")) {
                Map<String, LinkedHashMap> obj = null;
                try {
                    obj = yaml.load(new InputStreamReader(new FileInputStream(file.toFile())));
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                if (obj != null) {
                    obj.forEach((k, v) -> {
                        Map<String, Object> responses = (Map<String, Object>) v.get("responses");
                        if (!responses.containsKey("400")) {
                            var res401 = new LinkedHashMap<>();
                            res401.put("description", "Unhauthorized");
                            responses.put("401", res401);
                        }
                    });
                    try {
                        yaml.dump(obj, Files.newBufferedWriter(file));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }
}
