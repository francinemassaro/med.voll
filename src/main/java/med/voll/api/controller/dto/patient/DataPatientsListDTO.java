package med.voll.api.controller.dto.patient;

import med.voll.api.domain.Patient;

public record DataPatientsListDTO(
        Long id,
        String name,
        String email,
        String cpf
) {
    public DataPatientsListDTO(Patient patient){
        this(patient.getId(), patient.getName(), patient.getEmail(), patient.getCpf());
    }

}
