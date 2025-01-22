package dr.center.api.controller;

import dr.center.api.domain.doctor.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/doctors")
public class DoctorController {
    @Autowired
    private DoctorRepository repository;

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable Long id) {
        var doctor = repository.getReferenceById(id);
        return ResponseEntity.ok().body(new DoctorDetails(doctor));
    }

    @PostMapping
    @Transactional
    public ResponseEntity register(
            @RequestBody @Valid DoctorRegisterData data,
            UriComponentsBuilder uriBuilder) {
        var doctor = repository.save(data.toDoctor());

        var uri = uriBuilder.path("/doctors/{id}").buildAndExpand(doctor.getId()).toUri();

        return ResponseEntity.created(uri).body(new DoctorDetails(doctor));
    }

    @GetMapping
    public ResponseEntity<Page<ListDoctorData>> list(
            @PageableDefault(size = 10, sort = {"name"}) Pageable pagination
    ) {
        var page = repository.findAllByActiveTrue(pagination).map(ListDoctorData::new);

        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody @Valid DoctorUpdateData data) {
        var doctor = repository.getReferenceById(data.id());
        doctor.updateData(data);

        return ResponseEntity.ok(new DoctorDetails(doctor));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id) {
        repository.deleteById(id);

        return ResponseEntity.noContent().build();

    }

    @PutMapping("/{id}/deactivate")
    @Transactional
    public ResponseEntity deactivate(@PathVariable Long id) {
        var doctor = repository.getReferenceById(id);
        doctor.deactivate();

        return ResponseEntity.noContent().build();
    }

}
