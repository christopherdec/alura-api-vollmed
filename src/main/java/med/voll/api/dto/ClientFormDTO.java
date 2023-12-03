package med.voll.api.dto;

public record ClientFormDTO(String name, String cpf, String email, String phone, AddressDTO address) {
}
