package cc.codedhyan.codeitup.problem.model;

import cc.codedhyan.codeitup.user.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "_submission")
public class Submission {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(updatable = false)
    private String problemId;

    @ManyToOne
    @JoinColumn(name="problemId",insertable = false,updatable = false)
    private Problem problem;

    @Column(updatable = false)
    private String userId;

    @ManyToOne
    @JoinColumn(name="userId",insertable = false,updatable = false)
    private User user;

    @OneToMany(mappedBy = "submission",fetch = FetchType.EAGER)
    private List<TestCases> testCases;


    @Column(nullable = false, columnDefinition = "TEXT")
    private String submissionId;

    @Enumerated(EnumType.STRING)
    private SubmissionResult submissionResult;

    private Integer memory;

    private Double time;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private OffsetDateTime updatedAt;
}
