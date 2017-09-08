package com.warrantix.main.adapter;

import android.app.Activity;
import android.content.Intent;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.warrantix.main.R;
import com.squareup.picasso.Picasso;
import com.warrantix.main.activities.BaseActivity;
import com.warrantix.main.activities.MainActivity;
import com.warrantix.main.activities.WalletBrandProducts;
import com.warrantix.main.activities.WalletBrandProductsDetail;
import com.warrantix.main.activities.WalletMarketplaceSubCategory;
import com.warrantix.main.activities.productdetail.ProductsDetailScheduleService;
import com.warrantix.main.common.rest.model.Product;
import com.warrantix.main.common.rest.model.UsedProduct;

import java.util.ArrayList;
import java.util.List;

public class WalletProductList extends BaseAdapter {

    private final Activity context;

    private ProgressBar progressBar;
    private Handler handler = new Handler();
    private int progressStatus = 0;

    List<Product> mProducts= new ArrayList<>();

    public WalletProductList(Activity context, List<Product> products) {

        this.context = context;
        this.mProducts = products;
    }

    @Override
    public int getCount() {
        return mProducts.size();
    }

    @Override
    public Object getItem(int position) {
        return mProducts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View view, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View rowView;

        if (view == null)
            rowView = inflater.inflate(R.layout.listview_wallet_product, null, true);
        else
            rowView = view;

        TextView nameText = (TextView) rowView.findViewById(R.id.name);
        TextView nameText1 = (TextView) rowView.findViewById(R.id.name1);
        TextView nameText0 = (TextView) rowView.findViewById(R.id.name0);
        TextView nameTextGone = (TextView) rowView.findViewById(R.id.name1GONE);
        TextView daysText = (TextView) rowView.findViewById(R.id.days);
        final ImageView imageView = (ImageView) rowView.findViewById(R.id.products_image);

        nameText.setText(mProducts.get(position).getName());
        nameText0.setText(mProducts.get(position).getClass_());
        nameText1.setText("Rs. "+mProducts.get(position).getMsrp());
        daysText.setText(mProducts.get(position).getName());

        if (nameText0.getText().toString()!=""){
            nameText0.setVisibility(View.VISIBLE);
            nameTextGone.setVisibility(View.GONE);
        }else {
            nameText0.setVisibility(View.GONE);
            nameTextGone.setVisibility(View.VISIBLE);
        }

        Picasso.with(this.context.getApplicationContext())
                .load(mProducts.get(position).getImageThmb())
                .error(R.drawable.image_holder)
                .placeholder(R.drawable.image_holder)
                .into(imageView);

        final RelativeLayout socialButton = (RelativeLayout) rowView.findViewById(R.id.products_social_button);
        socialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int[] locationInWindow = new int[2];
                socialButton.getLocationInWindow(locationInWindow);

                if (context instanceof WalletBrandProducts)
                    ((WalletBrandProducts) context).showSocial1Bar(locationInWindow[0], locationInWindow[1], socialButton.getWidth(), socialButton.getHeight());
                else if (context instanceof MainActivity)
                    ((MainActivity) context).showSocial1Bar(locationInWindow[0], locationInWindow[1], socialButton.getWidth(), socialButton.getHeight());
            }
        });

        Button serviceBtn =  (Button) rowView.findViewById(R.id.products_service_button);
        serviceBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //...
                Intent mIntent = new Intent(context,ProductsDetailScheduleService.class);
                if (context instanceof BaseActivity)
                    ((BaseActivity) context).startActivity(mIntent, true);
                else
                    context.startActivity(mIntent);
            }
        });

        return rowView;
    }
}