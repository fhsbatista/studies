package dr.center.api.doctor;

public record Address(
        String street,
        String neighborhood,
        String zip,
        String city,
        String state,
) {
}
