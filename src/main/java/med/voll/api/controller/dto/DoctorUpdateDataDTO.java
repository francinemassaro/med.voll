package med.voll.api.controller.dto;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.Doctor;

public record DoctorUpdateDataDTO(
        @NotNull
        Long id,
        String name,
        String phone,
        AddressDataDTO address
) {
}
