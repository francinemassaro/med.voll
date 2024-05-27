package med.voll.api.service.scheduling.validations;

import med.voll.api.controller.dto.appointment.DataAppointmentSchedulingDto;
import med.voll.api.infra.exception.ValidationException;
import med.voll.api.repository.AppointmentRepository;

public class DoctorWithAnotherAppointmentValidator {

    private AppointmentRepository repository;

    public void validate(DataAppointmentSchedulingDto data){
        var doctorHaveAnotherConsultInTheSameHour = repository.existsByDoctorIdAndDate(data.doctorId(), data.date());

        if(doctorHaveAnotherConsultInTheSameHour) {
            throw new ValidationException("Médico já possui outra consulta agendada nesse mesmo horário");
        }
    }
}
