package dr.center.api.controller;

import dr.center.api.doctor.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorController {
    @Autowired
    private DoctorRepository repository;

    @PostMapping
    @Transactional
    public void register(@RequestBody @Valid DoctorRegisterData data) {
        repository.save(data.toDoctor());
    }

    @GetMapping
    public Page<ListDoctorData> list(
            @PageableDefault(size = 10, sort = {"name"})
            Pageable pagination
    ) {
        return repository.findAll(pagination).map(ListDoctorData::new);
    }

    @PutMapping
    @Transactional
    public void update(@RequestBody @Valid DoctorUpdateData data) {
        var doctor = repository.getReferenceById(data.id());
        doctor.updateData(data);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }

    @PutMapping("/{id}/deactivate")
    @Transactional
    public void deactivate(@PathVariable Long id) {
        var doctor = repository.getReferenceById(id);
        doctor.deactivate();
    }
}
