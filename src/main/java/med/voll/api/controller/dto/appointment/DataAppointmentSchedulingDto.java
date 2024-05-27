package med.voll.api.controller.dto.appointment;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.SpecialityEnum;

import java.time.LocalDateTime;

public record DataAppointmentSchedulingDto(
        Long doctorId,
        @NotNull
        Long patientId,
        @NotNull
        @Future
        LocalDateTime date,
        SpecialityEnum speciality
) {
}
