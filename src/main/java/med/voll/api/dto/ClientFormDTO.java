package med.voll.api.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record ClientFormDTO(
        @NotBlank
        String name,
        @Pattern(regexp = "^\\d{11}$")
        String cpf,
        @NotNull
        @Email
        String email,
        @NotNull
        @Pattern(regexp = "^\\d{10,14}$")
        String phone,
        @NotNull
        @Valid
        AddressDTO address) {
}
