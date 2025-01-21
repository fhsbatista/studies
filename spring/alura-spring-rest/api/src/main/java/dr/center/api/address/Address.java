package dr.center.api.address;

import jakarta.persistence.Embeddable;

@Embeddable
public class Address {
    private String number;
    private String street;
    private String neighborhood;
    private String zip;
    private String city;
    private String state;
    private String complement;

    public Address() {
    }


    public Address(
            String number,
            String street,
            String neighborhood,
            String zip,
            String city,
            String state,
            String complement
    ) {
        this.number = number;
        this.street = street;
        this.neighborhood = neighborhood;
        this.zip = zip;
        this.city = city;
        this.state = state;
        this.complement = complement;
    }
}
