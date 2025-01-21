package dr.center.api.controller;

import dr.center.api.doctor.Doctor;
import dr.center.api.doctor.DoctorRegisterData;
import dr.center.api.doctor.DoctorRepository;
import dr.center.api.doctor.ListDoctorData;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Page<ListDoctorData> list(Pageable pagination) {
        return repository.findAll(pagination).map(ListDoctorData::new);
    }
}
