package dr.center.api.doctor;

import dr.center.api.address.AddressData;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DoctorRegisterData(
        @NotBlank
        String name,

        @NotBlank
        @Email
        String email,


        @NotBlank
        @Pattern(regexp = "\\d{4,6}")
        String crm,

        @NotNull
        Speciality speciality,

        @NotNull
        @Valid
        AddressData address) {

    public Doctor toDoctor() {
        return new Doctor(
                name,
                email,
                crm,
                speciality,
                address.toAddress()
        );
    }
}
