package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.controller.dto.doctor.DataDoctorDetailDto;
import med.voll.api.controller.dto.patient.DataPatientDetailDto;
import med.voll.api.controller.dto.patient.DataPatientsListDTO;
import med.voll.api.controller.dto.patient.PatientRecordDataDTO;
import med.voll.api.controller.dto.patient.PatientUpdateData;
import med.voll.api.domain.Patient;
import med.voll.api.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity create(@RequestBody @Valid PatientRecordDataDTO data, UriComponentsBuilder uriBuilder) {
        var patient = new Patient(data);
        repository.save(patient);

        var uri = uriBuilder.path("patients/{id}").buildAndExpand(patient.getId()).toUri();

        return ResponseEntity.created(uri).body(new DataPatientDetailDto(patient));
    }

    @GetMapping
    public ResponseEntity<Page<DataPatientsListDTO>> getAll(@PageableDefault(size = 10, sort = {"name"}) Pageable pagination) {
        var page = repository.findAllByActiveTrue(pagination).map(DataPatientsListDTO::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody @Valid PatientUpdateData data) {
        var patient = repository.getReferenceById(data.id());
        patient.updateInfo(data);

        return ResponseEntity.ok(new DataPatientDetailDto(patient));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        var patient = repository.getReferenceById(id);
        patient.delete();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity detail(@PathVariable Long id){
        var patient = repository.getReferenceById(id);

        return ResponseEntity.ok(new DataPatientDetailDto(patient));
    }
}
