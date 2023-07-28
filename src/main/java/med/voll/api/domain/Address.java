package med.voll.api.domain;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.controller.dto.AddressDataDTO;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private String street;
    private String district;
    private String cep;
    private String city;
    private String uf;
    private String complement;
    private String number;

    public Address(AddressDataDTO data) {
        this.street = data.street();
        this.district = data.district();
        this.cep = data.cep();
        this.city = data.city();
        this.uf = data.uf();
        this.complement = data.complement();
        this.number = data.number();
    }

    public void updateAddress(AddressDataDTO address) {
        if(address.street() != null) {
            this.street = address.street();
        }
        if(address.district() != null) {
            this.district = address.district();
        }
        if(address.cep() != null) {
            this.cep = address.cep();
        }
        if(address.city() != null) {
            this.city = address.city();
        }
        if(address.uf() != null) {
            this.uf = address.uf();
        }
        if(address.complement() != null) {
            this.complement = address.number();
        }
        if(address.number() != null) {
            this.complement = address.number();
        }
    }
}
