package com.obdasystems.swagger;

import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class RenameOperationIdToBeCamelCase {

    public static void main(String[] args) throws Exception {
        Yaml yaml = new Yaml();
        Files.walk(Path.of("../apis/paths")).forEach(file -> {
            if (file.toString().endsWith(".yaml")) {
                AtomicBoolean modified = new AtomicBoolean(false);
                Map<String, Map> obj = null;
                try {
                    obj = yaml.load(new InputStreamReader(new FileInputStream(file.toFile())));
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                if (obj != null) {
                    obj.forEach((k, v) -> {
                        String operationId = (String) v.get("operationId");
                        if (operationId != null && operationId.contains("_")) {
                            operationId = operationId.replace("__", "_");
                            while (operationId.contains("_")) {
                                operationId = operationId
                                        .replaceFirst(
                                                "_[a-zA-Z]",
                                                String.valueOf(
                                                        Character.toUpperCase(operationId.charAt(operationId.indexOf("_") + 1))
                                                )
                                        );
                            }
                            v.put("operationId", operationId);
                            modified.set(true);
                        }
                    });
                    if (modified.get()) {
                        try {
                            yaml.dump(obj, Files.newBufferedWriter(file));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        });
    }
}
