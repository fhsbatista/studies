package dr.center.api.doctor;

public record ListDoctorData(
        String name,
        String email,
        String crm,
        Speciality speciality
) {
    public ListDoctorData(Doctor doctor) {
        this(
                doctor.getName(),
                doctor.getEmail(),
                doctor.getCrm(),
                doctor.getSpeciality()
        );
    }
}
