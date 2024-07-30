package cc.codedhyan.codeitup.problem.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class DefaultCodeId implements Serializable {
    private Integer languageId;
    private String problemId;
}
