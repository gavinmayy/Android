package com.warrantix.main.adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.warrantix.main.R;
import com.warrantix.main.activities.brandlist.WalletBrandListSettingsDefaultMarketplace;

public class MarketPlaceGridAdapter extends BaseAdapter {

    private Context context;
    private final int[] pluginResourceIDs;
    private int defaultSelected = 0;

    public MarketPlaceGridAdapter(Context context, int[] pluginResourceIDs, int defaultSelected) {
        this.context = context;
        this.pluginResourceIDs = pluginResourceIDs;
        this.defaultSelected = defaultSelected;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    public void setDefaultSelected(int selected) {
        this.defaultSelected = selected;
    }

    @Override
    public boolean isEnabled(int position) {
        int row = position / 3;
        if (row % 2 == 0)
            return false;
        else
            return true;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View gridView;
        if (convertView != null) {
            gridView = convertView;
        }
        else {
            gridView = new View(context);
            gridView = inflater.inflate(R.layout.gridcell_marketplace_plugin, null);
        }

        // for new view's backlayout
        int row = position / 3;
        RelativeLayout backLayout = (RelativeLayout) gridView.findViewById(R.id.backLayout);
        backLayout.setVisibility(View.VISIBLE);
        if (position % 3 == 0) {
            // left cell
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) backLayout.getLayoutParams();
            layoutParams.setMargins((int) context.getResources().getDimension(R.dimen.control_space_margin_small), 0, 0, 0);
            backLayout.setLayoutParams(layoutParams);
        }
        else if (position % 3 == 2) {
            // right cell
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) backLayout.getLayoutParams();
            layoutParams.setMargins(0, 0, (int) context.getResources().getDimension(R.dimen.control_space_margin_small), 0);
            backLayout.setLayoutParams(layoutParams);
        }
        else {
            // center cell
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) backLayout.getLayoutParams();
            layoutParams.setMargins(0, 0, 0, 0);
            backLayout.setLayoutParams(layoutParams);
        }

        // set value into textview
        TextView textNumberView = (TextView) gridView.findViewById(R.id.textNumberView);
        TextView defaultTextView = (TextView) gridView.findViewById(R.id.defaultTextView);
        ImageView pluginImageView = (ImageView) gridView.findViewById(R.id.pluginImageView);

        if (row % 2 == 0) {
            // this means title cell
            pluginImageView.setVisibility(View.GONE);
            if (position == 0) {
                textNumberView.setVisibility(View.GONE);
                defaultTextView.setVisibility(View.VISIBLE);
                defaultTextView.setGravity(Gravity.CENTER);
            }
            else {
                textNumberView.setVisibility(View.VISIBLE);
                defaultTextView.setVisibility(View.GONE);
                textNumberView.setGravity(Gravity.CENTER);
            }

            int txtIndex = (row/2)*3 + position % 3;

            if (txtIndex > 7) {
                textNumberView.setVisibility(View.GONE);
                if (txtIndex > 9)
                    backLayout.setVisibility(View.GONE);
            }
            else {
                textNumberView.setText(String.format("%02d", txtIndex));
            }
            backLayout.setBackgroundResource(R.drawable.default_background);
        }
        else {
            pluginImageView.setVisibility(View.VISIBLE);
            textNumberView.setVisibility(View.GONE);
            defaultTextView.setVisibility(View.GONE);

            int index = ((row-1)/ 2) * 3 + position % 3;
            pluginImageView.setImageResource(pluginResourceIDs[index]);
            if (index == defaultSelected) {
                // default view
                backLayout.setBackgroundResource(R.drawable.grid_selected_background);
                ((WalletBrandListSettingsDefaultMarketplace)context).setSelectedView(gridView, index, position);
            }
            else {
                // other view
                backLayout.setBackgroundResource(R.drawable.default_background);
            }
        }
        return gridView;
    }

    @Override
    public int getCount() {
        return pluginResourceIDs.length * 2;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

}