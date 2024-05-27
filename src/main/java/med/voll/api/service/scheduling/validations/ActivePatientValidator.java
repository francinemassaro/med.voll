package med.voll.api.service.scheduling.validations;

import med.voll.api.controller.dto.appointment.DataAppointmentSchedulingDto;
import med.voll.api.infra.exception.ValidationException;
import med.voll.api.repository.PatientRepository;

public class ActivePatientValidator {

    private PatientRepository repository;

    public void validate(DataAppointmentSchedulingDto data){
        var activePatient = repository.findActiveById(data.patientId());

        if(!activePatient){
            throw new ValidationException("Não é possível agendar consulta para pacientes inativos");
        }
    }

}
