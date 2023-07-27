package med.voll.api.controller.dto;

import med.voll.api.domain.Doctor;
import med.voll.api.domain.SpecialityEnum;

public record DataDoctorsListDTO(
        String name,
        String email,
        String crm,
        SpecialityEnum speciality
) {

    public DataDoctorsListDTO(Doctor doctor) {
        this(doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getSpeciality());
    }
}
