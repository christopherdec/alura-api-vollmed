package med.voll.api.domain.professional;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.address.AddressDTO;

public record ProfessionalUpdateDTO(
        @NotNull
        Long id,
        String name,
        String phone,
        AddressDTO address) {
}
