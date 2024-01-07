package med.voll.api.service.appointment.validator.schedule;

import med.voll.api.domain.appointment.AppointmentScheduleDto;

public interface AppointmentScheduleValidator {

    void validate(AppointmentScheduleDto data);
}
