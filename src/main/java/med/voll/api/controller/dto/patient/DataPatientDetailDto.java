package med.voll.api.controller.dto.patient;

import med.voll.api.domain.Address;
import med.voll.api.domain.Patient;

public record DataPatientDetailDto(
        Long id,
        String name,
        String email,
        String phone,
        String cpf,
        Address address) {

    public DataPatientDetailDto(Patient patient){
        this(patient.getId(), patient.getName(), patient.getEmail(), patient.getPhone(), patient.getCpf(),
                patient.getAddress());
    }
}
