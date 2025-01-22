package dr.center.api.doctor;

import dr.center.api.address.AddressData;

public record DoctorDetails(
        Long id,
        String name,
        String email,
        String crm,
        String phone,
        Speciality speciality,
        AddressData address) {

    public DoctorDetails(Doctor dr) {
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
