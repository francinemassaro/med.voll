package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.controller.dto.doctor.DataDoctorDetailDto;
import med.voll.api.controller.dto.doctor.DataDoctorsListDTO;
import med.voll.api.controller.dto.doctor.DoctorRecordDataDTO;
import med.voll.api.controller.dto.doctor.DoctorUpdateDataDTO;
import med.voll.api.domain.Doctor;
import med.voll.api.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    private DoctorRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity create(@RequestBody @Valid DoctorRecordDataDTO data, UriComponentsBuilder uriBuilder) {
        var doctor = new Doctor(data);

        repository.save(doctor);

        var uri = uriBuilder.path("doctors/{id}").buildAndExpand(doctor.getId()).toUri();

        return ResponseEntity.created(uri).body(new DataDoctorDetailDto(doctor));
    }

    @GetMapping
    public ResponseEntity<Page<DataDoctorsListDTO>> getAll(@PageableDefault(size = 10, sort = {"name"}) Pageable pagination) {
        var page = repository.findAllByActiveTrue(pagination).map(DataDoctorsListDTO::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody @Valid DoctorUpdateDataDTO data){
        var doctor = repository.getReferenceById(data.id());
        doctor.updateInfos(data);

        return ResponseEntity.ok(new DataDoctorDetailDto(doctor));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id){
        var doctor = repository.getReferenceById(id);
        doctor.delete();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity detail(@PathVariable Long id){
        var doctor = repository.getReferenceById(id);

        return ResponseEntity.ok(new DataDoctorDetailDto(doctor));
    }
}
