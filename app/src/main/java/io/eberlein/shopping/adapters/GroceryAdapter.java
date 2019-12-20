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
import io.eberlein.shopping.dialogs.GroceryDialog;
import io.eberlein.shopping.objects.Groceries;
import io.eberlein.shopping.objects.Grocery;
import io.eberlein.shopping.viewholders.ViewHolder;

public class GroceryAdapter extends CustomAdapter<Grocery, Groceries, GroceryAdapter.VH> {
    public GroceryAdapter(Groceries groceries, Context ctx, FragmentManager fragmentManager){
        super(groceries, ctx, fragmentManager);
        Log.d("GroceryAdapter", "init");
    }

    class VH extends ViewHolder<Grocery> {
        public VH(View v){
            super(v);
        }

        @Override
        public void onClicked(Grocery object) {
            // does nothing
        }

        @Override
        public void onBtnEditClicked(Grocery object) {
            GroceryDialog.showGroceryDialog(getContext(), object);
        }

        @Override
        public void onBtnDeletedClicked(Grocery object) {
            remove(object);
            notifyDataSetChanged();
        }

        @Override
        public void set(Grocery dbObject) {
            super.set(dbObject);
            Log.d("GroceryAdapter.set", (dbObject == null) ? "" : dbObject.getName());
            name.setText((dbObject == null) ? "" : dbObject.getName());
        }

        @Override
        public void openExtraMenu() {
            super.openExtraMenu();
            favourite.setVisibility(View.GONE);
        }
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VH(getInflatedView(parent));
    }

    @Override
    Fragment getItemEditFragment(Grocery object) {
        GroceryDialog.showGroceryDialog(getContext(), object);
        return null;
    }
}
