package com.warrantix.main.fragments.walletbrand;


import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.warrantix.main.R;
import com.warrantix.main.activities.WalletMarketplaceEshopProducts;
import com.warrantix.main.fragments.BaseFragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import it.sephiroth.android.library.tooltip.Tooltip;


/**
 * Created by MyUserName on 3/4/2016.
 */
public class WalletMarketplaceEshopProductFragment2 extends BaseFragment implements View.OnClickListener {
    private Context mContext;
    private Intent mIntent;
    private View mView;

    private TextView amounttxt;
    private TextView amountValuetxt;
    private TextView salientfeaturedtxt;

    private RadioButton creditcardRB;
    private RadioButton debitcardRB;
    private RadioButton netbankingRB;
    private RadioButton americanexpresscardRB;
    private RadioButton mobiquikwalletRB;
    private RadioButton payTMRB;

    private TextView entercarddetails;

    private EditText cardnumberTXT;
    private EditText numberoncardTXT;
    private EditText empiryTXT;
    private EditText CVVTXT;

    private Button nextBtn;

    private SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/yyyy", Locale.US);

    private ImageView wallet_hero;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.mContext = getActivity();
        mView = inflater.inflate(R.layout.fragment_brand_product2, container, false);

        InitializeView();
        return mView;
    }

    public void InitializeView(){
        amounttxt = (TextView) mView.findViewById(R.id.amountTXT);
        amountValuetxt = (TextView) mView.findViewById(R.id.amountValueTXT);
        salientfeaturedtxt = (TextView) mView.findViewById(R.id.salientfeatured);
        entercarddetails = (TextView) mView.findViewById(R.id.entercarddetails);

        creditcardRB = (RadioButton) mView.findViewById(R.id.mode_creditcard);
        debitcardRB = (RadioButton) mView.findViewById(R.id.mode_debitcard);
        netbankingRB = (RadioButton) mView.findViewById(R.id.mode_netbanking);
        americanexpresscardRB = (RadioButton) mView.findViewById(R.id.mode_americancard);
        mobiquikwalletRB = (RadioButton) mView.findViewById(R.id.mode_mobiquikwallet);
        payTMRB = (RadioButton) mView.findViewById(R.id.mode_paytmwallet);


        cardnumberTXT = (EditText) mView.findViewById(R.id.cardnumberTXT);
        numberoncardTXT = (EditText) mView.findViewById(R.id.numberoncardTXT);
        empiryTXT = (EditText) mView.findViewById(R.id.empiryTXT);
        CVVTXT = (EditText) mView.findViewById(R.id.CVVTXT);

        cardnumberTXT.setTextColor(getResources().getColor(R.color.wx_tag_color));
        numberoncardTXT.setTextColor(getResources().getColor(R.color.wx_tag_color));
        empiryTXT.setTextColor(getResources().getColor(R.color.wx_tag_color));
        CVVTXT.setTextColor(getResources().getColor(R.color.wx_tag_color));

        creditcardRB.setOnClickListener(this);
        debitcardRB.setOnClickListener(this);
        netbankingRB.setOnClickListener(this);
        americanexpresscardRB.setOnClickListener(this);
        mobiquikwalletRB.setOnClickListener(this);
        payTMRB.setOnClickListener(this);

        empiryTXT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar newCalendar = Calendar.getInstance();

                try {
                    newCalendar.setTime(dateFormatter.parse(empiryTXT.getText().toString()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                DatePickerDialog dialog = new DatePickerDialog(getActivity(), purchaseDatePickerListener,
                        newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
                dialog.show();
            }
        });

        creditcardRB.setButtonDrawable(R.drawable.round_active);
        debitcardRB.setButtonDrawable(R.drawable.round_grey);
        netbankingRB.setButtonDrawable(R.drawable.round_grey);
        americanexpresscardRB.setButtonDrawable(R.drawable.round_grey);
        mobiquikwalletRB.setButtonDrawable(R.drawable.round_grey);
        payTMRB.setButtonDrawable(R.drawable.round_grey);

        nextBtn = (Button) mView.findViewById(R.id.nextBTN);
        nextBtn.setOnClickListener(this);

        final ImageButton cvvInfoBTN = (ImageButton) mView.findViewById(R.id.cvvInfoBTN);
        cvvInfoBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Typeface font = Typeface.createFromAsset(mActivity.getAssets(), "fonts/Montserrat-Regular.ttf");
                Tooltip.make(mActivity,
                        new Tooltip.Builder(101)
                                .anchor(CVVTXT, Tooltip.Gravity.TOP)
                                .closePolicy(new Tooltip.ClosePolicy()
                                        .insidePolicy(true, false)
                                        .outsidePolicy(true, false), 2000)
                                .activateDelay(400)
                                .showDelay(300)
                                .text("This is dummy information")
                                .maxWidth(500)
                                .withArrow(true)
                                .withOverlay(true)
                                .typeface(font)
                                .withStyleId(R.style.ToolTipLayoutCustomStyle)
                                .floatingAnimation(Tooltip.AnimationBuilder.DEFAULT)
                                .build()
                ).show();
            }
        });
    }


    @Override
    public void onClick(View v) {
        if (v.getId() ==  R.id.mode_creditcard){
            creditcardRB.setChecked(true);
            debitcardRB.setChecked(false);
            netbankingRB.setChecked(false);
            americanexpresscardRB.setChecked(false);
            mobiquikwalletRB.setChecked(false);
            payTMRB.setChecked(false);

            creditcardRB.setButtonDrawable(R.drawable.round_active);
            debitcardRB.setButtonDrawable(R.drawable.round_grey);
            netbankingRB.setButtonDrawable(R.drawable.round_grey);
            americanexpresscardRB.setButtonDrawable(R.drawable.round_grey);
            mobiquikwalletRB.setButtonDrawable(R.drawable.round_grey);
            payTMRB.setButtonDrawable(R.drawable.round_grey);



        }
        if (v.getId() ==  R.id.mode_debitcard){
            creditcardRB.setChecked(false);
            debitcardRB.setChecked(true);
            netbankingRB.setChecked(false);
            americanexpresscardRB.setChecked(false);
            mobiquikwalletRB.setChecked(false);
            payTMRB.setChecked(false);

            creditcardRB.setButtonDrawable(R.drawable.round_grey);
            debitcardRB.setButtonDrawable(R.drawable.round_active);
            netbankingRB.setButtonDrawable(R.drawable.round_grey);
            americanexpresscardRB.setButtonDrawable(R.drawable.round_grey);
            mobiquikwalletRB.setButtonDrawable(R.drawable.round_grey);
            payTMRB.setButtonDrawable(R.drawable.round_grey);




        }

        if (v.getId() ==  R.id.mode_netbanking) {
            creditcardRB.setChecked(false);
            debitcardRB.setChecked(false);
            netbankingRB.setChecked(true);
            americanexpresscardRB.setChecked(false);
            mobiquikwalletRB.setChecked(false);
            payTMRB.setChecked(false);

            creditcardRB.setButtonDrawable(R.drawable.round_grey);
            debitcardRB.setButtonDrawable(R.drawable.round_grey);
            netbankingRB.setButtonDrawable(R.drawable.round_active);
            americanexpresscardRB.setButtonDrawable(R.drawable.round_grey);
            mobiquikwalletRB.setButtonDrawable(R.drawable.round_grey);
            payTMRB.setButtonDrawable(R.drawable.round_grey);
        }

        if (v.getId() ==  R.id.mode_americancard) {
            creditcardRB.setChecked(false);
            debitcardRB.setChecked(false);
            netbankingRB.setChecked(false);
            americanexpresscardRB.setChecked(true);
            mobiquikwalletRB.setChecked(false);
            payTMRB.setChecked(false);

            creditcardRB.setButtonDrawable(R.drawable.round_grey);
            debitcardRB.setButtonDrawable(R.drawable.round_grey);
            netbankingRB.setButtonDrawable(R.drawable.round_grey);
            americanexpresscardRB.setButtonDrawable(R.drawable.round_active);
            mobiquikwalletRB.setButtonDrawable(R.drawable.round_grey);
            payTMRB.setButtonDrawable(R.drawable.round_grey);

        }
        if (v.getId() ==  R.id.mode_mobiquikwallet) {
            creditcardRB.setChecked(false);
            debitcardRB.setChecked(false);
            netbankingRB.setChecked(false);
            americanexpresscardRB.setChecked(false);
            mobiquikwalletRB.setChecked(true);
            payTMRB.setChecked(false);

            creditcardRB.setButtonDrawable(R.drawable.round_grey);
            debitcardRB.setButtonDrawable(R.drawable.round_grey);
            netbankingRB.setButtonDrawable(R.drawable.round_grey);
            americanexpresscardRB.setButtonDrawable(R.drawable.round_grey);
            mobiquikwalletRB.setButtonDrawable(R.drawable.round_active);
            payTMRB.setButtonDrawable(R.drawable.round_grey);
        }
        if (v.getId() ==  R.id.mode_paytmwallet) {
            creditcardRB.setChecked(false);
            debitcardRB.setChecked(false);
            netbankingRB.setChecked(false);
            americanexpresscardRB.setChecked(false);
            mobiquikwalletRB.setChecked(false);
            payTMRB.setChecked(true);
            creditcardRB.setButtonDrawable(R.drawable.round_grey);
            debitcardRB.setButtonDrawable(R.drawable.round_grey);
            netbankingRB.setButtonDrawable(R.drawable.round_grey);
            americanexpresscardRB.setButtonDrawable(R.drawable.round_grey);
            mobiquikwalletRB.setButtonDrawable(R.drawable.round_grey);
            payTMRB.setButtonDrawable(R.drawable.round_active);
        }

        if (v.getId() == R.id.nextBTN){
            if ((mContext != null) && (mContext instanceof WalletMarketplaceEshopProducts)) {
                ((WalletMarketplaceEshopProducts)mContext).loadProduct3Fragment();
            }
        }

    }

    private DatePickerDialog.OnDateSetListener purchaseDatePickerListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int year, int monthOfYear, int dayOfMonth) {
            Calendar today = Calendar.getInstance();
            Calendar newDate = Calendar.getInstance();
            newDate.set(year, monthOfYear, dayOfMonth);
            empiryTXT.setText(dateFormatter.format(newDate.getTime()));
        }
    };

}
