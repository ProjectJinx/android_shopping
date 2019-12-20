package io.eberlein.shopping.objects;

import io.eberlein.shopping.Static;

public class Shops extends DBListObject<Shop> {
    public Shops(){
        super(Static.BOOK_SHOP);
    }

    public Shop getFavourited(){
        for(Shop s : getObjects()){
            if(s.isFavourite()) return s;
        }
        return null;
    }
}
