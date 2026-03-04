package com.obdasystems.swagger;

import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class AddDefaultValueToPrimitiveObject {

    public static void main(String[] args) throws Exception {
        Yaml yaml = new Yaml();
        Files.walk(Path.of("../apis/components/schemas")).forEach(file -> {
            if (file.toString().endsWith(".yaml")) {
                AtomicBoolean modified = new AtomicBoolean(false);
                Map obj = null;
                try {
                    obj = yaml.load(new InputStreamReader(new FileInputStream(file.toFile())));
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                if (obj != null) {
                    Map<String, Map> props = (Map) obj.get("properties");
                    if (props != null) {
                        props.forEach((k, v) -> {
                            String type = (String) v.get("type");
                            if (type != null && type.equals("boolean")) {
                                v.put("default", false);
                                modified.set(true);
                            }
                        });
                    }
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
