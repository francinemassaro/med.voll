package med.voll.api.controller.dto.appointment;

import med.voll.api.domain.Appointment;

import java.time.LocalDateTime;

public record DataDetailAppointmentDto(
        Long id,
        Long doctorId,
        Long patientId,
        LocalDateTime date
) {
    public DataDetailAppointmentDto(Appointment appointment) {
        this(appointment.getId(), appointment.getDoctor().getId(), appointment.getPatient().getId(), appointment.getDate());
    }
}
