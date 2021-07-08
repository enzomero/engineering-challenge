package eu.senla.status_api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PersonalInfo {
    //Container for personal information
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String firstName;
    private String secondName;
    private Integer age;
    @OneToOne
    @JoinColumn(name = "profile_uuid")
    private Profile profile;

}
