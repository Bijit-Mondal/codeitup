package cc.codedhyan.codeitup.boilerplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class Generate {

    private static final Map<String, String> cTypeMap = new HashMap<>();
    private static final Map<String, String> javaTypeMap = new HashMap<>();

    static {
        // C type mappings
        cTypeMap.put("list<int>", "int *");
        cTypeMap.put("int", "int");
        cTypeMap.put("double", "double");
        cTypeMap.put("long long", "long long");
        // Add more C type mappings as needed

        // Javascript doesn't have types, so we don't need any type mappings

        // Java type mappings
        javaTypeMap.put("list<int>", "List<Integer>");
        javaTypeMap.put("int", "int");
        javaTypeMap.put("double", "double");
        javaTypeMap.put("long long", "long");
        javaTypeMap.put("HashMap<String, String>", "HashMap<String, String>");
        // Add more Java type mappings as needed
    }

    @Test
    public void generateAll() throws IOException {
        ClassPathResource resource = new ClassPathResource("problems");
        File problemsDir = resource.getFile();

        try (Stream<Path> paths = Files.walk(problemsDir.toPath())) {
            paths.filter(Files::isRegularFile)
                    .filter(path -> path.getFileName().toString().equals("structure.json"))
                    .forEach(this::generate);
        }
    }

    public void generate(Path structureFilePath) {
        try {
            // Read the json structure of the file
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode structure = objectMapper.readTree(structureFilePath.toFile());
            // System.out.println(structure);

            // Extract necessary information from the json structure
            String problemName = structure.get("problem_name").asText();
            String functionName = structure.get("function_name").asText();
            JsonNode inputs = structure.get("input");
            JsonNode output = structure.get("output");

            // Generate the boilerplate code
            String cBoilerplate = generateCBoilerplate(functionName, inputs, output);
            String jsBoilerplate = generateJSBoilerplate(functionName, inputs, output);
            String javaBoilerplate = generateJavaBoilerplate(problemName, functionName, inputs, output);

            Path boilerplateDir = structureFilePath.getParent().resolve("boilerplate");
            Files.createDirectories(boilerplateDir);



            saveToFile(boilerplateDir.resolve("solution.c").toString(), cBoilerplate);
            saveToFile(boilerplateDir.resolve("solution.js").toString(), jsBoilerplate);
            saveToFile(boilerplateDir.resolve("solution.java").toString(), javaBoilerplate);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String generateJavaBoilerplate(String problemName, String functionName, JsonNode inputs, JsonNode output) {
        StringBuilder inputParams = new StringBuilder();
        for (JsonNode input : inputs) {
            if (inputParams.length() > 0) {
                inputParams.append(", ");
            }
            String type = input.get("type").asText();
            inputParams.append(javaTypeMap.getOrDefault(type, type)).append(" ").append(input.get("name").asText());
        }
        StringBuilder outputReturnType = new StringBuilder();
        String outputType = output.get("type").asText();
        outputReturnType.append(javaTypeMap.getOrDefault(outputType, outputType));
        return String.format("public %s %s(%s) {\n    // Implementation goes here\n    return %s;\n}\n",
                outputReturnType.toString(), functionName, inputParams.toString(), output.get("name").asText());
    }

    private String generateJSBoilerplate(String functionName, JsonNode inputs, JsonNode output) {
        StringBuilder inputParams = new StringBuilder();
        for (JsonNode input : inputs) {
            if (inputParams.length() > 0) {
                inputParams.append(", ");
            }
            inputParams.append(input.get("name").asText());
        }
        return String.format("function %s(%s) {\n    // Implementation goes here\n    return %s;\n}\n",
                functionName, inputParams.toString(), output.get("name").asText());
    }

    private String generateCBoilerplate(String functionName, JsonNode inputs, JsonNode output) {
        StringBuilder inputParams = new StringBuilder();
        for (JsonNode input : inputs) {
            if (inputParams.length() > 0) {
                inputParams.append(", ");
            }
            String type = input.get("type").asText();
            inputParams.append(cTypeMap.getOrDefault(type, type)).append(" ").append(input.get("name").asText());
        }
        StringBuilder outputReturnType = new StringBuilder();
        String outputType = output.get("type").asText();
        outputReturnType.append(cTypeMap.getOrDefault(outputType, outputType));
        return String.format("%s %s(%s) {\n    // Implementation goes here\n    return %s;\n}\n",
                outputReturnType.toString(), functionName, inputParams.toString(), output.get("name").asText());
    }

    private static void saveToFile(String fileName, String content) {
        System.out.println("Saving to file: " + fileName);
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            fileWriter.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
