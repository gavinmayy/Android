package com.warrantix.main.activities;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.squareup.otto.Subscribe;
import com.squareup.picasso.Picasso;
import com.warrantix.main.R;
import com.warrantix.main.common.bus.BusProvider;
import com.warrantix.main.common.event.ProductSuccessEvent;
import com.warrantix.main.common.event.RelatedProductSuccessEvent;
import com.warrantix.main.common.pref.WarrantixPreference;
import com.warrantix.main.common.rest.model.Product;
import com.warrantix.main.common.rest.model.RelatedProduct;
import com.warrantix.main.common.rest.response.GetProductResponse;
import com.warrantix.main.common.rest.response.GetRelatedProductResponse;
import com.warrantix.main.dialog.MessageDialog;
import com.warrantix.main.manager.BackendManager;


/**
 * Created by MyUserName on 2/26/2016.
 */
public class WalletBrandAccessoriesEshop extends BaseActivity implements View.OnClickListener{

    private TextView applyforTEXT;
    private TextView detailsTEXT;

    private TextView titleTEXT;
    private TextView flipcoverTEXT;
    private TextView colorTEXT;
    private TextView whiteTEXT;
    private TextView proceTEXT;

    private TextView deliveryTEXT;
    private TextView financeOptionTEXT;
    private TextView enter_pincodeTEXT;
    private TextView delivery_pincodeTEXT;
    private TextView quantityTEXT;
    private TextView aboutthisitemTEXT;
    private TextView descriptionTEXT;
    private TextView description_explainTEXT;
    private TextView featured_detailsTEXT;
    private TextView featured_details_explainTEXT;

    private Button addToCartBTN;
    private Intent mIntent;

    private ImageButton up_arrowBtn;
    private ImageButton down_arrowBtn;
    private TextView numberTEXT;
    private Spinner spinnerNum;

    private Button buynowBTN;

    private LinearLayout revealBTN;
    private LinearLayout referBTN;
    private LinearLayout reviewBTN;
    private LinearLayout rankBTN;
    private LinearLayout chatBTN;

    private ImageButton BackArrowBtn;

    private String name = "";
    private String price = "";
    private String description = "";
    private String imageURL = "";
    private String color = "";
    private String msrpSymbol = "";
    private String featureAndDetail = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_accessories_shop);
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

        applyforTEXT = (TextView) findViewById(R.id.applyTXT);
        applyforTEXT.setPaintFlags(applyforTEXT.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG );
        detailsTEXT = (TextView) findViewById(R.id.detailsTXT);
        detailsTEXT.setPaintFlags(detailsTEXT.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG );

        String title = getIntent().getStringExtra("title");
        titleTEXT = (TextView) findViewById(R.id.title);
        if (title != null)
            titleTEXT.setText(title);

        flipcoverTEXT = (TextView) findViewById(R.id.Flip_CoverTXT);
        colorTEXT = (TextView) findViewById(R.id.colorTXT);
        whiteTEXT = (TextView) findViewById(R.id.whiteTXT);
        proceTEXT = (TextView) findViewById(R.id.proceTXT);
        deliveryTEXT = (TextView) findViewById(R.id.delivaryTXT);
        financeOptionTEXT = (TextView) findViewById(R.id.financeTXT);
        enter_pincodeTEXT = (TextView) findViewById(R.id.enter_pincodeTXT);
        delivery_pincodeTEXT = (TextView) findViewById(R.id.delivaryTXT);
        quantityTEXT = (TextView) findViewById(R.id.QuantityTXT);
        aboutthisitemTEXT = (TextView) findViewById(R.id.ABOUT_THIS_ITEMTXT);
        descriptionTEXT = (TextView) findViewById(R.id.DescriptionTXT);
        description_explainTEXT = (TextView) findViewById(R.id.Features_Details_ExplainTXT);
        featured_detailsTEXT = (TextView) findViewById(R.id.Features_DetailsTXT);
        featured_details_explainTEXT = (TextView) findViewById(R.id.Features_Details_Explain1TXT);

        LinearLayout applyFinanceBTN = (LinearLayout) findViewById(R.id.applyFinanceBTN);
        applyFinanceBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(WalletBrandAccessoriesEshop.this, WalletBrandFinance1.class);
                i.putExtra("title", "GET FINANCE");
                startActivity(i, true);
            }
        });

        String _Id = getIntent().getStringExtra("id");

        if (title.equals(WarrantixPreference.SUBCATEGORY_TITLE_ESHOP_ACCESSORIES)){
            BackendManager.getInstance().getRelatedProductResponse(_Id);
        } else {
            BackendManager.getInstance().getProductResponse(_Id);
        }


        addToCartBTN = (Button) findViewById(R.id.addtocartBTN);
        addToCartBTN.setOnClickListener(this);

        up_arrowBtn = (ImageButton) findViewById(R.id.up_arrowBTN);
        up_arrowBtn.setOnClickListener(this);

        down_arrowBtn = (ImageButton) findViewById(R.id.down_arrowBTN);
        down_arrowBtn.setOnClickListener(this);

        numberTEXT = (TextView) findViewById(R.id.numberTXT);
        spinnerNum = (Spinner) findViewById(R.id.spinner);

        final int[] number = {0,1,2,3,4,5,6,7,8,9,10};
        spinnerNum.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> arg0, View view, int position, long id) {
                int item = spinnerNum.getSelectedItemPosition();
                numberTEXT.setText(String.valueOf(number[item]));

            }

            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        buynowBTN = (Button) findViewById(R.id.buynowBTN);
        buynowBTN.setOnClickListener(this);

        revealBTN = (LinearLayout) findViewById(R.id.revealBTN);
        referBTN = (LinearLayout) findViewById(R.id.referBTN);
        reviewBTN = (LinearLayout) findViewById(R.id.reviewBTN);
        rankBTN = (LinearLayout) findViewById(R.id.rankBTN);
        chatBTN = (LinearLayout) findViewById(R.id.chatBTN);

        revealBTN.setOnClickListener(this);
        referBTN.setOnClickListener(this);
        reviewBTN.setOnClickListener(this);
        rankBTN.setOnClickListener(this);
        chatBTN.setOnClickListener(this);

        BackArrowBtn = (ImageButton) findViewById(R.id.brand_arrow);
        BackArrowBtn.setOnClickListener(this);

    }

    @Subscribe
    public void onRelatedProductSuccessEvent(final RelatedProductSuccessEvent event)
    {
        WalletBrandAccessoriesEshop.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                GetRelatedProductResponse getRelatedProductResponse = event.getRelatedProductsResponse();
                if (getRelatedProductResponse != null){
                    RelatedProduct relatedProduct = getRelatedProductResponse.getRelatedProduct();
                    name = relatedProduct.getName();
                    price = String.valueOf(relatedProduct.getMsrp());
                    description = relatedProduct.getDescription();
                    imageURL = relatedProduct.getImageThmb();
                    msrpSymbol = relatedProduct.getMsrpCurrencySymb();
                    featureAndDetail = relatedProduct.getFeatureAndDetails();

                    flipcoverTEXT.setText(name);
                    proceTEXT.setText("price: "+price);
                    description_explainTEXT.setText(description);
                    featured_details_explainTEXT.setText(featureAndDetail);

                    ImageView productImageView = (ImageView) findViewById(R.id.productImageView);
                    if (!imageURL.equals("")&& imageURL != null) {
                        Picasso.with(WalletBrandAccessoriesEshop.this.getApplicationContext())
                                .load(imageURL)
                                .error(R.drawable.image_holder)
                                .placeholder(R.drawable.image_holder)
                                .into(productImageView);
                    }
                }
            }
        });
    }

    @Subscribe
    public void onProductSuccessEvent(final ProductSuccessEvent event)
    {
        WalletBrandAccessoriesEshop.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                GetProductResponse getProductResponse = event.getProductResponse();
                if (getProductResponse != null){
                    Product product = getProductResponse.getProduct();
                    if (product !=null){
                        name = product.getName();
                        price = product.getMsrp().toString();
                        description = product.getDescription();
                        imageURL = product.getImageThmb();
                        msrpSymbol = product.getMsrpCurrencySymb();
                        featureAndDetail = product.getFeatureAndDetails();
                    }

                    flipcoverTEXT.setText(name);
                    proceTEXT.setText("price: "+price);
                    description_explainTEXT.setText(description);
                    featured_details_explainTEXT.setText(featureAndDetail);

                    ImageView productImageView = (ImageView) findViewById(R.id.productImageView);
                    if (!imageURL.equals("") && imageURL != null) {
                        Picasso.with(WalletBrandAccessoriesEshop.this.getApplicationContext())
                                .load(imageURL)
                                .error(R.drawable.image_holder)
                                .placeholder(R.drawable.image_holder)
                                .into(productImageView);
                    }
                }
            }
        });
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.addtocartBTN){
            MessageDialog dialog = new MessageDialog(WalletBrandAccessoriesEshop.this);
            dialog.setTitle("Added");
            dialog.setMessage("");
            dialog.show();
        }
        if (v.getId() == R.id.up_arrowBTN)
        {
            if (Integer.parseInt(numberTEXT.getText().toString())<=100){
                numberTEXT.setText(String.valueOf(Integer.parseInt(numberTEXT.getText().toString())+1));
            }
        }
        if (v.getId() == R.id.down_arrowBTN)
        {
            if (Integer.parseInt(numberTEXT.getText().toString())>1){
                numberTEXT.setText(String.valueOf(Integer.parseInt(numberTEXT.getText().toString())-1));
            }
        }
        if (v.getId() == R.id.buynowBTN)
        {
            WalletMarketplaceEshopProducts.eshop_productsSel=1;
            mIntent = new Intent(WalletBrandAccessoriesEshop.this, WalletMarketplaceEshopProducts.class);
            startActivity(mIntent, true);

//            WalletBrandAccessoriesFragment.AccessoriesSel =1;
//            mIntent = new Intent(WalletBrandAccessoriesEshop.this, WalletBrandAccessoriesFragment.class);
//            startActivity(mIntent);
        }

        if (v.getId() == R.id.revealBTN ){
            mIntent = new Intent(WalletBrandAccessoriesEshop.this, WalletBrandSocial.class);
            mIntent.putExtra("selected", 0);
            startActivity(mIntent, true);
        }
        if (v.getId() == R.id.referBTN ){
            mIntent = new Intent(WalletBrandAccessoriesEshop.this, WalletBrandSocial.class);
            mIntent.putExtra("selected", 1);
            startActivity(mIntent, true);
        }
        if (v.getId() == R.id.reviewBTN ){
            mIntent = new Intent(WalletBrandAccessoriesEshop.this, WalletBrandSocial.class);
            mIntent.putExtra("selected", 2);
            startActivity(mIntent, true);
        }
        if (v.getId() == R.id.rankBTN){
            mIntent = new Intent(WalletBrandAccessoriesEshop.this, WalletBrandSocial.class);
            mIntent.putExtra("selected", 3);
            startActivity(mIntent, true);
        }
        if (v.getId() == R.id.chatBTN){
            mIntent = new Intent(WalletBrandAccessoriesEshop.this, WalletBrandSocial.class);
            mIntent.putExtra("selected", 4);
            startActivity(mIntent, true);
        }
        if (v.getId() == R.id.brand_arrow){
            finish(true);
        }
    }


}
