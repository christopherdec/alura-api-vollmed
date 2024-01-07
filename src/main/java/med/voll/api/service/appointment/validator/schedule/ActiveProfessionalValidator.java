package med.voll.api.service.appointment.validator.schedule;

import jakarta.validation.ValidationException;
import med.voll.api.domain.appointment.AppointmentScheduleDto;
import med.voll.api.repository.ProfessionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ActiveProfessionalValidator implements AppointmentScheduleValidator {

    @Autowired
    private ProfessionalRepository professionalRepository;

    public void validate(AppointmentScheduleDto data) {
        // professional is optional
        if (data.professionalId() == null)
            return;

        Optional<Boolean> active = professionalRepository.findActiveById(data.professionalId());

        if (active.isEmpty())
            throw new ValidationException("Professional not found");

        if (!active.get())
            throw new ValidationException("It isn't possible to schedule an appointment with an inactive professional");
    }
}
