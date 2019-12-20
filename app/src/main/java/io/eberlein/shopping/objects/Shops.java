package io.eberlein.shopping.objects;

import io.eberlein.shopping.Static;

public class Shops extends DBListObject<Shop> {
    public Shops(){
        super(Static.BOOK_SHOPS);
    }

    public Shop getFavourited(){
        for(Shop s : getObjects()){
            if(s.isFavourite()) return s;
        }
        return null;
    }
}
