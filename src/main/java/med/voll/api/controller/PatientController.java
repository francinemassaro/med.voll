package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.controller.dto.DataPatientsListDTO;
import med.voll.api.controller.dto.PatientRecordDataDTO;
import med.voll.api.controller.dto.PatientUpdateData;
import med.voll.api.domain.Patient;
import med.voll.api.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public List<DataPatientsListDTO> getAll(){
       return repository.findAll().stream().map(DataPatientsListDTO::new).toList();
    }

    @PutMapping
    @Transactional
    public void update(@RequestBody @Valid PatientUpdateData data){
        var patient = repository.getReferenceById(data.id());
        patient.updateInfo(data);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void delete(@PathVariable Long id){
        var patient = repository.getReferenceById(id);
        patient.delete();
    }
}
