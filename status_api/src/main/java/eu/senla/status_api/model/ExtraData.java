package eu.senla.status_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ExtraData {
    //container for extra data for statistic
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String citizenOf;
    private String knownLanguages;
    @OneToOne
    @JoinColumn(name = "profile_uuid")
    private Profile profile;
}
