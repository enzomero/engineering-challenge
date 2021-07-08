package eu.senla.status_gate.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChangeRequestDto {
    private ExtraDataDto extraDataDto;
    private ContractDto contractDto;
}
