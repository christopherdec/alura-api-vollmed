package med.voll.api.service.appointment.validator.cancel;

import jakarta.validation.ValidationException;
import med.voll.api.domain.appointment.AppointmentCancelDto;
import med.voll.api.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AlreadyCanceledValidator implements AppointmentCancelValidator {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public void validate(AppointmentCancelDto data) {
        var appointment = appointmentRepository.getReferenceById(data.id());
        if (appointment.getCancelReason() != null)
            throw new ValidationException("Appointment is already canceled");
    }
}
