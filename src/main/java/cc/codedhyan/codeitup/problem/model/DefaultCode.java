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
public class DefaultCode {
    @Id
    @GeneratedValue
    private Integer id;

    private Integer languageId;

    private String problemId;

    private String code;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "problem", insertable = false, updatable = false)
    private Problem problem;

    @ManyToOne
    @JoinColumn(name = "language", insertable = false, updatable = false)
    private Language language;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private OffsetDateTime createdAt;
    @UpdateTimestamp
    @Column(nullable = false)
    private OffsetDateTime updatedAt;
}
