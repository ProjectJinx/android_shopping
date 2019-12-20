package io.eberlein.shopping.events;


import io.eberlein.shopping.objects.Grocery;

public class GroceryItemChangedEvent extends Event<Grocery> {
    public GroceryItemChangedEvent(Grocery grocery){
        super(grocery);
    }
}
