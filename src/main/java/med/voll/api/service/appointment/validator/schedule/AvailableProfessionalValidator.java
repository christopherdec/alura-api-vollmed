package med.voll.api.service.appointment.validator.schedule;

import jakarta.validation.ValidationException;
import med.voll.api.domain.appointment.AppointmentScheduleDto;
import med.voll.api.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AvailableProfessionalValidator implements AppointmentScheduleValidator {

    @Autowired
    private AppointmentRepository appointmentRepository;

    public void validate(AppointmentScheduleDto data) {
        var professionalId = data.professionalId();
        var startDate = data.date();
        var endDate = startDate.plusHours(1);
        if (appointmentRepository
                .existsByProfessionalIdAndDateBetweenAndCancelReasonIsNull(professionalId, startDate, endDate)) {
            throw new ValidationException("The selected professional is already occupied in the specified date");
        }
    }
}

