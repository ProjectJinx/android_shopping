package io.eberlein.shopping.objects;

import io.eberlein.shopping.Static;

public class Groceries extends DBListObject<Grocery> {
    public Groceries(){
        super(Static.BOOK_GROCERIES);
    }
}
