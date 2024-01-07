package med.voll.api.domain.appointment;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.expertise.Expertise;

import java.time.LocalDateTime;

public record AppointmentScheduleDto(
        Long professionalId,
        @NotNull
        @JsonAlias("client_id") // just some @JsonAlias examples
        Long clientId,
        @NotNull
        @Future
        @JsonAlias({ "data", "startDate" })
//        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime date,
        Expertise expertise) {
}
