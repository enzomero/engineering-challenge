package eu.senla.status_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExtraDataDto {
    //container for extra data for statistic
    private String citizenOf;
    private List<String> knownLanguages;
}
