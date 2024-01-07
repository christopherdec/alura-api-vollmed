package med.voll.api.service.appointment.validator.schedule;

import jakarta.validation.ValidationException;
import med.voll.api.domain.appointment.AppointmentScheduleDto;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class BusinessHoursValidator implements AppointmentScheduleValidator {

    private static final int CLINIC_OPENING_HOUR = 7;

    private static final int CLINIC_CLOSING_HOUR = 19;

    public void validate(AppointmentScheduleDto data) {
        var date = data.date();

        var sunday = date.getDayOfWeek() == DayOfWeek.SUNDAY;

        var beforeClinicOpen = date.getHour() < CLINIC_OPENING_HOUR;

        var afterClinicClose = date.getHour() > CLINIC_CLOSING_HOUR - 1;

        if (sunday || beforeClinicOpen || afterClinicClose)
            throw new ValidationException("Appointment date is outside clinic's business hours");
    }
}
