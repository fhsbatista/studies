package dr.center.api.controller;

import dr.center.api.patient.PatientDetails;
import dr.center.api.patient.PatientRegisterData;
import dr.center.api.patient.PatientRepository;
import dr.center.api.patient.PatientUpdateData;
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
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientRepository repository;


    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable Long id) {
        var patient = repository.getReferenceById(id);

        return ResponseEntity.ok().body(new PatientDetails(patient));
    }

    @PostMapping
    @Transactional
    public ResponseEntity register(
            @RequestBody @Valid PatientRegisterData data,
            UriComponentsBuilder uriBuilder) {
        var patient = repository.save(data.toPatient());

        var uri = uriBuilder.path("/patients/{id}").buildAndExpand(patient.getId()).toUri();

        return ResponseEntity.created(uri).body(new PatientDetails(patient));
    }

    @GetMapping
    public ResponseEntity<Page<PatientDetails>> list(
            @PageableDefault(size = 10, sort = {"name"}) Pageable pagination
    ) {
        var page = repository.findAll(pagination).map(PatientDetails::new);

        return ResponseEntity.ok(page);
    }

    @PutMapping()
    @Transactional
    public ResponseEntity update(@RequestBody PatientUpdateData data) {
        var patient = repository.getReferenceById(data.id());
        patient.updateData(data);

        return ResponseEntity.ok(new PatientDetails(patient));
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
        var patient = repository.getReferenceById(id);
        patient.deactivate();

        return ResponseEntity.noContent().build();
    }
}