package med.voll.api.service.scheduling.validations;

import med.voll.api.controller.dto.appointment.DataAppointmentSchedulingDto;
import med.voll.api.infra.exception.ValidationException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class AdvanceTimeValidator implements ValidatorSchedulingUseCase {

    public void validate(DataAppointmentSchedulingDto data){
        var consultDate = data.date();
        var now = LocalDateTime.now();
        var differenceInMinutes = Duration.between(now, consultDate).toMinutes();
        if(differenceInMinutes < 30){
            throw new ValidationException("Consulta deve ser agendada com antecedência mínima de 30 minutos");
        }
    }
}
