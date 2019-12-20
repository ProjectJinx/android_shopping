package io.eberlein.shopping.dialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.eberlein.shopping.R;
import io.eberlein.shopping.objects.Grocery;

import static com.blankj.utilcode.util.StringUtils.getString;


class DialogBinder {
    @BindView(R.id.et_multi) EditText multi;
    @BindView(R.id.et_name) EditText name;
    @BindView(R.id.et_price) EditText price;

    DialogBinder(View v, Grocery grocery){
        ButterKnife.bind(this, v);
        multi.setText(String.valueOf(grocery.getMulti()));
        name.setText(grocery.getName());
        price.setText(String.valueOf(grocery.getPrice()));
    }

    int getMulti(){
        return Integer.valueOf(multi.getText().toString());
    }

    double getPrice(){
        return Double.valueOf(price.getText().toString());
    }

    public String getName(){
        return name.getText().toString();
    }
}

public class GroceryDialog {


    public static void showGroceryDialog(Context ctx, Grocery grocery){
        AlertDialog.Builder adb = new AlertDialog.Builder(ctx);
        View v = LayoutInflater.from(ctx).inflate(R.layout.dialog_add_grocery, null, false);
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
}
