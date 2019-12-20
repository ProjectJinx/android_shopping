package io.eberlein.shopping;

import android.os.Bundle;

import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.alibaba.fastjson.JSON;
import com.blankj.utilcode.util.FragmentUtils;
import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Menu;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.SubscriberExceptionEvent;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.eberlein.shopping.adapters.ShopAdapter;
import io.eberlein.shopping.events.ShopAdapterItemChangedEvent;
import io.eberlein.shopping.events.ShopFavouritedEvent;
import io.eberlein.shopping.events.ShopSelectedEvent;
import io.eberlein.shopping.objects.Shop;
import io.eberlein.shopping.objects.Shops;
import io.eberlein.shopping.ui.GroceriesFragment;
import io.eberlein.shopping.ui.ShopFragment;
import io.paperdb.Book;
import io.paperdb.Paper;


public class MainActivity extends AppCompatActivity {
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.drawer_layout) DrawerLayout drawer;
    @BindView(R.id.nav_view) NavigationView navigationView;
    @BindView(R.id.recycler_shop) RecyclerView shopRecycler;

    @OnClick(R.id.btn_add_shop)
    void btnAddShopClicked(){
        drawer.closeDrawers();
        FragmentUtils.replace(getSupportFragmentManager(), new ShopFragment(), R.id.nav_host_fragment);
    }

    private Shops shops;
    private ShopAdapter shopAdapter;

    private AppBarConfiguration mAppBarConfiguration;

    private void testDB(){
        for(String b : new String[]{
                Static.BOOK_DBOBJECT,
                Static.BOOK_GROCERY,
                Static.BOOK_SHOP
        }) {
            for(String k : Paper.book(b).getAllKeys()){
                Log.d(b, k);
                Log.d(k, JSON.toJSONString(Paper.book(b).read(k)));
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Paper.init(this);
        testDB();
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home
        ).setDrawerLayout(drawer).build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        shops = new Shops();
        shopAdapter = new ShopAdapter(shops, this, getSupportFragmentManager());
        shopRecycler.setLayoutManager(new LinearLayoutManager(this));
        shopRecycler.setAdapter(shopAdapter);

        if(shops.size() == 0) drawer.openDrawer(GravityCompat.START);
        else {
            Shop fs = shops.getFavourited();
            if(fs == null) drawer.openDrawer(GravityCompat.START);
            else FragmentUtils.replace(getSupportFragmentManager(), new GroceriesFragment(fs), R.id.nav_host_fragment);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSubscriberException(SubscriberExceptionEvent event){
        event.throwable.printStackTrace();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onShopSelected(ShopSelectedEvent sse){
        drawer.closeDrawers();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onShopAdapterItemChanged(ShopAdapterItemChangedEvent saic){
        shops.add(saic.getObject());
        shopAdapter.notifyDataSetChanged();
        drawer.openDrawer(GravityCompat.START);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onShopItemFavourited(ShopFavouritedEvent sf){
        Shop fs = sf.getObject();
        for(Shop shop : shops.getObjects()){
            if(!shop.getUuid().equals(fs.getUuid()) && shop.isFavourite()) shop.setFavourite(false);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
