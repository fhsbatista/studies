package dr.center.api.doctor;

import dr.center.api.address.Address;
import jakarta.persistence.*;

@Table(name = "doctors")
@Entity(name = "doctor")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Speciality speciality;

    @Embedded
    private Address address;

    private String name;
    private String email;
    private String phone;
    private String crm;

    public Doctor(
            String name,
            String email,
            String phone,
            String crm,
            Speciality speciality,
            Address address
    ) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.crm = crm;
        this.speciality = speciality;
        this.address = address;
    }

    public Doctor() {
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public Speciality getSpeciality() {
        return this.speciality;
    }

    public String getCrm() {
        return this.crm;
    }

    public void updateData(DoctorUpdateData data) {
        if (data.name() != null) {
            this.name = data.name();
        }

        if (data.email() != null) {
            this.email = data.email();
        }

        if (data.phone() != null) {
            this.phone = data.phone();
        }

        if (data.crm() != null) {
            this.crm = data.crm();
        }

        if (data.speciality() != null) {
            this.speciality = data.speciality();
        }

        if (data.address() != null) {
            this.address.updateData(data.address());
        }
    }
}
