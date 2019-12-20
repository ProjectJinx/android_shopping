package io.eberlein.shopping.viewholders;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.FragmentUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;
import butterknife.OnTouch;
import io.eberlein.shopping.R;
import io.eberlein.shopping.exceptions.NotImplementedException;
import io.eberlein.shopping.interfaces.ViewHolderInterface;
import io.eberlein.shopping.objects.DBObject;


public class ViewHolder<T extends DBObject>
        extends RecyclerView.ViewHolder
        implements ViewHolderInterface<T> {
    private T dbObject;
    private boolean extraMenuOpen = false;

    @Override
    public void onBtnDeletedClicked(T object) {
        throw new NotImplementedException("onBtnDeletedClicked");
    }

    @Override
    public void onBtnEditClicked(T object) {
        throw new NotImplementedException("onBtnEditClicked");
    }

    @Override
    public void onClicked(T object) {
        throw new NotImplementedException("onClicked");
    }

    @Override
    public void onSwipeDown(T object) {

    }

    @Override
    public void onSwipeLeft(T object) {

    }

    @Override
    public void onSwipeRight(T object) {

    }

    @Override
    public void onSwipeUp(T object) {

    }

    public @BindView(R.id.viewHolder) RelativeLayout viewHolder;
    public @BindView(R.id.tv_name) TextView name;
    // hidden
    public @BindView(R.id.cb_fav) CheckBox favourite;
    public @BindView(R.id.btn_delete) Button delete;
    public @BindView(R.id.btn_edit) Button edit;

    @OnClick(R.id.btn_delete)
    void btnDeleteClicked(){
        closeExtraMenu();
        onBtnDeletedClicked(dbObject);
    }

    @OnClick(R.id.btn_edit)
    void btnEditClicked(){
        onBtnEditClicked(dbObject);
    }

    @OnClick
    void onClick(){
        if(!extraMenuOpen) onClicked(dbObject);
        else closeExtraMenu();
    }

    @OnLongClick
    void onLongClick(){
        if(!extraMenuOpen) openExtraMenu();
        else closeExtraMenu();
    }

    public void openExtraMenu(){
        extraMenuOpen = true;
        name.setVisibility(View.GONE);
        favourite.setVisibility(View.VISIBLE);
        delete.setVisibility(View.VISIBLE);
        edit.setVisibility(View.VISIBLE);
    }

    public void closeExtraMenu(){
        extraMenuOpen = false;
        name.setVisibility(View.VISIBLE);
        favourite.setVisibility(View.GONE);
        delete.setVisibility(View.GONE);
        edit.setVisibility(View.GONE);
    }

    public ViewHolder(View v){
        super(v);
        ButterKnife.bind(this, v);
    }

    @Override
    public void set(T dbObject){
        Log.d("ViewHolder", "set");
        this.dbObject = dbObject;
    }

    public void setColor(int c){
        viewHolder.setBackgroundColor(c);
    }
}
