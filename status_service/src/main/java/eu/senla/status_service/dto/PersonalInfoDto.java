package eu.senla.status_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonalInfoDto {
    //Container for personal information
    private String firstName;
    private String secondName;
    private Integer age;
}
