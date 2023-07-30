package med.voll.api.controller.dto.doctor;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import med.voll.api.controller.dto.AddressDataDTO;
import med.voll.api.domain.SpecialityEnum;

public record DoctorRecordDataDTO(

        @NotBlank
        String name,

        @NotBlank
        @Email
        String email,

        @NotBlank
        String phone,

        @NotBlank
        @Pattern(regexp = "\\d{4,6}")
        String crm,

        @NotNull //não é notblank porque não é uma string. NotBlank é só para string
        SpecialityEnum speciality,

        @NotNull
        @Valid //para validar os beans validations também do Address DTO
        AddressDataDTO address
) {
}
