package io.eberlein.shopping.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.FragmentUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;
import io.eberlein.shopping.R;
import io.eberlein.shopping.object.Shop;
import io.eberlein.shopping.object.Shops;
import io.eberlein.shopping.ui.GroceriesFragment;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ViewHolder> {
    private Shops shops;
    private Context ctx;
    private FragmentManager fragmentManager;

    public ShopAdapter(FragmentManager fragmentManager, Context ctx, Shops shops){
        this.fragmentManager = fragmentManager;
        this.shops = shops;
        this.ctx = ctx;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private boolean extraMenuOpen = false;
        private Shop shop;

        @BindView(R.id.vh_shop) RelativeLayout vh;
        @BindView(R.id.tv_name) TextView name;

        @OnClick
        void onClick(){
            if(!extraMenuOpen) FragmentUtils.replace(fragmentManager, new GroceriesFragment(shop), R.id.nav_host_fragment);
            else closeExtraMenu();
        }

        @OnLongClick
        void onLongClick(){
            if(!extraMenuOpen) openExtraMenu();
            else closeExtraMenu();
        }

        private void openExtraMenu(){
            extraMenuOpen = true;
        }

        private void closeExtraMenu(){
            extraMenuOpen = false;
        }

        ViewHolder(View v){
            super(v);
            ButterKnife.bind(this, v);
        }

        public void setShop(Shop shop){
            this.shop = shop;
            name.setText(shop.getName());
        }

        public void setColor(int c){
            vh.setBackgroundColor(c);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(ctx).inflate(R.layout.viewholder_shop, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setShop(shops.get(position));
        if((position % 2) > 0) holder.setColor(Color.GRAY);
    }

    @Override
    public int getItemCount() {
        return shops.size();
    }

    public void add(Shop shop){
        int p = shops.add(shop);
        if(p > 0) notifyItemChanged(p);
    }

    public void set(Shops shops){
        this.shops = shops;
        notifyDataSetChanged();
    }
}
