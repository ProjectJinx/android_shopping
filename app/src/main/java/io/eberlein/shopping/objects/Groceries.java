package io.eberlein.shopping.objects;

import io.eberlein.shopping.Static;

public class Groceries extends DBListObject<Grocery> {
    public Groceries(Shop shop){
        super(Static.BOOK_GROCERY + ":" + shop.getUuid());
    }
}
