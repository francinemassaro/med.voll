package med.voll.api.service.scheduling.validations;

import med.voll.api.controller.dto.appointment.DataAppointmentSchedulingDto;

public interface ValidatorSchedulingUseCase {
    void validate(DataAppointmentSchedulingDto data);
}
