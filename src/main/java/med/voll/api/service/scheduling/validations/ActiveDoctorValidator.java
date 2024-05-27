package med.voll.api.service.scheduling.validations;

import med.voll.api.controller.dto.appointment.DataAppointmentSchedulingDto;
import med.voll.api.infra.exception.ValidationException;
import med.voll.api.repository.DoctorRepository;

public class ActiveDoctorValidator {

    private DoctorRepository repository;

    public void validate(DataAppointmentSchedulingDto data){
        if(data.doctorId() == null){
            return;
        }

        var activeDoctor = repository.findActiveById(data.doctorId());
        if(!activeDoctor){
            throw new ValidationException("Não é possível agendar consulta com médico inativo");
        }
    }
}
