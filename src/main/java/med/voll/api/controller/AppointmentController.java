package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.voll.api.domain.appointment.AppointmentCancelDto;
import med.voll.api.domain.appointment.AppointmentListingDto;
import med.voll.api.domain.appointment.AppointmentScheduleDto;
import med.voll.api.repository.AppointmentRepository;
import med.voll.api.service.appointment.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/appointment")
@SecurityRequirement(name = "bearer-key")
public class AppointmentController {

    @Autowired
    AppointmentService appointmentService;

    @Autowired
    AppointmentRepository appointmentRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<AppointmentListingDto> schedule(@RequestBody @Valid AppointmentScheduleDto data) {
        var appointment = appointmentService.schedule(data);
        return ResponseEntity.ok(new AppointmentListingDto(appointment));
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity<Void> cancel(@RequestBody @Valid AppointmentCancelDto data) {
        appointmentService.cancel(data);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<AppointmentListingDto>> list(@PageableDefault(size = 20, sort = {"date"}) Pageable pageable) {
        var page = appointmentRepository.findAll(pageable).map(AppointmentListingDto::new);
        return ResponseEntity.ok(page);
    }
}
