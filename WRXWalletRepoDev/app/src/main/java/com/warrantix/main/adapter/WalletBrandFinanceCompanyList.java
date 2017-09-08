package com.warrantix.main.adapter;

import android.app.Activity;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.warrantix.main.R;
import com.squareup.picasso.Picasso;
import com.warrantix.main.WarrantixApplication;
import com.warrantix.main.activities.BaseActivity;
import com.warrantix.main.activities.WalletBrandFinance;
import com.warrantix.main.activities.WalletBrandFinance0;
import com.warrantix.main.activities.WalletBrandFinance1;
import com.warrantix.main.activities.WalletBrandInsurance;
import com.warrantix.main.activities.social.WalletBrandSocialChatMessage;
import com.warrantix.main.common.rest.model.Service;
import com.warrantix.main.common.rest.model.ServiceCompany;

import java.util.ArrayList;
import java.util.List;

public class WalletBrandFinanceCompanyList extends BaseAdapter {

    private final Activity context;
    private List<ServiceCompany> mServiceCompanies = new ArrayList<>();

    public WalletBrandFinanceCompanyList(Activity context, List<ServiceCompany> serviceCompanies) {
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
            rowView = inflater.inflate(R.layout.listview_wallet_brand_financecompany, null, true);
        else
            rowView = view;

        final ImageView imageView = (ImageView) rowView.findViewById(R.id.products_image);

        ServiceCompany serviceCompany = mServiceCompanies.get(position);

        Picasso.with(this.context.getApplicationContext())
                .load(serviceCompany.getImageUrl())
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

        Button shopButton = (Button) rowView.findViewById(R.id.financePolicyButton);
        shopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (context instanceof BaseActivity) {
                    Intent i = new Intent(context, WalletBrandFinance0.class);
                    ((BaseActivity)context).startActivity(i, true);
                }
                else {
                    Intent i = new Intent(context, WalletBrandFinance0.class);
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

        ImageButton brand_insurance_chat = (ImageButton) rowView.findViewById(R.id.brand_insurance_chat);
        brand_insurance_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(context, WalletBrandSocialChatMessage.class);
                mIntent.putExtra("title", "START A NEW CHAT");
                if (context instanceof BaseActivity)
                    ((BaseActivity) context).startActivity(mIntent, true);
                else
                    context.startActivity(mIntent);
            }
        });
        return rowView;
    }




}