package eu.senla.status_api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContractDto {
    //Container for contract related data
    long startDate;
    long term;
    boolean isSigned;
}
