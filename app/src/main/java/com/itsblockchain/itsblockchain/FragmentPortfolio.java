package com.itsblockchain.itsblockchain;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.itsblockchain.itsblockchain.Adapters.CoinAdapter;
import com.itsblockchain.itsblockchain.DataProviders.CoinData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anonymous on 24/1/18.
 */

public class FragmentPortfolio extends Fragment implements View.OnClickListener {

    RecyclerView recyclerView;
    CoinAdapter mAdapter;
    List<CoinData> mData;
    FloatingActionButton mFab;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_portfolio, container, false);

        mData = new ArrayList<>();
        mAdapter = new CoinAdapter(getContext(), mData);
        mFab = rootView.findViewById(R.id.add_);
        mFab.setOnClickListener(this);

        recyclerView = rootView.findViewById(R.id.portfolio_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        recyclerView.setAdapter(mAdapter);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Raleway-Regular.ttf");
        TextView textView = view.findViewById(R.id.assetCount);
        textView.setTypeface(typeface);

        load_data();
    }

    private void load_data() {
        mData.add(new CoinData("bit.png","BTC / Bitcoin", 11098.12, -12.223));
        mData.add(new CoinData("eth.png","ETH / Ethereum", 998.12, 2.12));
        mData.add(new CoinData("rip.png","XRP / Ripple", 1.42, -0.43));
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {

        if(view == mFab){

            startActivity(
                    new Intent(getActivity(), SelectCoinActivity.class)
            );

        }

    }
}
