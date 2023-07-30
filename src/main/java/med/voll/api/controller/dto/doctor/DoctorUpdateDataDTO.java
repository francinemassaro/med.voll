package med.voll.api.controller.dto.doctor;

import jakarta.validation.constraints.NotNull;
import med.voll.api.controller.dto.AddressDataDTO;

public record DoctorUpdateDataDTO(
        @NotNull
        Long id,
        String name,
        String phone,
        AddressDataDTO address
) {
}
