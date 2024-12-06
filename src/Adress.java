public class Adress {
    private String city;
    private String street;
    private String house;
    private String floor;

    public Adress(String city, String street, String house, String floor) {
        this.city = city;
        this.street = street;
        this.house = house;
        this.floor = floor;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getHouse() {
        return house;
    }

    public String getFloor() {
        return floor;
    }

    @Override
    public String toString() {
        return city + ", " + street + ", " + house + ", " + floor;
    }
}
