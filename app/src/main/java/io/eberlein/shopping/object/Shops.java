package io.eberlein.shopping.object;

import java.util.ArrayList;
import java.util.List;

import io.eberlein.shopping.Static;
import io.paperdb.Book;
import io.paperdb.Paper;

public class Shops {
    private List<Shop> shops;

    public Shops(){
        shops = new ArrayList<>();
    }

    public int add(Shop shop){
        if(!shops.contains(shop)) {
            shops.add(shop);
            return size() - 1;
        }
        return -1;
    }

    public Shop get(int position){
        return shops.get(position);
    }

    public static Shops get(){
        Book sb = Paper.book(Static.BOOK_SHOP);
        Shops shops = new Shops();
        for(String k : sb.getAllKeys()) shops.add(sb.read(k));
        return shops;
    }

    public int size(){
        return shops.size();
    }
}
