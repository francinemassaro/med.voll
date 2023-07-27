package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.controller.dto.DataDoctorsListDTO;
import med.voll.api.controller.dto.DoctorRecordDataDTO;
import med.voll.api.domain.Doctor;
import med.voll.api.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    public Page<DataDoctorsListDTO> getAll(@PageableDefault(size = 10, sort = {"name"}) Pageable pagination) {
        return repository.findAll(pagination).map(DataDoctorsListDTO::new);
    }
}
