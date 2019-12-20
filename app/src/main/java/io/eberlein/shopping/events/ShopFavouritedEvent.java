package io.eberlein.shopping.events;

import io.eberlein.shopping.objects.Shop;

public class ShopFavouritedEvent extends Event<Shop> {
    public ShopFavouritedEvent(Shop shop){
        super(shop);
    }
}
