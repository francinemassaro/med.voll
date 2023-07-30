package med.voll.api.controller.dto.patient;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import med.voll.api.controller.dto.AddressDataDTO;
import org.hibernate.validator.constraints.br.CPF;

public record PatientRecordDataDTO(

        @NotBlank
        String name,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String phone,
        @NotBlank
        @CPF
        String cpf,
        @NotNull
        @Valid
        AddressDataDTO address
) {
}
