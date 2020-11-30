package LukaszSz90.simpleapp.domain.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "users_details")
@Data @NoArgsConstructor @AllArgsConstructor
@Builder
@ToString(exclude = "user")
@EqualsAndHashCode(of = )
public class UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String LastName;
    private LocalDate birthDate;

    @OneToOne
    private User user;
    @Column(insertable = false,updatable = false)
    private Long userId;
}