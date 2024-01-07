package med.voll.api.service.appointment.validator.schedule;

import jakarta.validation.ValidationException;
import med.voll.api.domain.appointment.AppointmentScheduleDto;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class TimeAdvanceValidator implements AppointmentScheduleValidator {

    public void validate(AppointmentScheduleDto data) {
        var date = data.date();
        var now = LocalDateTime.now();
        var minutesDiff = Duration.between(now, date).toMinutes();

        if (minutesDiff < 30) {
            throw new ValidationException("An appointment should be scheduled at least 30 minutes in advance");
        }
    }
}
