package eu.senla.status_api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonalInfoDto {
    //Container for personal information
    @NotBlank(message = "firstName cannot be blank")
    private String firstName;
    @NotBlank(message = "secondName cannot be blank")
    private String secondName;
    @NotNull(message = "age cannot be empty")
    private Integer age;
}
