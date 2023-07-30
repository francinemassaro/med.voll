package med.voll.api.controller.dto.patient;

import jakarta.validation.constraints.NotNull;
import med.voll.api.controller.dto.AddressDataDTO;

public record PatientUpdateData(
        @NotNull
        Long id,
        String name,
        String phone,
        AddressDataDTO address
) {
}
