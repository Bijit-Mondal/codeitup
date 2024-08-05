package cc.codedhyan.codeitup.problem.model;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum SubmissionResult {
    ACCEPTED("Accepted"),
    REJECTED("Rejected"),
    PENDING("Pending");

    private final String value;
    public String getValue() {
        return value;
    }
}
