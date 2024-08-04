package cc.codedhyan.codeitup.problem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "_default_codes")
@IdClass(DefaultCodeId.class)
public class DefaultCode {
    @Id
    private Integer languageId;

    @Id
    private String problemId;

    private String code;

    @JsonIgnore
    private String runnerCode;

    @ManyToOne
    @JoinColumn(name = "problemId", insertable = false, updatable = false)
    @JsonIgnore
    private Problem problem;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "languageId", insertable = false, updatable = false)
    private Language language;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private OffsetDateTime createdAt;
    @UpdateTimestamp
    @Column(nullable = false)
    private OffsetDateTime updatedAt;
}
