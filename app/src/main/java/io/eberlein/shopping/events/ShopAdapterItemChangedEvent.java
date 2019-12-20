package io.eberlein.shopping.events;

import io.eberlein.shopping.objects.Shop;

public class ShopAdapterItemChangedEvent extends Event<Shop> {
    public ShopAdapterItemChangedEvent(Shop shop){
        super(shop);
    }
}
