package io.eberlein.shopping.objects;

import io.eberlein.shopping.Static;
import io.paperdb.Book;
import io.paperdb.Paper;

public class Shops extends DBListObject<Shop> {
    public Shops(){
        super(Static.BOOK_SHOPS);
    }

    public static Shops get(){
        Shops shops = new Shops();
        Book sb = Paper.book(shops.getBook());
        for(String k : sb.getAllKeys()) shops.add(sb.read(k));
        return shops;
    }
}
