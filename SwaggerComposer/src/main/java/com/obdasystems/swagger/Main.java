package com.obdasystems.swagger;

import io.swagger.parser.OpenAPIParser;
import io.swagger.util.Yaml;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.parser.core.models.SwaggerParseResult;

import java.io.File;

public class Main {
    public static void main(String[] args) throws Exception {
        SwaggerParseResult result = new OpenAPIParser().readLocation("new/mastro.yaml", null, null);
        OpenAPI openAPI = result.getOpenAPI();
        for (var p : openAPI.getPaths().keySet()) {
            setTags(p, openAPI);
        }
        Yaml.mapper().writeValue(new File("new/mastro_gen.yaml"), openAPI);
    }

    private static void setTags(String p, OpenAPI openAPI) {
        var tag = getTag(p);
        var path = openAPI.getPaths().get(p);
        if (path.getGet() != null) path.getGet().addTagsItem(tag);
        if (path.getPost() != null) path.getPost().addTagsItem(tag);
        if (path.getPut() != null) path.getPut().addTagsItem(tag);
        if (path.getDelete() != null) path.getDelete().addTagsItem(tag);
    }

    private static String getTag(String path) {
        if (path.contains("avp") || path.contains("authorizationViewProfile")) {
            return "AVP";
        } else if (path.contains("ontologyDraft")) {
            return "Ontology Draft";
        } else if (path.contains("ontologyConstraint")) {
            return "Ontology Constraints";
        } else if (path.contains("endpoint")) {
            return "Endpoint";
        } else if (path.contains("highlights")) {
            return "Highlights";
        } else if (path.contains("queryGraph")) {
            return "Query Graph";
        } else if (path.contains("mapping") || path.contains("triplify")) {
            return "Mappings";
        } else if (path.contains("ontologyCatalog")) {
            return "Ontology Catalog";
        } else if (path.contains("logger")) {
            return "Logger";
        } else if (path.contains("datasource")) {
            return "Datasource";
        } else if (path.contains("owlOntology")) {
            return "Ontology";
        } else if (path.contains("knowledgeGraph")) {
            return "Knowledge Graph";
        } else if (path.contains("role")) {
            return "Role";
        } else if (path.contains("statistics")) {
            return "Statistics";
        } else if (path.contains("user")) {
            return "User";
        } else if (path.contains("taskScheduler")) {
            return "Task Scheduler";
        } else return "General";
    }
}