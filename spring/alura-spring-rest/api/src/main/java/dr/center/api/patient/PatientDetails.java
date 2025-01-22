package dr.center.api.patient;

import dr.center.api.address.AddressData;

public record PatientDetails(
        Long id,
        String name,
        String email,
        String cpf,
        String phone,
        AddressData address,
        Boolean active
) {
    public PatientDetails(Patient patient) {
        this(
                patient.getId(),
                patient.getName(),
                patient.getEmail(),
                patient.getCpf(),
                patient.getPhone(),
                AddressData.fromAddress(patient.getAddress()),
                patient.getActive()
        );
    }
}