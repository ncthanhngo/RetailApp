package org.thanhngo.retailapp.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name ="categories")
@Data //toString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
}
