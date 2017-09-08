package com.warrantix.main.adapter;

import android.app.Activity;

import android.content.Intent;
import android.location.Location;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.warrantix.main.R;
import com.squareup.picasso.Picasso;
import com.warrantix.main.WarrantixApplication;
import com.warrantix.main.activities.BaseActivity;
import com.warrantix.main.activities.MapsActivity;
import com.warrantix.main.activities.brandlist.WalletBrandListLocalDeals;
import com.warrantix.main.activities.productdetail.ProductsDetailServiceContacts;
import com.warrantix.main.common.permission.MarshMallowPermission;
import com.warrantix.main.common.rest.WarrantixWebService;
import com.warrantix.main.common.rest.model.Deal;
import com.warrantix.main.common.rest.model.Dealer;
import com.warrantix.main.common.rest.model.Product;
import com.warrantix.main.manager.BackendManager;

import java.util.ArrayList;
import java.util.List;

public class WalletBrandListLocalDealList extends BaseAdapter {

    private final Activity context;
    private List<Dealer> mDealers = new ArrayList<>();

    public WalletBrandListLocalDealList(Activity context, List<Dealer> dealers) {
        this.context = context;
        mDealers = dealers;
    }

    @Override
    public int getCount() {
        return mDealers.size();
    }

    @Override
    public Object getItem(int position) {
        return mDealers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View view, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View rowView;

        if (view != null)
            rowView = view;
        else {
            rowView = inflater.inflate(R.layout.activity_brand_list_localdeals_list, null, true);

            TextView nameText = (TextView) rowView.findViewById(R.id.name);
            TextView nameText1 = (TextView) rowView.findViewById(R.id.name1);
            TextView nameText2 = (TextView) rowView.findViewById(R.id.name2);
            TextView nameText3 = (TextView) rowView.findViewById(R.id.name3);
            TextView distanceText = (TextView) rowView.findViewById(R.id.distance);
            TextView percentText = (TextView) rowView.findViewById(R.id.percent);
            TextView priceText = (TextView) rowView.findViewById(R.id.price);
            final ImageView imageView = (ImageView) rowView.findViewById(R.id.products_image);

            Dealer selDealer = mDealers.get(position);

            Deal deal = selDealer.getDeals().get(0);
            Product product = WarrantixWebService.getInstance().getProduct(deal.getProductID()).getProduct();


            if (product != null) {

                nameText.setText(product.getName());
                nameText1.setText(product.getBrandID());
                nameText2.setText(selDealer.getName());
                nameText3.setText(selDealer.getContact().getAddress());
                distanceText.setText("16km");
                percentText.setText(deal.getShortDescription());
                priceText.setText(product.getMsrp().toString());

                ImageButton brand_insurance_location = (ImageButton) rowView.findViewById(R.id.brand_insurance_location);
                brand_insurance_location.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                if (context instanceof WalletBrandListLocalDeals) {
//                    Location location = ((WalletBrandListLocalDeals)context).getMyLocation();
//                    WarrantixApplication.getInstance().showMyLocation(context, location);
//                }

                        MarshMallowPermission marshMallowPermission = new MarshMallowPermission(context);

                        // request camera
                        if (!marshMallowPermission.checkPermissionForGPS()) {
                            marshMallowPermission.requestPermissionForGPS();
                            return;
                        }

                        Intent i = new Intent(context, MapsActivity.class);
                        context.startActivity(i);
                    }
                });

                ImageButton brand_insurance_call = (ImageButton) rowView.findViewById(R.id.brand_insurance_call);
                brand_insurance_call.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        WarrantixApplication.getInstance().showDial(context);
                    }
                });


                Picasso.with(this.context.getApplicationContext())
                        .load(product.getImageThmb())
                        .error(R.drawable.image_holder)
                        .placeholder(R.drawable.image_holder)
                        .into(imageView);
            }
        }

        return rowView;
    }



}