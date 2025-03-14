package dr.center.api.domain.address;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record AddressData(
        @NotBlank
        String street,

        @NotBlank
        String neighborhood,

        @NotBlank
        @Pattern(regexp = "\\d{8}")
        String zip,

        @NotBlank
        String city,

        @NotBlank
        String state,

        String number,
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

    public static AddressData fromAddress(Address address) {
        return new AddressData(
                address.getStreet(),
                address.getNeighborhood(),
                address.getZip(),
                address.getCity(),
                address.getState(),
                address.getNumber(),
                address.getComplement()
        );
    }
}
