package com.itsblockchain.itsblockchain.Adapters;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.itsblockchain.itsblockchain.DataProviders.PortfolioCoinData;
import com.itsblockchain.itsblockchain.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by anonymous on 24/1/18.
 */

public class CoinAdapter extends RecyclerView.Adapter<CoinAdapter.ViewHolder> {

    Context mContext;
    List<PortfolioCoinData> mList;

    public CoinAdapter(Context mContext, List<PortfolioCoinData> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.sample_portfolio_coin, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        InputStream is = null;
        try {
            is = mContext.getAssets().open("icons/"+mList.get(position).getImgUrl());
            holder.mPic.setImageBitmap(BitmapFactory.decodeStream(is));
        } catch (IOException e) {
            e.printStackTrace();
        }

        holder.mName.setText(mList.get(position).getName());
        String value = "$ " + String.valueOf(mList.get(position).getValue());
        holder.mValue.setText(value);

        if(mList.get(position).getChange()>=0){
            holder.mChange.setTextColor(Color.parseColor("#166910"));
            value = "$ " + String.valueOf(mList.get(position).getChange());
            holder.mChange.setText(value);
        }else {
            holder.mChange.setTextColor(Color.parseColor("#b91311"));
            value = "$ " + String.valueOf(-1 * mList.get(position).getChange());
            holder.mChange.setText(value);
        }

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView mName, mValue, mChange;
        ImageView mPic;

        public ViewHolder(View itemView) {
            super(itemView);

            Typeface typeface = Typeface.createFromAsset(mContext.getAssets(), "fonts/Raleway-Regular.ttf");
            Typeface typeface2 = Typeface.createFromAsset(mContext.getAssets(), "fonts/Cabin-Regular.ttf");

            mPic = itemView.findViewById(R.id.coinImage);
            mName = itemView.findViewById(R.id.coinName);
            mName.setTypeface(typeface);
            mValue = itemView.findViewById(R.id.coinValue);
            mValue.setTypeface(typeface2);
            mChange = itemView.findViewById(R.id.coinChange);
            mChange.setTypeface(typeface2);
        }
    }
}
