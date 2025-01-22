package dr.center.api.controller;

import dr.center.api.patient.PatientDetails;
import dr.center.api.patient.PatientRegisterData;
import dr.center.api.patient.PatientRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
}