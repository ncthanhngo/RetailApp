package org.thanhngo.retailapp.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name ="users")
@Data //toString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class User extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "fullname", length = 300)
    private String fullName;
    @Column(name = "phone_number", length = 10, nullable = false)
    private String phoneNumber;
    @Column(name = "address", length = 200)
    private String address;
    @Column(name = "password", length = 200,nullable = false)
    private String password;
    private boolean active;
    @Column(name = "date_of_birth")
    private Date birthOfBirth;
    @Column(name = "facebook_account_id")
    private int facebookAccountId;
    @Column(name = "google_account_id")
    private int googleAccountId;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private  org.thanhngo.retailapp.models.Role role;
}
