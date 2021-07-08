package eu.senla.status_api.model;

import eu.senla.status_api.dto.UserState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "uuid", unique = true, nullable = false)
    private String uuid;
    private String userState;
    @OneToOne(mappedBy = "profile", cascade = CascadeType.ALL)
    private PersonalInfo user;

    @OneToOne(mappedBy = "profile", cascade = CascadeType.ALL)
    private Contract contract;

    @OneToOne(mappedBy = "profile", cascade = CascadeType.ALL)
    private ExtraData extraData;

    @OneToOne(mappedBy = "profile", cascade = CascadeType.ALL)
    private PersonalInfo approver;
}
