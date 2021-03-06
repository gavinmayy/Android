package com.warrantix.main.adapter;

import android.app.Activity;

import android.content.Intent;
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
import com.warrantix.main.activities.WalletBrandAMC;
import com.warrantix.main.common.rest.model.Service;
import com.warrantix.main.common.rest.model.ServiceCompany;

import java.util.ArrayList;
import java.util.List;

public class WalletBrandAMCList extends BaseAdapter {

    private final Activity context;
    private List<ServiceCompany> mServiceCompanies;

    public WalletBrandAMCList(Activity context, List<ServiceCompany> serviceCompanies) {
        this.context = context;
        mServiceCompanies = serviceCompanies;
    }

    @Override
    public int getCount() {
        return mServiceCompanies.size();
    }

    @Override
    public Object getItem(int position) {
        return mServiceCompanies.get(position);
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
            rowView = inflater.inflate(R.layout.listview_wallet_brand_insurance, null, true);
        else
            rowView = view;

        TextView nameText = (TextView) rowView.findViewById(R.id.name);
        TextView placeText = (TextView) rowView.findViewById(R.id.place);
        final ImageView imageView = (ImageView) rowView.findViewById(R.id.products_image);

        nameText.setText(mServiceCompanies.get(position).getName());
        placeText.setText(mServiceCompanies.get(position).getDescription());


        Picasso.with(this.context.getApplicationContext())
                    .load(mServiceCompanies.get(position).getImageUrl())
                    .error(R.drawable.image_holder)
                    .placeholder(R.drawable.image_holder)
                    .into(imageView);


        imageView.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {
            //...
//             Intent intent = new Intent(getContext(),WalletHeroActivity.class);
//             getContext().startActivity(intent);
         //overridePendingTransition(R.anim.slide_left, R.anim.slide_right);
        }
        });

        ImageButton shopButton = (ImageButton) rowView.findViewById(R.id.brand_insurance_shop);
        shopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (context instanceof BaseActivity) {
                    Intent i = new Intent(context, WalletBrandAMC.class);
                    ((BaseActivity)context).startActivity(i, true);
                }
                else {
                    Intent i = new Intent(context, WalletBrandAMC.class);
                    context.startActivity(i);
                }
            }
        });

        ImageButton brand_insurance_call = (ImageButton) rowView.findViewById(R.id.brand_insurance_call);
        brand_insurance_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WarrantixApplication.getInstance().showDial(context);
            }
        });

        return rowView;
    }




}