package com.itsblockchain.itsblockchain.Adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.itsblockchain.itsblockchain.DataProviders.SelectCoinData;
import com.itsblockchain.itsblockchain.R;

import java.util.List;

/**
 * Created by anonymous on 27/1/18.
 */

public class SelectCoinAdapter extends RecyclerView.Adapter<SelectCoinAdapter.ViewHolder> {

    Context mContext;
    List<SelectCoinData> mList;

    public SelectCoinAdapter(Context mContext, List<SelectCoinData> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.sample_coin, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        String resultText = mList.get(position).getSymbol() + " / " +mList.get(position).getName();
        holder.mName.setText(resultText);

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void updateList(List<SelectCoinData> list){
        mList=list;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        TextView mName;

        public ViewHolder(View itemView) {
            super(itemView);
            mName = itemView.findViewById(R.id.coinName);
            mName.setTypeface(
                    Typeface.createFromAsset(mContext.getAssets(), "fonts/Raleway-Regular.ttf")
            );
        }
    }
}
