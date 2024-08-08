package cc.codedhyan.codeitup.contest.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "_contest")
public class Contest {
    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false, length = 40)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false, columnDefinition = "DATETIME")
    private LocalDateTime startTime;

    @Column(nullable = false, columnDefinition = "DATETIME")
    private LocalDateTime endTime;

    @Column(nullable = false)
    private Boolean hidden;

    @OneToMany(mappedBy = "contest", fetch = FetchType.EAGER)
    private List<ContestProblem> problems;

    @OneToMany(mappedBy = "contest", fetch = FetchType.EAGER)
    private List<ContestSubmission> submissions;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private OffsetDateTime updatedAt;
}
