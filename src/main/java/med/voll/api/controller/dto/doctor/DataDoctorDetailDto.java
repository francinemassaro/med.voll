package med.voll.api.controller.dto.doctor;

import med.voll.api.domain.Address;
import med.voll.api.domain.Doctor;
import med.voll.api.domain.SpecialityEnum;

public record DataDoctorDetailDto(
        Long id,
        String name,
        String email,
        String phone,
        String crm,
        SpecialityEnum speciality,
        Address address
) {
    public DataDoctorDetailDto(Doctor doctor) {
        this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getPhone(), doctor.getCrm(),
                doctor.getSpeciality(),doctor.getAddress());
    }
}
