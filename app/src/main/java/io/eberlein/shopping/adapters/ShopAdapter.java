package io.eberlein.shopping.adapters;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.blankj.utilcode.util.FragmentUtils;

import io.eberlein.shopping.R;
import io.eberlein.shopping.objects.Shop;
import io.eberlein.shopping.objects.Shops;
import io.eberlein.shopping.ui.GroceriesFragment;
import io.eberlein.shopping.ui.ShopFragment;
import io.eberlein.shopping.viewholders.ViewHolder;

public class ShopAdapter extends CustomAdapter<Shop, Shops, ShopAdapter.VH> {
    public ShopAdapter(Shops shops, Context ctx, FragmentManager fragmentManager){
        super(shops, ctx, fragmentManager);
        Log.d("ShopAdapter", "init");
    }

    class VH extends ViewHolder<Shop> {
        public VH(View v){
            super(v);
        }

        @Override
        public void onClicked(Shop object) {
            FragmentUtils.replace(getFragmentManager(), getItemOnClickFragment(object), R.id.nav_host_fragment);
        }

        @Override
        public void onBtnEditClicked(Shop object) {
            FragmentUtils.replace(getFragmentManager(), getItemEditFragment(object), R.id.nav_host_fragment);
        }

        @Override
        public void onBtnDeletedClicked(Shop object) {
            remove(object);
            notifyDataSetChanged();
        }

        @Override
        public void set(Shop dbObject) {
            Log.d("ShopAdapter.set", dbObject.getName());
            name.setText(dbObject.getName());
            super.set(dbObject);
        }
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("ShopAdapter", "onCreateViewHolder");
        return new VH(getInflatedView(parent));
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
