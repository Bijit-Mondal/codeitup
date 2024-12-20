package cc.codedhyan.codeitup.problem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.checkerframework.common.reflection.qual.ClassBound;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "_languages")
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private Integer judge0id;

    @Column(unique = true,nullable = false)
    private String aceEditor;

    @ToString.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "language",fetch = FetchType.EAGER)
    private List<DefaultCode> defaultCode;

    @JsonIgnore
    @OneToMany(mappedBy = "language", fetch = FetchType.EAGER)
    private List<Submission> submissions;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private OffsetDateTime createdAt;
    @UpdateTimestamp
    @Column(nullable = false)
    private OffsetDateTime updatedAt;
}