package ru.dsstaroverov.mailApp.to;

public class EmailTo extends BaseTo {
    private String address;

    public EmailTo() {
    }

    public EmailTo(Integer id, String address) {
        super(id);
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "EmailTo{" +
                "address='" + address + '\'' +
                '}';
    }
}
