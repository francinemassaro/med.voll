package med.voll.api.repository;

import med.voll.api.domain.Doctor;
import med.voll.api.domain.SpecialityEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    Page<Doctor> findAllByActiveTrue(Pageable pagination);

    @Query("""
            SELECT d FROM Doctors WHERE
            d.active = true
            AND
            d.speciality = :speciality
            AND
            d.id NOT IN(
                SELECT a.doctor.id FROM Appointment a WHERE
                a.date = :date
            )
            ORDER BY rand()
            LIMIT 1
            """)
    Doctor chooseRandomDoctorFreeOnTheDate(SpecialityEnum speciality, LocalDateTime date);

    @Query(
            """
            SELECT d.active FROM Doctors d WHERE m.id = :id
                    """
    )
    Boolean findActiveById(Long id);
}
