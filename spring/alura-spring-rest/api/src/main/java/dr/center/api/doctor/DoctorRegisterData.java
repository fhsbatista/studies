package dr.center.api.doctor;

import dr.center.api.address.AddressData;

public record DoctorRegisterData(
        String name,
        String email,
        String crm,
        Speciality speciality,
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
