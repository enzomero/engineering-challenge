package eu.senla.status_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Contract {
    //Container for contract related data
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    long startDate;
    long term;
    boolean isSigned;
    @OneToOne
    @JoinColumn(name = "profile_uuid")
    private Profile profile;
}
