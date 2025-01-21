package dr.center.api.address;

public record AddressData(
        String number,
        String street,
        String neighborhood,
        String zip,
        String city,
        String state,
        String complement
) {
    public Address toAddress() {
        return new Address(
                number,
                street,
                neighborhood,
                zip,
                city,
                state,
                complement);
    }
}
