package cc.codedhyan.codeitup.RunnerFull;

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
//            String cRunner = generateCRunner(functionName, inputs, output);
            String jsRunner = generateJSRunner(functionName, inputs, output);
//            String javaRunner = generateJavaRunner(problemName, functionName, inputs, output);

//            System.out.println("C Runner: "+ cRunner);
            System.out.println("JS Runner: "+ jsRunner);
//            System.out.println("Java Runner: "+ javaRunner);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    private String generateJavaRunner(String problemName, String functionName, JsonNode inputs, JsonNode output) {
//        StringBuilder inputSet = new StringBuilder();
//        StringBuilder outputSet = new StringBuilder();
//        for(JsonNode input : inputs) {
//
//        }
//    }

    private String generateJSRunner(String functionName, JsonNode inputs, JsonNode output) {
        StringBuilder inputSetup = new StringBuilder();
        for (int i = 0; i < inputs.size(); i++) {
            JsonNode input = inputs.get(i);
            if (i == 0) {
                inputSetup.append("const ").append(input.get("name").asText()).append(" = ");
            } else {
                inputSetup.append("const ").append(input.get("name").asText()).append(" = ");
            }
            inputSetup.append("require('fs').readFileSync('/dev/problems/")
                    .append(functionName).append("/tests/inputs/##INPUT_FILE_INDEX##.txt', 'utf8')\n")
                    .append("    .trim().split('\\n').join(' ').split(' ');\n");
        }

        return String.format(
                "##USER_CODE_HERE##\n" +
                        "const input = require('fs').readFileSync('/dev/problems/%s/tests/inputs/##INPUT_FILE_INDEX##.txt', 'utf8').trim().split('\\n').join(' ').split(' ');\n" +
                        "const %s = input.splice(0, input.length).map(Number);\n" +
                        "const result = %s(%s);\n" +
                        "console.log(result);\n",
                functionName, inputs.get(0).get("name").asText(), functionName, inputs.get(0).get("name").asText()
        );
    }

//    private String generateCRunner(String functionName, JsonNode inputs, JsonNode output) {
//
//    }

    private static void saveToFile(String fileName, String content) {
        System.out.println("Saving to file: " + fileName);
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            fileWriter.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
