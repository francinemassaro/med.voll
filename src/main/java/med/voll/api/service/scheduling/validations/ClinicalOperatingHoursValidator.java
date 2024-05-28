package med.voll.api.service.scheduling.validations;

import med.voll.api.controller.dto.appointment.DataAppointmentSchedulingDto;
import med.voll.api.infra.exception.ValidationException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ClinicalOperatingHoursValidator implements ValidatorSchedulingUseCase {

    public void validate(DataAppointmentSchedulingDto data) {
        var appointmentDate = data.date();
        var sunday = appointmentDate.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var beforeOpening = appointmentDate.getHour() < 7;
        var afterClosing = appointmentDate.getHour() > 18;
        if(sunday || beforeOpening || afterClosing){
            throw new ValidationException("Consulta fora do horário de funcionamento da clínica");
        }
    }
}
