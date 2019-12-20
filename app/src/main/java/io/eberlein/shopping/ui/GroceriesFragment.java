package io.eberlein.shopping.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.eberlein.shopping.R;
import io.eberlein.shopping.objects.Grocery;
import io.eberlein.shopping.objects.Shop;

public class GroceriesFragment extends Fragment {
    private Shop shop;

    // ---------- Dialog Bindings

    class DialogBinder {
        @BindView(R.id.et_multi) EditText multi;
        @BindView(R.id.et_name) EditText name;
        @BindView(R.id.et_price) EditText price;

        public DialogBinder(View v, Grocery grocery){
            ButterKnife.bind(this, v);
            multi.setText(String.valueOf(grocery.getMulti()));
            name.setText(grocery.getName());
            price.setText(String.valueOf(grocery.getPrice()));
        }

        public int getMulti(){
            return Integer.valueOf(multi.getText().toString());
        }

        public double getPrice(){
            return Double.valueOf(price.getText().toString());
        }

        public String getName(){
            return name.getText().toString();
        }
    }

    // ---------- Fragment Bindings

    @BindView(R.id.recycler_groceries) RecyclerView groceryRecycler;

    public void showGroceryDialog(Grocery grocery){
        AlertDialog.Builder adb = new AlertDialog.Builder(getContext());
        View v = LayoutInflater.from(getContext()).inflate(R.layout.dialog_add_grocery, null, false);
        DialogBinder db = new DialogBinder(v, grocery);
        adb.setView(v);
        adb.setTitle(R.string.add_grocery);
        adb.setPositiveButton(getString(R.string.add), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                grocery.setMulti(db.getMulti());
                grocery.setName(db.getName());
                grocery.setPrice(db.getPrice());
                dialog.dismiss();
            }
        });
        adb.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        adb.show();
    }

    @OnClick(R.id.btn_add_grocery)
    void btnAddGroceryClicked(){
        showGroceryDialog(new Grocery());
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
