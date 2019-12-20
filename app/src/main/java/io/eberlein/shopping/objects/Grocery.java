package io.eberlein.shopping.objects;

import io.eberlein.shopping.Static;

public class Grocery extends DBObject {
    private String name;
    private double price;
    private int multi;

    public Grocery(){
        super(Static.BOOK_GROCERY);
        this.name = "";
        this.price = 0.0;
        this.multi = 1;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getMulti() {
        return multi;
    }

    public void setName(String name) {
        this.name = name;
        save();
    }

    public void setPrice(double price) {
        this.price = price;
        save();
    }

    public void setMulti(int multi) {
        this.multi = multi;
        save();
    }
}
