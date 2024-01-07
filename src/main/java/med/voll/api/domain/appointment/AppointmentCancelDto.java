package med.voll.api.domain.appointment;

import jakarta.validation.constraints.NotNull;

public record AppointmentCancelDto(
        @NotNull
        Long id,
        @NotNull
        CancelReason reason) {
}
