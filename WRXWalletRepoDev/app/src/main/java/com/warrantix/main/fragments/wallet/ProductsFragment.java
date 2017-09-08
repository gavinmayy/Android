package com.warrantix.main.fragments.wallet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.noveogroup.android.log.Log;
import com.squareup.otto.Subscribe;
import com.warrantix.main.R;
import com.warrantix.main.activities.BaseActivity;
import com.warrantix.main.activities.MainActivity;
import com.warrantix.main.activities.WalletBrandProductsDetail;
import com.warrantix.main.activities.WalletMarketplaceSubCategory;
import com.warrantix.main.adapter.WalletProductList;
import com.warrantix.main.adapter.WarrantixMarketplaceSubCategoryAccessoriesAdapter;
import com.warrantix.main.common.event.ProductsSuccessEvent;
import com.warrantix.main.common.event.RelatedProductsSuccessEvent;
import com.warrantix.main.common.pref.WarrantixPreference;
import com.warrantix.main.common.rest.WarrantixWebService;
import com.warrantix.main.common.rest.model.Product;
import com.warrantix.main.common.rest.response.GetProductsResponse;
import com.warrantix.main.common.rest.response.GetRelatedProductsResponse;
import com.warrantix.main.fragments.BaseFragment;
import com.warrantix.main.manager.BackendManager;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProductsFragment extends BaseFragment {

    private LinearLayout mCategory = null;
    private ListView listView ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_products, container, false);
        initializeView(v);
        initializeBannerBar(v);
        return v;
    }

    private void initializeBannerBar(View v) {
        final LinearLayout allButton = (LinearLayout) v.findViewById(R.id.products_all);
        final LinearLayout appliancesButton = (LinearLayout) v.findViewById(R.id.products_appliances);
        final LinearLayout vehicleButton = (LinearLayout) v.findViewById(R.id.wallet_vehicles);
        final LinearLayout electronicButton = (LinearLayout) v.findViewById(R.id.products_electronics);
        final LinearLayout assortedButton = (LinearLayout) v.findViewById(R.id.products_assorted);

        final TextView allTextView = (TextView) v.findViewById(R.id.allTextView);
        final TextView appliancesTextView = (TextView) v.findViewById(R.id.appliancesTextView);
        final TextView vehiclesTextView = (TextView) v.findViewById(R.id.vehiclesTextView);
        final TextView electronicTextView = (TextView) v.findViewById(R.id.electronicsTextView);
        final TextView personalTextView = (TextView) v.findViewById(R.id.personalTextView);

        allButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allTextView.setBackgroundResource(R.drawable.stroke_accent_button_selector);
                appliancesTextView.setBackgroundResource(R.drawable.stroke_grey_button_selector);
                vehiclesTextView.setBackgroundResource(R.drawable.stroke_grey_button_selector);
                electronicTextView.setBackgroundResource(R.drawable.stroke_grey_button_selector);
                personalTextView.setBackgroundResource(R.drawable.stroke_grey_button_selector);

                allTextView.setTextColor(getResources().getColor(R.color.wx_accent_color));
                appliancesTextView.setTextColor(getResources().getColor(R.color.wx_fourth_color));
                vehiclesTextView.setTextColor(getResources().getColor(R.color.wx_fourth_color));
                electronicTextView.setTextColor(getResources().getColor(R.color.wx_fourth_color));
                personalTextView.setTextColor(getResources().getColor(R.color.wx_fourth_color));
            }
        });

        appliancesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allTextView.setBackgroundResource(R.drawable.stroke_grey_button_selector);
                appliancesTextView.setBackgroundResource(R.drawable.stroke_accent_button_selector);
                vehiclesTextView.setBackgroundResource(R.drawable.stroke_grey_button_selector);
                electronicTextView.setBackgroundResource(R.drawable.stroke_grey_button_selector);
                personalTextView.setBackgroundResource(R.drawable.stroke_grey_button_selector);

                allTextView.setTextColor(getResources().getColor(R.color.wx_fourth_color));
                appliancesTextView.setTextColor(getResources().getColor(R.color.wx_accent_color));
                vehiclesTextView.setTextColor(getResources().getColor(R.color.wx_fourth_color));
                electronicTextView.setTextColor(getResources().getColor(R.color.wx_fourth_color));
                personalTextView.setTextColor(getResources().getColor(R.color.wx_fourth_color));
            }
        });

        vehicleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allTextView.setBackgroundResource(R.drawable.stroke_grey_button_selector);
                appliancesTextView.setBackgroundResource(R.drawable.stroke_grey_button_selector);
                vehiclesTextView.setBackgroundResource(R.drawable.stroke_accent_button_selector);
                electronicTextView.setBackgroundResource(R.drawable.stroke_grey_button_selector);
                personalTextView.setBackgroundResource(R.drawable.stroke_grey_button_selector);

                allTextView.setTextColor(getResources().getColor(R.color.wx_fourth_color));
                appliancesTextView.setTextColor(getResources().getColor(R.color.wx_fourth_color));
                vehiclesTextView.setTextColor(getResources().getColor(R.color.wx_accent_color));
                electronicTextView.setTextColor(getResources().getColor(R.color.wx_fourth_color));
                personalTextView.setTextColor(getResources().getColor(R.color.wx_fourth_color));
            }
        });

        electronicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allTextView.setBackgroundResource(R.drawable.stroke_grey_button_selector);
                appliancesTextView.setBackgroundResource(R.drawable.stroke_grey_button_selector);
                vehiclesTextView.setBackgroundResource(R.drawable.stroke_grey_button_selector);
                electronicTextView.setBackgroundResource(R.drawable.stroke_accent_button_selector);
                personalTextView.setBackgroundResource(R.drawable.stroke_grey_button_selector);

                allTextView.setTextColor(getResources().getColor(R.color.wx_fourth_color));
                appliancesTextView.setTextColor(getResources().getColor(R.color.wx_fourth_color));
                vehiclesTextView.setTextColor(getResources().getColor(R.color.wx_fourth_color));
                electronicTextView.setTextColor(getResources().getColor(R.color.wx_accent_color));
                personalTextView.setTextColor(getResources().getColor(R.color.wx_fourth_color));
            }
        });

        assortedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allTextView.setBackgroundResource(R.drawable.stroke_grey_button_selector);
                appliancesTextView.setBackgroundResource(R.drawable.stroke_grey_button_selector);
                vehiclesTextView.setBackgroundResource(R.drawable.stroke_grey_button_selector);
                electronicTextView.setBackgroundResource(R.drawable.stroke_grey_button_selector);
                personalTextView.setBackgroundResource(R.drawable.stroke_accent_button_selector);

                allTextView.setTextColor(getResources().getColor(R.color.wx_fourth_color));
                appliancesTextView.setTextColor(getResources().getColor(R.color.wx_fourth_color));
                vehiclesTextView.setTextColor(getResources().getColor(R.color.wx_fourth_color));
                electronicTextView.setTextColor(getResources().getColor(R.color.wx_fourth_color));
                personalTextView.setTextColor(getResources().getColor(R.color.wx_accent_color));
            }
        });
    }

    public void initializeView(View v) {
        mCategory = (LinearLayout) v.findViewById(R.id.category);

        ListView listView = (ListView) v.findViewById(R.id.list);
        GetProductsResponse getProductsResponse = WarrantixWebService.getInstance().getProducts(WarrantixPreference.BRANDID_HEROID);
        if (getProductsResponse != null){
            WalletProductList adapter = new WalletProductList(mActivity, getProductsResponse.getProducts());

            listView.setAdapter(adapter);
            listView.setOnScrollListener(scrollChangeListener);
        }
//        BackendManager.getInstance().getProductsResponse(WarrantixPreference.BRANDID_HEROID);

        listView.setOnScrollListener(scrollChangeListener);
//        listView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if (mActivity instanceof MainActivity)
//                    return ((MainActivity) mActivity).social2BarDispatchEvent(v, event);
//
//                return false;
//            }
//        });

//        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                if (mActivity instanceof MainActivity)
//                    ((MainActivity) mActivity).showSocial3Bar();
//
//                return true;
//            }
//        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent mIntent = new Intent(mActivity, WalletBrandProductsDetail.class);
                if (mActivity instanceof BaseActivity)
                    ((BaseActivity) mActivity).startActivity(mIntent, true);
                else
                    startActivity(mIntent);
            }
        });
    }

//    @Subscribe
//    public void onProductsSuccessEvent(final ProductsSuccessEvent event)
//    {
//        getActivity().runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                GetProductsResponse getProductsResponse = event.getProductsResponse();
//                List<Product> products = new ArrayList<>();
//                if (getProductsResponse != null){
//                    products = getProductsResponse.getProducts();
//                    if (products.size() != 0) {
//                        WalletProductList adapter = new WalletProductList(getActivity(), products);
//                        listView.setAdapter(adapter);
//                    }
//                }
//            }
//        });
//    }

    private final AbsListView.OnScrollListener scrollChangeListener = new AbsListView.OnScrollListener() {
        public int mLastFirstVisibleItem = 0;
        public boolean mIsScrollingUp = true;

        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            // TODO Auto-generated method stub

        }

        public void onScrollStateChanged(AbsListView view, int scrollState) {
            // TODO Auto-generated method stub
            if(scrollState != 0)
                return;

            final int currentFirstVisibleItem = view.getFirstVisiblePosition();
            if (currentFirstVisibleItem > mLastFirstVisibleItem) {
                mIsScrollingUp = false;
                Log.i("ScrollListener", "scrolling down...");
            } else if (currentFirstVisibleItem < mLastFirstVisibleItem) {
                mIsScrollingUp = true;
                Log.i("ScrollListener", "scrolling up...");
            }

            mLastFirstVisibleItem = currentFirstVisibleItem;

            if (mIsScrollingUp == true) {
                if (mCategory.getVisibility() != View.VISIBLE) {
                    mCategory.setVisibility(View.VISIBLE);

                    Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.animation_slide_in_up);
                    animation.setDuration(400);
                    mCategory.setAnimation(animation);
                    mCategory.animate();
                    animation.start();
                }
            }
            else {
                if (mCategory.getVisibility() != View.GONE) {
                    mCategory.setVisibility(View.GONE);

                    Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.animation_slide_out_up);
                    animation.setDuration(400);
                    mCategory.setAnimation(animation);
                    mCategory.animate();
                    animation.start();
                }
            }


//            if(view.getFirstVisiblePosition() == 0) {
//                if (mCategory.getVisibility() != View.VISIBLE) {
//                    mCategory.setVisibility(View.VISIBLE);
//
//                    Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.animation_slide_in_up);
//                    animation.setDuration(400);
//                    mCategory.setAnimation(animation);
//                    mCategory.animate();
//                    animation.start();
//                }
//            }
//            else {
//                if (mCategory.getVisibility() != View.GONE) {
//                    mCategory.setVisibility(View.GONE);
//
//                    Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.animation_slide_out_up);
//                    animation.setDuration(400);
//                    mCategory.setAnimation(animation);
//                    mCategory.animate();
//                    animation.start();
//                }
//            }
        }
    };
}