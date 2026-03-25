package com.obdasystems.swagger;

import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class SearchMonolithUnusedRoutes {

    public static void main(String[] args) throws Exception {
        Yaml yaml = new Yaml();
        var operations = new HashSet<String>();
        try (Stream<Path> path = Files.walk(Path.of("../apis/paths"))) {
            path.forEach(file -> {
                if (file.toString().endsWith(".yaml")) {
                    Map<String, Map> obj;
                    try {
                        obj = yaml.load(new InputStreamReader(new FileInputStream(file.toFile())));
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    if (obj != null) {
                        obj.forEach((k, v) -> {
                            String operationId = (String) v.get("operationId");
                            if (operationId != null)
                                operations.add(operationId);
                        });
                    }
                }
            });
        }
        var totalOperations = operations.size();
        System.out.println("Scanned operations: " + totalOperations);
        var notFounds = 0;
        var founds = 0;
        for (var operationId : operations) {
            AtomicBoolean found = new AtomicBoolean(false);
            try (Stream<Path> path = Files.walk(Path.of("../../monolith/src"))) {
                path.forEach(mFile -> {
                    try {
                        if (mFile.toFile().isFile()) {
                            var scanner = new Scanner(mFile.toFile());
                            while (scanner.hasNextLine()) {
                                final String lineFromFile = scanner.nextLine();
                                if (lineFromFile != null) {
                                    if (lineFromFile.contains("." + operationId + "(")) {
                                        found.set(true);
                                        throw new RuntimeException("Found!");
                                    }
                                }
                            }
                        }
                    } catch (RuntimeException e) {
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                });
                if (!found.get()) {
                    System.out.println(operationId);
                    notFounds++;
                } else {
                    founds++;
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Used operations: " + founds + "(" + (founds * 100) / totalOperations + "%)");
        System.out.println("Not used operations: " + notFounds + "(" + (notFounds * 100) / totalOperations + "%)");
    }
}
