package dr.center.api.doctor;

import dr.center.api.address.Address;
import jakarta.persistence.*;

@Table(name="doctors")
@Entity(name="doctor")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private final Speciality speciality;

    @Embedded
    private final Address address;

    private final String name;
    private final String email;
    private final String crm;

    public Doctor(
            String name,
            String email,
            String crm,
            Speciality speciality,
            Address address
    ) {
        this.name = name;
        this.email = email;
        this.crm = crm;
        this.speciality = speciality;
        this.address = address;
    }
}
