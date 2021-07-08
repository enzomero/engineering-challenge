package eu.senla.status_gate.dto;

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
    @NonNull
    private String citizenOf;
    private List<String> knownLanguages;
}
