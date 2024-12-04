package vn.yenthan.core.util;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.yenthan.core.config.ThreadContext;

import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
public class EntityBase implements Serializable {
    private static final int ID_MAX_LENGTH = 36;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CREATED_DATE", updatable = false, nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime createdDate;

    @Column(name = "MODIFIED_DATE", columnDefinition = "TIMESTAMP")
    private LocalDateTime modifiedDate;

    @Column(name = "CREATED_BY")
    private String createdBy;

    @Column(name = "MODIFIED_BY")
    private String modifiedBy;

    @PrePersist
    public void onInsert() {
        LocalDateTime dateNow = LocalDateTime.now();
        if (ThreadContext.getCurrentUser() != null) {
            this.createdBy = ThreadContext.getCurrentUser().getUsername();
            this.modifiedBy = ThreadContext.getCurrentUser().getUsername();
        }
        this.createdDate = dateNow;
        this.modifiedDate = dateNow;
    }

    @PreUpdate
    public void onUpdate() {
        if (ThreadContext.getCurrentUser() != null) {
            this.modifiedBy = ThreadContext.getCurrentUser().getUsername();
        }
        this.modifiedDate = LocalDateTime.now();
    }

}
