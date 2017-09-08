package com.warrantix.main.activities.brandlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.warrantix.main.R;
import com.warrantix.main.activities.BaseActivity;
import com.warrantix.main.adapter.WalletBrandListOrderList;
import com.warrantix.main.common.rest.WarrantixWebService;
import com.warrantix.main.common.rest.model.Order;
import com.warrantix.main.common.rest.response.GetOrdersResponse;
import com.warrantix.main.manager.BackendManager;

import java.util.List;


/**
 * Created by MyUserName on 2/26/2016.
 */
public class WalletBrandListOrder extends BaseActivity {
    ListView list;


    private Intent mIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_list_order);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (isInitialized == false) {
            Initialize();
            isInitialized = true;
        }
    }

    public void Initialize(){

        list=(ListView)findViewById(R.id.list);
        list.setAdapter(null);

        GetOrdersResponse getOrdersResponse = BackendManager.getInstance().getOrdersResponse();
        if (getOrdersResponse != null){
            List<Order> orders = getOrdersResponse.getOrders();
            if (orders.size() != 0 ){
                WalletBrandListOrderList adapter = new WalletBrandListOrderList(this,orders);
                list.setAdapter(adapter);
            }
        }

        ImageButton btnBack = (ImageButton) findViewById(R.id.brand_arrow);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(true);
            }
        });
    }
}
