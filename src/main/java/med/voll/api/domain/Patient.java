package med.voll.api.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.controller.dto.patient.PatientRecordDataDTO;
import med.voll.api.controller.dto.patient.PatientUpdateData;

@Table(name = "patients")
@Entity(name = "Patients")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String cpf;
    @Embedded
    private Address address;
    private boolean active = true;

    public Patient(PatientRecordDataDTO data) {
        this.name = data.name();
        this.email = data.email();
        this.phone = data.phone();
        this.cpf = data.cpf();
        this.address = new Address(data.address());
    }

    public void updateInfo(PatientUpdateData data) {
        if(data.name() != null) {
            this.name = data.name();
        }

        if(data.phone() != null) {
            this.phone = data.phone();
        }

        if(data.address() != null) {
            this.address.updateAddress(data.address());
        }
    }

    public void delete() {
        this.active=false;
    }
}
