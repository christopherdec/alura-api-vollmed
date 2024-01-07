package med.voll.api.service.appointment.validator.schedule;

import jakarta.validation.ValidationException;
import med.voll.api.domain.appointment.AppointmentScheduleDto;
import med.voll.api.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MaxDailyAppointmentsValidator implements AppointmentScheduleValidator {

    private static final int MAX_DAILY_APPOINTMENTS_PER_CLIENT = 1;

    @Autowired
    private AppointmentRepository appointmentRepository;

    public void validate(AppointmentScheduleDto data) {
        var date = data.date().toLocalDate();
        var startDate = date.atStartOfDay();
        var endDate = startDate.plusDays(1);

        int dailyAppointmentsCnt = appointmentRepository
                .countByClientIdAndDateBetween(data.clientId(), startDate, endDate);

        if (dailyAppointmentsCnt >= MAX_DAILY_APPOINTMENTS_PER_CLIENT)
            throw new ValidationException("Client already has an appointment in the specified day");
    }
}
