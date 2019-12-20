package io.eberlein.shopping.objects;


import io.eberlein.shopping.Static;

public class Shop extends DBObject {
    private boolean favourite;
    private String name;
    private String description;
    private Groceries groceries;

    public Shop(){
        super(Static.BOOK_SHOP);
        this.favourite = false;
        this.name = "";
        this.description = "";
        this.groceries = new Groceries(getUuid());
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Groceries getGroceries() {
        return groceries;
    }

    public boolean isFavourite() {
        return favourite;
    }

    public void setName(String name) {
        this.name = name;
        save();
    }

    public void setDescription(String description) {
        this.description = description;
        save();
    }

    public void setGroceries(Groceries groceries) {
        this.groceries = groceries;
        save();
    }

    public void setFavourite(boolean favourite) {
        this.favourite = favourite;
        save();
    }

    public int addGrocery(Grocery grocery){
        int r = groceries.add(grocery);
        save();
        return r;
    }

    public Grocery newGrocery(){
        return new Grocery(this);
    }
}
