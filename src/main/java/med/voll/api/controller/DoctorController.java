package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.controller.dto.doctor.DataDoctorDetail;
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
    public ResponseEntity<Page<DataDoctorsListDTO>> getAll(@PageableDefault(size = 10, sort = {"name"}) Pageable pagination) {
        var page = repository.findAllByActiveTrue(pagination).map(DataDoctorsListDTO::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody @Valid DoctorUpdateDataDTO data){
        var doctor = repository.getReferenceById(data.id());
        doctor.updateInfos(data);

        return ResponseEntity.ok(new DataDoctorDetail(doctor));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id){
        var doctor = repository.getReferenceById(id);
        doctor.delete();

        return ResponseEntity.noContent().build();
    }
}
