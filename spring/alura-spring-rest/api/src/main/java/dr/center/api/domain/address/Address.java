package dr.center.api.domain.address;

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

    public void updateData(AddressData data) {
        if (data.number() != null) {
            this.number = data.number();
        }

        if (data.street() != null) {
            this.street = data.street();
        }

        if (data.neighborhood() != null) {
            this.neighborhood = data.neighborhood();
        }

        if (data.zip() != null) {
            this.zip = data.zip();
        }

        if (data.city() != null) {
            this.city = data.city();
        }

        if (data.state() != null) {
            this.state = data.state();
        }

        if (data.complement() != null) {
            this.complement = data.complement();
        }
    }

    public String getNumber() {
        return number;
    }

    public String getStreet() {
        return street;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public String getZip() {
        return zip;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getComplement() {
        return complement;
    }
}
