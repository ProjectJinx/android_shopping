package io.eberlein.shopping.ui;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.eberlein.shopping.R;
import io.eberlein.shopping.event.ShopAdapterItemChanged;
import io.eberlein.shopping.object.Shop;

public class ShopFragment extends Fragment {
    private Shop shop;

    // todo small image view in front of name
    @BindView(R.id.et_name) EditText name;
    @BindView(R.id.linear_images) LinearLayout images;

    @OnClick(R.id.btn_save)
    void btnSaveClicked(){
        saveShop();
    }

    public ShopFragment(){
        this.shop = new Shop();
    }

    public ShopFragment(Shop shop){
        this.shop = shop;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add_shop, container, false);
        ButterKnife.bind(this, v);
        populateShopVariables();
        populateImages();
        return v;
    }

    private void populateShopVariables(){
        name.setText(shop.getName());
    }

    private void populateImages(){ // todo
        ImageView iv = new ImageView(getContext());
        iv.setId(0);
        iv.setPadding(2, 2, 2, 2);
        iv.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.baseline_local_mall_black_48));
        iv.setScaleType(ImageView.ScaleType.FIT_XY);
        images.addView(iv);

    }

    private void saveShop(){
        shop.setName(name.getText().toString());
        shop.save();
        EventBus.getDefault().post(new ShopAdapterItemChanged());
    }

    /*
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
     */

    @Override
    public void onDestroy() {
        saveShop();
        super.onDestroy();
    }
}
