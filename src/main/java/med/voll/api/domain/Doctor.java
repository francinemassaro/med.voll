package med.voll.api.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.controller.dto.DoctorRecordDataDTO;
import med.voll.api.controller.dto.DoctorUpdateDataDTO;

@Table(name = "doctors")
@Entity(name = "Doctors")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String crm;
    @Enumerated(EnumType.STRING)
    private SpecialityEnum speciality;

    //Embedded para não precisar criar uma tabela no banco de dados e criar um relacionamento. Ele já entende que
    // o endereço faz parte da tabela de Médico.
    @Embedded
    private Address address;

    public Doctor(DoctorRecordDataDTO data) {
        this.name = data.name();
        this.email = data.email();
        this.phone=data.phone();
        this.crm = data.crm();
        this.address = new Address(data.address());
        this.crm = data.crm();
        this.speciality = data.speciality();
    }

    public void updateInfos(DoctorUpdateDataDTO data) {
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
}
