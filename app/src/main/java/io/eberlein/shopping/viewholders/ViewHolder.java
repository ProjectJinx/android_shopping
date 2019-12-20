package io.eberlein.shopping.viewholders;

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
import io.eberlein.shopping.R;
import io.eberlein.shopping.exceptions.NotImplementedException;
import io.eberlein.shopping.interfaces.ViewHolderInterface;
import io.eberlein.shopping.objects.DBObject;


public class ViewHolder<T extends DBObject> extends RecyclerView.ViewHolder implements ViewHolderInterface<T> {
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

    public @BindView(R.id.viewHolder) RelativeLayout viewHolder;
    public @BindView(R.id.tv_name) TextView name;
    // hidden
    public @BindView(R.id.cb_fav) CheckBox favourite;
    public @BindView(R.id.btn_delete) Button delete;
    public @BindView(R.id.btn_edit) Button edit;

    @OnClick(R.id.btn_delete)
    void btnDeleteClicked(){
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

    private void openExtraMenu(){
        extraMenuOpen = true;
        name.setVisibility(View.GONE);
        favourite.setVisibility(View.VISIBLE);
        delete.setVisibility(View.VISIBLE);
        edit.setVisibility(View.VISIBLE);
    }

    private void closeExtraMenu(){
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
        this.dbObject = dbObject;
    }

    public void setColor(int c){
        viewHolder.setBackgroundColor(c);
    }
}
