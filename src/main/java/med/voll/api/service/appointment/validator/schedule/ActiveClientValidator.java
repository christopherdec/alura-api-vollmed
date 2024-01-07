package med.voll.api.service.appointment.validator.schedule;

import jakarta.validation.ValidationException;
import med.voll.api.domain.appointment.AppointmentScheduleDto;
import med.voll.api.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ActiveClientValidator implements AppointmentScheduleValidator {

    @Autowired
    private ClientRepository clientRepository;

    public void validate(AppointmentScheduleDto data) {
        Optional<Boolean> active = clientRepository.findActiveById(data.clientId());
        if (active.isEmpty())
            throw new ValidationException("Client not found");
        if (!active.get())
            throw new ValidationException("Client isn't active");
    }
}
