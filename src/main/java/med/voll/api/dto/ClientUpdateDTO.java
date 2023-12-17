package med.voll.api.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record ClientUpdateDTO(
        @NotNull
        Long id,
        String name,
        @Email
        String email,
        @Pattern(regexp = "^\\d{10,14}$")
        String phone,
        @Pattern(regexp = "^\\d{11}$")
        String cpf,
        @Valid
        AddressDTO address) {
}
