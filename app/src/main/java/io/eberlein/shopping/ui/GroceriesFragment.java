package io.eberlein.shopping.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.eberlein.shopping.R;
import io.eberlein.shopping.object.Shop;

public class GroceriesFragment extends Fragment {
    private Shop shop;

    @OnClick(R.id.btn_add_grocery)
    void btnAddGroceryClicked(){
        // todo dialog stuff
    }

    public GroceriesFragment(Shop shop){
        this.shop = shop;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_groceries, container, false);
        ButterKnife.bind(this, v);
        return v;
    }
}
