package io.eberlein.shopping.adapters;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import io.eberlein.shopping.objects.Shop;
import io.eberlein.shopping.objects.Shops;
import io.eberlein.shopping.ui.GroceriesFragment;
import io.eberlein.shopping.ui.ShopFragment;

public class ShopAdapter extends CustomAdapter<Shop> {
    public ShopAdapter(Shops shops, Context ctx, FragmentManager fragmentManager){
        super(shops, ctx, fragmentManager);
    }

    @Override
    Fragment getItemOnClickFragment(Shop object) {
        return new GroceriesFragment(object);
    }

    @Override
    Fragment getItemEditFragment(Shop object) {
        return new ShopFragment(object);
    }
}
