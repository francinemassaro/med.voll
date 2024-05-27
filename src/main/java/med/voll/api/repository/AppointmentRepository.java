package med.voll.api.repository;

import med.voll.api.domain.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    boolean existsByDoctorIdAndDate(Long doctorId, LocalDateTime date);

    boolean existsByPatientIdAndDateBetween(Long id, LocalDateTime firstHour, LocalDateTime lastHour);
}
