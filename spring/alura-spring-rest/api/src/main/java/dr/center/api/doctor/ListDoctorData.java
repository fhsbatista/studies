package dr.center.api.doctor;

public record ListDoctorData(
        Long id,
        String name,
        String email,
        String crm,
        Speciality speciality
) {
    public ListDoctorData(Doctor doctor) {
        this(
                doctor.getId(),
                doctor.getName(),
                doctor.getEmail(),
                doctor.getCrm(),
                doctor.getSpeciality()
        );
    }
}
