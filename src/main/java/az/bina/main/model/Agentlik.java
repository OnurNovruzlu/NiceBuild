package az.bina.main.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Agentlik {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer agent_id;
    private String agent_name;
    private String description;
    private String contact;
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private String image;
    @OneToMany(mappedBy = "agent_id")
    private List<Elan> elanList;

}
