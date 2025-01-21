package dr.center.api.doctor;

import dr.center.api.address.AddressData;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DoctorUpdateData(
        @NotNull
        Long id,

        String name,
        String phone,
        Speciality speciality,

        @Email
        String email,

        @Pattern(regexp = "\\d{4,6}")
        String crm,

        @Valid
        AddressData address) {

    public Doctor toDoctor() {
        return new Doctor(
                name,
                email,
                phone,
                crm,
                speciality,
                address.toAddress()
        );
    }
}
