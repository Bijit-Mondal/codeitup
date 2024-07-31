package cc.codedhyan.codeitup.contest.model;

import cc.codedhyan.codeitup.problem.model.Problem;
import cc.codedhyan.codeitup.user.model.User;
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
@AllArgsConstructor
@NoArgsConstructor
@IdClass(ContestSubmissionId.class)
public class ContestSubmission {

    @Id
    private String contestId;
    @Id
    private String problemId;
    @Id
    private String userId;

    @ManyToOne
    @JoinColumn(name = "contestId", insertable = false, updatable = false)
    private Contest contest;

    @ManyToOne
    @JoinColumn(name = "problemId", insertable = false, updatable = false)
    private Problem problem;

    @ManyToOne
    @JoinColumn(name = "userId", insertable = false, updatable = false)
    private User user;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String submissionId;

    private Integer points;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private OffsetDateTime updatedAt;

}
