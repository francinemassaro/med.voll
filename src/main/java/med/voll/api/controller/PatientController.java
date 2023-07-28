package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.controller.dto.PatientRecordDataDTO;
import med.voll.api.domain.Patient;
import med.voll.api.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientRepository repository;

    @PostMapping
    @Transactional
    public void create(@RequestBody @Valid PatientRecordDataDTO data){
        repository.save(new Patient(data));
    }

}
