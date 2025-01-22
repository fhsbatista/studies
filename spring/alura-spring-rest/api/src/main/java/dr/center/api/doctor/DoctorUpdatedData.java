package dr.center.api.doctor;

import dr.center.api.address.Address;
import dr.center.api.address.AddressData;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DoctorUpdatedData(
        Long id,
        String name,
        String email,
        String crm,
        String phone,
        Speciality speciality,
        AddressData address) {

    public DoctorUpdatedData(Doctor dr) {
        this(
                dr.getId(),
                dr.getName(),
                dr.getEmail(),
                dr.getCrm(),
                dr.getPhone(),
                dr.getSpeciality(),
                AddressData.fromAddress(dr.getAddress())
        );
    }
}
