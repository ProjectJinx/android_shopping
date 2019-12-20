package io.eberlein.shopping.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import io.eberlein.shopping.R;
import io.eberlein.shopping.exceptions.NotImplementedException;
import io.eberlein.shopping.objects.DBListObject;
import io.eberlein.shopping.objects.DBObject;
import io.eberlein.shopping.viewholders.ViewHolder;


public class CustomAdapter<T extends DBObject, VH extends ViewHolder<T>> extends RecyclerView.Adapter<VH> {
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

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return (VH) new ViewHolder<T>(LayoutInflater.from(ctx).inflate(R.layout.viewholder_custom, parent, false));
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
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

    public void remove(T object){
        dbListObject.remove(object);
    }

    public FragmentManager getFragmentManager() {
        return fragmentManager;
    }
}
