package io.eberlein.shopping.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.FragmentUtils;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;
import io.eberlein.shopping.R;
import io.eberlein.shopping.objects.DBListObject;


class NotImplementedException extends UnsupportedOperationException {
    public NotImplementedException(String methodName){
        super(String.format("Method '%s' should be overwritten. Do not call this method directly.", methodName));
    }
}


public class CustomAdapter<T> extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    private DBListObject<T> dbListObject;
    private Context ctx;
    private FragmentManager fragmentManager;

    Fragment getItemOnClickFragment(T object){
        throw new NotImplementedException("getItemOnClickFragment");
    }

    Fragment getItemEditFragment(T object){
        throw new NotImplementedException("getItemEditFragment");
    }

    public CustomAdapter(DBListObject<T> dbListObject, Context ctx, FragmentManager fragmentManager){
        this.dbListObject = dbListObject;
        this.ctx = ctx;
        this.fragmentManager = fragmentManager;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private T dbObject;
        private boolean extraMenuOpen = false;

        @BindView(R.id.viewHolder) RelativeLayout viewHolder;
        @BindView(R.id.tv_name) TextView name;
        // hidden
        @BindView(R.id.cb_fav) CheckBox favourite;
        @BindView(R.id.btn_delete) Button delete;

        @OnClick(R.id.btn_delete)
        void btnDeleteClicked(){
            dbListObject.remove(dbObject);
            notifyDataSetChanged();
        }

        @OnClick(R.id.btn_edit)
        void btnEditClicked(){
            FragmentUtils.replace(fragmentManager, getItemEditFragment(dbObject), R.id.nav_host_fragment);
        }

        @OnClick
        void onClick(){
            if(!extraMenuOpen) FragmentUtils.replace(fragmentManager, getItemOnClickFragment(dbObject), R.id.nav_host_fragment);
            else closeExtraMenu();
        }

        @OnLongClick
        void onLongClick(){
            if(!extraMenuOpen) openExtraMenu();
            else closeExtraMenu();
        }

        private void openExtraMenu(){
            extraMenuOpen = true;
            favourite.setVisibility(View.VISIBLE);
            delete.setVisibility(View.VISIBLE);
        }

        private void closeExtraMenu(){
            extraMenuOpen = false;
            favourite.setVisibility(View.GONE);
            delete.setVisibility(View.GONE);
        }

        public ViewHolder(View v){
            super(v);
            ButterKnife.bind(this, v);
        }

        public void set(T dbObject){
            this.dbObject = dbObject;
        }

        public void setColor(int c){
            viewHolder.setBackgroundColor(c);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(ctx).inflate(R.layout.viewholder_custom, parent, false));
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.ViewHolder holder, int position) {
        holder.set(dbListObject.get(position));
    }

    @Override
    public int getItemCount() {
        return dbListObject.size();
    }

    public void add(T object){
        int p = dbListObject.add(object);
        if(p > 0) notifyItemChanged(p);
    }

    public void set(DBListObject<T> dbListObject){
        this.dbListObject = dbListObject;
        notifyDataSetChanged();
    }
}
