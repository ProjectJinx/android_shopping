package io.eberlein.shopping.object;

public class Grocery {
    private String name;
    private Double price;

    public Grocery(){
        this.name = "";
        this.price = 0.0;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
