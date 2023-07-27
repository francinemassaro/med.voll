package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.controller.dto.DataDoctorsListDTO;
import med.voll.api.controller.dto.DoctorRecordDataDTO;
import med.voll.api.domain.Doctor;
import med.voll.api.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    private DoctorRepository repository;

    @PostMapping
    @Transactional
    public void create(@RequestBody @Valid DoctorRecordDataDTO data) {
        repository.save(new Doctor(data));
    }

    @GetMapping
    public List<DataDoctorsListDTO> getAll() {
        return repository.findAll().stream().map(DataDoctorsListDTO::new).toList();
    }
}
