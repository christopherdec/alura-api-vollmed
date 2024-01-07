package med.voll.api.service.appointment.validator.cancel;

import med.voll.api.domain.appointment.AppointmentCancelDto;

public interface AppointmentCancelValidator {

    void validate(AppointmentCancelDto data);

}
