package cc.codedhyan.codeitup.boilerplateFull;

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

import static cc.codedhyan.codeitup.boilerplate.Generate.saveToFile;

public class Generate {

    private static final Map<String, String> cTypeMap = new HashMap<>();
    private static final Map<String, String> javaTypeMap = new HashMap<>();
    private static final Map<String, String> javaParserMap = new HashMap<>();

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

        javaParserMap.put("int", "Integer");
        javaParserMap.put("double", "Double");
        javaParserMap.put("long long", "Long");
        javaParserMap.put("char","Character");
        javaParserMap.put("string","String");
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

    private void generate(Path structureFilePath) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode structure = objectMapper.readTree(structureFilePath.toFile());

            // Extract necessary information
            String problemName = structure.get("problem_name").asText();
            String functionName = structure.get("function_name").asText();
            JsonNode inputs = structure.get("input");
            JsonNode output = structure.get("output");

            // Generate full-Runner code
            String javaRunner = generateJavaRunner(problemName, functionName, inputs, output);

            Path runnerDir = structureFilePath.getParent().resolve("runner");
            Files.createDirectories(runnerDir);

            saveToFile(runnerDir.resolve("Solution.java").toString(), javaRunner);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String generateJavaRunner(String problemName, String functionName, JsonNode inputs, JsonNode output) {
        StringBuilder inputReads = new StringBuilder();
        int inputReadIndex = 0;
        StringBuilder paramList = new StringBuilder();
        for(JsonNode input:inputs){
            paramList.append(String.format("%s,",input.get("name").asText()));
            if (input.get("type").asText().startsWith("list<")) {
                String type = input.get("type").asText().substring(5, input.get("type").asText().length() - 1);
                String parse = type.substring(0, 1).toUpperCase() + type.substring(1);

                inputReads.append(String.format("""
                    int size_%s = Integer.parseInt(lines.get(%d).trim());
                    %s %s = new ArrayList<>(size_%s);
                    String[] inputStr_%s = lines.get(%d).split("\\s+");
                    for (String str : inputStr_%s) {
                        %s.add(%s.parse%s(str));
                    }
                    """,
                        input.get("name").asText(),
                        inputReadIndex++,
                        javaTypeMap.get(input.get("type").asText()),
                        input.get("name").asText(),
                        input.get("name").asText(),
                        input.get("name").asText(),
                        inputReadIndex++,
                        input.get("name").asText(),
                        input.get("name").asText(),
                        javaParserMap.get(type),
                        parse
                ));
            } else {
                inputReads.append(String.format("""
                    %s %s = 0;
                    """,
                        javaTypeMap.get(input.get("type").asText()),
                        input.get("name").asText()
                ));
            }
        }
        String outputType = javaTypeMap.get(output.get("type").asText());

        String functionCall = String.format("%s result = %s.%s(%s);", outputType, problemName, functionName, paramList.substring(0,paramList.length()-1));

        String outputWrite = "System.out.println(result);";

        return String.format(
                """
                        import java.io.*;
                        import java.util.*;

                        public class Solution {

                            public static void main(String[] args) {
                                String filePath = "/dev/stdin";
                                List<String> lines = readLinesFromFile(filePath);
                                %s
                                %s
                                %s
                            }
                            public static List<String> readLinesFromFile(String filePath) {
                                List<String> lines = new ArrayList<>();
                                try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                                    String line;
                                    while ((line = br.readLine()) != null) {
                                        lines.add(line);
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                return lines;
                            }
                        }
                        ##USER_CODE_HERE##
                        """,
                inputReads, functionCall, outputWrite
        );
   }
}
