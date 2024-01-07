package med.voll.api.service.appointment;

import jakarta.validation.ValidationException;
import med.voll.api.domain.appointment.Appointment;
import med.voll.api.domain.appointment.AppointmentCancelDto;
import med.voll.api.domain.appointment.AppointmentScheduleDto;
import med.voll.api.domain.professional.Professional;
import med.voll.api.repository.AppointmentRepository;
import med.voll.api.repository.ClientRepository;
import med.voll.api.repository.ProfessionalRepository;
import med.voll.api.service.appointment.validator.cancel.AppointmentCancelValidator;
import med.voll.api.service.appointment.validator.schedule.AppointmentScheduleValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private ProfessionalRepository professionalRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private List<AppointmentScheduleValidator> scheduleValidators;

    @Autowired
    private List<AppointmentCancelValidator> cancelValidators;

    public Appointment schedule(AppointmentScheduleDto data) {
        scheduleValidators.forEach(v -> v.validate(data));

        var client = clientRepository.getReferenceById(data.clientId());
        var professional = getProfessional(data);

        var appointment = new Appointment(professional, client, data.date());
        return appointmentRepository.save(appointment);
    }

    private Professional getProfessional(AppointmentScheduleDto data) {
        if (data.professionalId() != null) {
            return professionalRepository.getReferenceById(data.professionalId());
        }
        if (data.expertise() == null) {
            throw new ValidationException("A professional or expertise should be informed");
        }
        var professional = professionalRepository.findRandomAvailableByExpertiseAndDate(data.expertise(), data.date());
        if (professional.isEmpty()) {
            throw new RuntimeException("No professional available for the specified expertise and date");
        }
        return professional.get();
    }

    public void cancel(AppointmentCancelDto data) {
        if (!appointmentRepository.existsById(data.id()))
            throw new ValidationException("Appointment not found");

        cancelValidators.forEach(v -> v.validate(data));

        var appointment = appointmentRepository.getReferenceById(data.id());
        appointment.setCancelReason(data.reason());
    }
}
