package med.voll.api.service.scheduling;

import med.voll.api.controller.dto.appointment.DataAppointmentSchedulingDto;
import med.voll.api.controller.dto.appointment.DataDetailAppointmentDto;
import med.voll.api.domain.Appointment;
import med.voll.api.domain.Doctor;
import med.voll.api.infra.exception.ValidationException;
import med.voll.api.repository.AppointmentRepository;
import med.voll.api.repository.DoctorRepository;
import med.voll.api.repository.PatientRepository;
import med.voll.api.service.scheduling.validations.ValidatorSchedulingUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentSchedulingService {

    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private List<ValidatorSchedulingUseCase> validators;

    public AppointmentSchedulingService() {
    }

    public DataDetailAppointmentDto schedule(DataAppointmentSchedulingDto data) {
        if (!patientRepository.existsById(data.patientId())) {
            throw new ValidationException("Id do paciente informado não existe!");
        }
        if (data.doctorId() != null && !doctorRepository.existsById(data.doctorId())) {
            throw new ValidationException("Id do medico informado não existe!");
        }

        validators.forEach(v -> v.validate(data));

        var patient = patientRepository.findById(data.patientId()).get();
        var doctor = chooseDoctor(data);
        if (doctor == null){
            throw new ValidationException("Não existe médico disponível nessa data!");
        }
        var appointment = new Appointment(null, doctor, patient, data.date());
        appointmentRepository.save(appointment);

        return new DataDetailAppointmentDto(appointment);
    }

    private Doctor chooseDoctor(DataAppointmentSchedulingDto data) {
        if (data.doctorId() != null) {
            return doctorRepository.getReferenceById(data.doctorId());
        }

        if (data.speciality() == null) {
            throw new ValidationException("Especialidade é obrigatória, quando o médico não for escolhido!");
        }

        return doctorRepository.chooseRandomDoctorFreeOnTheDate(data.speciality(), data.date());
    }
}
