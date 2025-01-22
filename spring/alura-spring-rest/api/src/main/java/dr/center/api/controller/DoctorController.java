package dr.center.api.controller;

import dr.center.api.doctor.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorController {
    @Autowired
    private DoctorRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity register(@RequestBody @Valid DoctorRegisterData data) {
        repository.save(data.toDoctor());

        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Page<ListDoctorData>> list(
            @PageableDefault(size = 10, sort = {"name"})
            Pageable pagination
    ) {
        var page = repository.findAllByActiveTrue(pagination).map(ListDoctorData::new);

        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody @Valid DoctorUpdateData data) {
        var doctor = repository.getReferenceById(data.id());
        doctor.updateData(data);

        return ResponseEntity.ok(new DoctorUpdatedData(doctor));
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
