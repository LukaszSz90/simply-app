package LukaszSz90.simpleapp.domain.model;

import javax.persistence.*;

@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    @Column()
    private String url;
    private String description;

    @ManyToOne
    private User user;
}
