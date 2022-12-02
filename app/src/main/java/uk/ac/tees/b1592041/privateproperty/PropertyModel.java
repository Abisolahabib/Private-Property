package uk.ac.tees.b1592041.privateproperty;

public class PropertyModel {
    String amount;
    String location;
    String image;


    public PropertyModel() {
    }

    public PropertyModel(String amount, String location, String image) {
        this.amount = amount;
        this.location = location;
        this.image = image;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
