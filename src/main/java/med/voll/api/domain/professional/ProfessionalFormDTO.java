package med.voll.api.domain.professional;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.expertise.Expertise;
import med.voll.api.domain.address.AddressDTO;

public record ProfessionalFormDTO(
        @NotBlank
        String name,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String phone,
        @NotBlank
        @Pattern(regexp = "\\d{4,6}")
        String crm,
        @NotNull
        Expertise expertise,
        @NotNull
        @Valid
        AddressDTO address) {
}
