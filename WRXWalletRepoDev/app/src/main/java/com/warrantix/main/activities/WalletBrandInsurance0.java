package com.warrantix.main.activities;


import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.warrantix.main.R;
import com.warrantix.main.adapter.WalletBrandInsuranceList;
import com.warrantix.main.adapter.WarrantixMarketplaceSubCategoryAccessoriesAdapter;
import com.warrantix.main.common.pref.WarrantixPreference;
import com.warrantix.main.common.rest.WarrantixWebService;
import com.warrantix.main.common.rest.model.Service;
import com.warrantix.main.common.rest.model.ServiceCompany;
import com.warrantix.main.common.rest.response.GetRelatedProductsResponse;
import com.warrantix.main.common.rest.response.GetServiceCompaniesResponse;
import com.warrantix.main.common.rest.response.GetServicesResponse;
import com.warrantix.main.manager.BackendManager;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by MyUserName on 3/4/2016.
 */
public class WalletBrandInsurance0 extends BaseActivity {
    private ListView list = null;
    private List<ServiceCompany> serviceCompanies = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_insurance0);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (isInitialized == false) {
            InitializeView();
            isInitialized = true;
        }
    }

    public void InitializeView(){

        list = (ListView) findViewById(R.id.list);
        list.setAdapter(null);

        GetServiceCompaniesResponse getServiceCompanyResponse = BackendManager.getInstance().getServiceCompaniesResponse(WarrantixPreference.TYPE_INSURANCE, WarrantixPreference.BRANDID_HEROID);
        if (getServiceCompanyResponse != null){
            serviceCompanies = getServiceCompanyResponse.getServiceCompaniesServices();
            if (serviceCompanies.size() != 0) {
                WalletBrandInsuranceList adapter = new WalletBrandInsuranceList(this, serviceCompanies);
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
