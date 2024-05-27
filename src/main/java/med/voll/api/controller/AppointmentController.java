package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.controller.dto.appointment.DataAppointmentSchedulingDto;
import med.voll.api.controller.dto.appointment.DataDetailAppointmentDto;
import med.voll.api.service.scheduling.AppointmentSchedulingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("appointments")
public class AppointmentController {

    @Autowired
    private AppointmentSchedulingService appointmentService;

    @PostMapping
    @Transactional
    public ResponseEntity schedule(@RequestBody @Valid DataAppointmentSchedulingDto data) {
        System.out.println(data);
        appointmentService.schedule(data);
        return ResponseEntity.ok(new DataDetailAppointmentDto(null, null, null, null));
    }
}

