package med.voll.api.service.appointment.validator.cancel;

import jakarta.validation.ValidationException;
import med.voll.api.domain.appointment.AppointmentCancelDto;
import med.voll.api.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CancelAdvanceValidator implements AppointmentCancelValidator {

    private static final int APPOINTMENT_CANCEL_MIN_HOURS_ADVANCE = 24;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public void validate(AppointmentCancelDto data) {
        var appointment = appointmentRepository.getReferenceById(data.id());
        if (appointment.getDate().plusHours(APPOINTMENT_CANCEL_MIN_HOURS_ADVANCE).isAfter(LocalDateTime.now()))
            throw new ValidationException(
                    "An appointment cannot be canceled less than " +
                            APPOINTMENT_CANCEL_MIN_HOURS_ADVANCE +
                    " hours before its date");
    }
}
