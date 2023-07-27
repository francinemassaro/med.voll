package med.voll.api.controller.dto;

import med.voll.api.domain.SpecialityEnum;

public record DoctorRecordDataDTO(
        String name,
        String email,
        String crm,
        SpecialityEnum speciality,
        AddressDataDTO address
) {
}
