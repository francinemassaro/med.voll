package med.voll.api.repository;

import med.voll.api.domain.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Page<Patient> findAllByActiveTrue(Pageable pagination);

    @Query(
            """
            SELECT p.active FROM Patients p WHERE p.id = :id
                    """
    )
    Boolean findActiveById(Long id);
}
