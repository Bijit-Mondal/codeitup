package cc.codedhyan.codeitup.contest.model;

import cc.codedhyan.codeitup.problem.model.Problem;
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
@Table(name = "_contest_problem")
@IdClass(ContestProblemId.class)
public class ContestProblem {
    @Id
    private String contestId;

    @Id
    private String problemId;

    @ManyToOne
    @JoinColumn(name = "contestId", insertable = false, updatable = false)
    private Contest contest;

    @ManyToOne
    @JoinColumn(name = "problemId", insertable = false, updatable = false)
    private Problem problem;

    private Integer occurrence;

    @Builder.Default
    private Long solved = 0L;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private OffsetDateTime updatedAt;
}
