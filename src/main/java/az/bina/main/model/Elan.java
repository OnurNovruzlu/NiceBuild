package az.bina.main.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Elan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer price;
    private String location;
    private String contact;
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private String image;
    @JoinColumn(name = "agent_id",referencedColumnName = "agent_id")
    @ManyToOne
    private Agentlik agent_id;
    @JoinColumn(name = "category_id",referencedColumnName = "category_id")
    @ManyToOne
    private Category category_id;
}
