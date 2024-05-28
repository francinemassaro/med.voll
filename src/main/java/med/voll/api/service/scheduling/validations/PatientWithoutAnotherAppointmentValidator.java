package med.voll.api.service.scheduling.validations;

import med.voll.api.controller.dto.appointment.DataAppointmentSchedulingDto;
import med.voll.api.infra.exception.ValidationException;
import med.voll.api.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PatientWithoutAnotherAppointmentValidator implements ValidatorSchedulingUseCase {

    @Autowired
    private AppointmentRepository repository;

    public void validate(DataAppointmentSchedulingDto data){
        var firstHour = data.date().withHour(7);
        var lastHour = data.date().withHour(18);
        var patientWithAnotherAppointmentInTheSameDay = repository.existsByPatientIdAndDateBetween(data.patientId(),
                firstHour, lastHour);

        if(patientWithAnotherAppointmentInTheSameDay){
            throw new ValidationException("Paciente já possui uma consulta agendada nesse dia");
        }
    }
}
