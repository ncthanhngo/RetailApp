package org.thanhngo.retailapp.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@MappedSuperclass
@Data //toString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseEntity {
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
