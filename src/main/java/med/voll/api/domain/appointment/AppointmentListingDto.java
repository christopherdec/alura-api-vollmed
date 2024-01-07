package med.voll.api.domain.appointment;

import java.time.LocalDateTime;

public record AppointmentListingDto(
        Long id,
        Long professionalId,
        Long clientId,
        LocalDateTime date) {

    public AppointmentListingDto(Appointment appointment) {
        this(appointment.getId(),
                appointment.getProfessional().getId(),
                appointment.getClient().getId(),
                appointment.getDate());
    }
}
