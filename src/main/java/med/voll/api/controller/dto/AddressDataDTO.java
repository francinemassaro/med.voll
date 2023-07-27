package med.voll.api.controller.dto;

public record AddressDataDTO(
        String street,
        String district,
        String cep,
        String city,
        String uf,
        String complement,
        String number
) {
}
