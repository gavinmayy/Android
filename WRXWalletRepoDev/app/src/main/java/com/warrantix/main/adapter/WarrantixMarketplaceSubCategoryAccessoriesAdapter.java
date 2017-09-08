package com.warrantix.main.adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.warrantix.main.R;
import com.warrantix.main.activities.BaseActivity;
import com.warrantix.main.activities.WalletBrandAccessoriesEshop;
import com.warrantix.main.activities.WalletMarketplaceSubCategory;
import com.warrantix.main.activities.registration.ScanCodeActivity;
import com.warrantix.main.common.bus.BusProvider;
import com.warrantix.main.common.rest.model.Product;
import com.warrantix.main.common.rest.model.RelatedProduct;
import com.warrantix.main.manager.BackendManager;

import java.util.ArrayList;
import java.util.List;

public class WarrantixMarketplaceSubCategoryAccessoriesAdapter extends BaseAdapter {

    private final Activity context;
    private ProgressBar progressBar;
    private Handler handler = new Handler();
    private int progressStatus = 0;
    private boolean isAccessory = false;
    List<RelatedProduct> mRelatedProducts= new ArrayList<>();
    List<Product> mProducts = new ArrayList<>();

    private Button serviceBTN;
    private Button shopBTN;

    public WarrantixMarketplaceSubCategoryAccessoriesAdapter(Activity context, List<RelatedProduct> usedProducts, List<Product> products) {

        this.context = context;
        this.mRelatedProducts = usedProducts;
        this.mProducts = products;
    }

    @Override
    public int getCount() {
        return mRelatedProducts.size();
    }

    @Override
    public Object getItem(int position) {
        return mRelatedProducts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(final int position, View view, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View rowView;

        if (view == null)
            rowView = inflater.inflate(R.layout.listview_marketplace_subcategory, null, true);
        else
            rowView = view;

        serviceBTN = (Button) rowView.findViewById(R.id.products_service);
        serviceBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, ScanCodeActivity.class);
                if (context instanceof BaseActivity)
                    ((BaseActivity)context).startActivity(i, true);
                else
                    context.startActivity(i);
            }
        });
        shopBTN = (Button) rowView.findViewById(R.id.shopBTN);
        shopBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, WalletBrandAccessoriesEshop.class);
                if (context instanceof WalletMarketplaceSubCategory)
                    i.putExtra("title", ((WalletMarketplaceSubCategory)context).getSubCategoryTitle());
                    i.putExtra("id", mRelatedProducts.get(position).getId());
                if (context instanceof BaseActivity)
                    ((BaseActivity)context).startActivity(i, true);
                else
                    context.startActivity(i);
            }
        });

        final LinearLayout socialButton = (LinearLayout) rowView.findViewById(R.id.socialButton);
        socialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int[] locationInWindow = new int[2];
                socialButton.getLocationInWindow(locationInWindow);

                if (context instanceof WalletMarketplaceSubCategory)
                    ((WalletMarketplaceSubCategory) context).showSocial1Bar(locationInWindow[0], locationInWindow[1], socialButton.getWidth(), socialButton.getHeight());
            }
        });

        TextView nameText = (TextView) rowView.findViewById(R.id.name);
        TextView placeText = (TextView) rowView.findViewById(R.id.place);
        TextView priceText = (TextView) rowView.findViewById(R.id.price);
        final ImageView imageView = (ImageView) rowView.findViewById(R.id.products_image);

        RelatedProduct relatedProduct = mRelatedProducts.get(position);

        nameText.setText(mProducts.get(position).getName());
        placeText.setText(mRelatedProducts.get(position).getModel());
        priceText.setText(String.valueOf(mRelatedProducts.get(position).getMsrp()));

        Picasso.with(this.context.getApplicationContext())
                .load(mRelatedProducts.get(position).getImageThmb())
                .error(R.drawable.image_holder)
                .placeholder(R.drawable.image_holder)
                .into(imageView);

        return rowView;
    }

}