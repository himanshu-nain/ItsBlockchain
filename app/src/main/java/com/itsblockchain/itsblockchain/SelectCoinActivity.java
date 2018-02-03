package com.itsblockchain.itsblockchain;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.itsblockchain.itsblockchain.Adapters.SelectCoinAdapter;
import com.itsblockchain.itsblockchain.DataProviders.SelectCoinData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anonymous on 27/1/18.
 */

public class SelectCoinActivity extends AppCompatActivity{

    RecyclerView recyclerView;
    SelectCoinAdapter mAdapter;
    List<SelectCoinData> coinDataList, copyList;
    ProgressBar mbar;

    SearchView mSearch;

    Toolbar toolbar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_coin);

        toolbar = findViewById(R.id.select_coin_toolbar);
        setSupportActionBar(toolbar);

        mbar = findViewById(R.id.progressBar);
        mSearch = findViewById(R.id.search_view);
        recyclerView = findViewById(R.id.select_coin_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(SelectCoinActivity.this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(SelectCoinActivity.this, DividerItemDecoration.VERTICAL));

        coinDataList = new ArrayList<>();
        copyList = new ArrayList<>();

        mAdapter = new SelectCoinAdapter(SelectCoinActivity.this, coinDataList);

        recyclerView.setAdapter(mAdapter);
        mSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return false;
            }
        });

        loadData();

    }

    public void filter(String text){
        coinDataList.clear();
        if(text.isEmpty()){
            coinDataList.addAll(copyList);
        } else{
            text = text.toLowerCase();
            for(SelectCoinData item: copyList){
                if(item.getSymbol().toLowerCase().contains(text) || item.getName().toLowerCase().contains(text)){
                    coinDataList.add(item);
                }
            }
        }
        mAdapter.updateList(coinDataList);
    }


    private void loadData() {

        Volley.newRequestQueue(this)
                .add(
                        new StringRequest(Request.Method.GET,
                                "https://api.coinmarketcap.com/v1/ticker/?limit=1500",
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        if(mbar.getVisibility() == View.VISIBLE){
                                            mbar.setVisibility(View.GONE);
                                        }

                                        try {
                                            JSONArray array = new JSONArray(response);
                                            JSONObject object;

                                            for (int i=0; i<array.length(); i++){

                                                object = array.getJSONObject(i);

                                                coinDataList.add(
                                                        new SelectCoinData(object.getString("name"), object.getString("symbol"))
                                                );

                                            }

                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                        copyList.addAll(coinDataList);
                                        mAdapter.updateList(coinDataList);
                                    }
                                }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                                if(mbar.getVisibility() == View.VISIBLE){
                                    mbar.setVisibility(View.GONE);
                                }

                                Snackbar.make(getCurrentFocus(),
                                        "Could not get data. Please try again.", Snackbar.LENGTH_LONG)
                                        .setAction("RETRY", new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                if(mbar.getVisibility() == View.GONE){
                                                    mbar.setVisibility(View.VISIBLE);
                                                }
                                                loadData();
                                            }
                                        }).show();

                            }
                        })

                );

    }


}
