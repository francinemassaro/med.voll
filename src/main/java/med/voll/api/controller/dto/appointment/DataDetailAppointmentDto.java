package med.voll.api.controller.dto.appointment;

import java.time.LocalDateTime;

public record DataDetailAppointmentDto(
        Long id,
        Long doctorId,
        Long patientId,
        LocalDateTime date
) {
}
