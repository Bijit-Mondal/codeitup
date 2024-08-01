package cc.codedhyan.codeitup.problem.model;

import cc.codedhyan.codeitup.contest.model.ContestProblem;
import cc.codedhyan.codeitup.contest.model.ContestSubmission;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "_problems")
public class Problem {
    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false, length = 40)
    private String title;

    @Column(nullable = false, unique = true)
    private String slug;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String testCases;

    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;

    @Builder.Default
    private Boolean hidden = true;

    @Builder.Default
    private Long solved = 0L;

    private String solutionGist;

    private String solutionTutorial;

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "problem", fetch = FetchType.EAGER)
    private List<ContestProblem> contests;

    @JsonIgnore
    @OneToMany(mappedBy = "problem", fetch = FetchType.EAGER)
    private List<ContestSubmission> tags;

    @JsonIgnore
    @OneToMany(mappedBy = "problem", fetch = FetchType.EAGER)
    private List<Submission> submissions;

    @ToString.Exclude
    @OneToMany(mappedBy = "problem", fetch = FetchType.EAGER)
    private List<DefaultCode> defaultCode;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private OffsetDateTime updatedAt;
}
