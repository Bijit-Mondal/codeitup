package cc.codedhyan.codeitup.problem.model;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum TestCasesResult {
    PENDING("Pending"),
    ACCEPTED("Accepted"),
    WRONG_ANSWER("Wrong Answer"),
    TIME_LIMIT_EXCEEDED("Time Limit Exceeded"),
    MEMORY_LIMIT_EXCEEDED("Memory Limit Exceeded"),
    RUNTIME_ERROR("Runtime Error"),
    COMPILATION_ERROR("Compilation Error"),
    INTERNAL_ERROR("Internal Error");

    private final String value;

    public String getValue() {
        return value;
    }
}
