package io.eberlein.shopping.objects;

import io.eberlein.shopping.Static;

public class Groceries extends DBListObject<Grocery> {
    public Groceries(String name){
        super(Static.BOOK_GROCERY + ":" + name.replace(" ", ""));
    }
}
