package io.eberlein.shopping.object;

import java.util.ArrayList;
import java.util.List;

public class Groceries {
    private List<Grocery> groceries;

    public Groceries(){
        groceries = new ArrayList<>();
    }

    public List<Grocery> getGroceries() {
        return groceries;
    }

    public void setGroceries(List<Grocery> groceries) {
        this.groceries = groceries;
    }
}
