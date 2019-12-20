package io.eberlein.shopping.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.eberlein.shopping.R;
import io.eberlein.shopping.adapters.GroceryAdapter;
import io.eberlein.shopping.dialogs.GroceryDialog;
import io.eberlein.shopping.events.GroceryItemChangedEvent;
import io.eberlein.shopping.objects.Grocery;
import io.eberlein.shopping.objects.Shop;


public class GroceriesFragment extends Fragment {
    private Shop shop;
    private GroceryAdapter groceryAdapter;

    @BindView(R.id.recycler_groceries) RecyclerView groceryRecycler;

    @OnClick(R.id.btn_add_grocery)
    void btnAddGroceryClicked(){
        GroceryDialog.showGroceryDialog(getContext(), new Grocery());
    }

    public GroceriesFragment(Shop shop){
        this.shop = shop;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_groceries, container, false);
        ButterKnife.bind(this, v);
        groceryAdapter = new GroceryAdapter(shop.getGroceries(), getContext(), null);
        groceryRecycler.setAdapter(groceryAdapter);
        groceryRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        return v;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGroceryItemChanged(GroceryItemChangedEvent gice){
        shop.addGrocery(gice.getObject());
        groceryAdapter.notifyDataSetChanged();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}
