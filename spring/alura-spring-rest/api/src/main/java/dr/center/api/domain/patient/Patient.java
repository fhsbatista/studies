package dr.center.api.domain.patient;

import dr.center.api.domain.address.Address;
import jakarta.persistence.*;

@Table(name = "patients")
@Entity(name = "patient")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Address address;

    private String name;
    private String email;
    private String cpf;
    private String phone;
    private Boolean active;

    public Patient() {}

    public Patient(
            String name,
            String email,
            String cpf,
            String phone,
            Address address

    ) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.cpf = cpf;
        this.phone = phone;
        this.address = address;
        this.active = true;
    }

    public Long getId() {
        return this.id;
    }

    public Address getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getCpf() {
        return cpf;
    }

    public String getPhone() {
        return phone;
    }

    public Boolean getActive() {
        return active;
    }

    public void updateData(PatientUpdateData data) {
        if (data.name() != null) {
            this.name = data.name();
        }

        if (data.email() != null) {
            this.email = data.email();
        }

        if (data.cpf() != null) {
            this.cpf = data.cpf();
        }

        if (data.phone() != null) {
            this.phone = data.phone();
        }

        if (data.address() != null) {
            this.address.updateData(data.address());
        }
    }

    public void deactivate() {
        this.active = false;
    }
}
