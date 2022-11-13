import java.io.Serializable;
public class Address implements Serializable{

    private String city;
    private String street;
    private int n_house;

    public Address(String city, String street, int n_house) {
        this.city = city;
        this.street = street;
        this.n_house = n_house;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public int getN_house() {
        return n_house;
    }

    @Override
    public String toString() {
        return   city + ' ' +
                 street + ' ' +
                 n_house + ' ';
    }
}
