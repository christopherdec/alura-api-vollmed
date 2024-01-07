package med.voll.api.repository;

import med.voll.api.domain.appointment.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    int countByClientIdAndDateBetween(Long clientId, LocalDateTime startDate, LocalDateTime endDate);

    boolean existsByProfessionalIdAndDateBetweenAndCancelReasonIsNull(Long professionalId,
                                                                      LocalDateTime startDate, LocalDateTime endDate);
}
