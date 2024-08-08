package cc.codedhyan.codeitup.problem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "_test_cases")
public class TestCases {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private Integer ind;

    private String submissionId;

    @ManyToOne
    @JoinColumn(name = "submissionId", insertable = false, updatable = false)
    @JsonIgnore
    @ToString.Exclude
    private Submission submission;

    @Column(unique = true)
    private String judge0TrackingId;

    private String time;

    private Integer memory;

    @Enumerated(EnumType.STRING)
    private TestCasesResult testCasesResult;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private OffsetDateTime updatedAt;
}