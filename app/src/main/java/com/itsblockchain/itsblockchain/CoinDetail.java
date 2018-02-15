package com.itsblockchain.itsblockchain;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.itsblockchain.itsblockchain.DataProviders.PortfolioCoinData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CoinDetail extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    Toolbar toolbar;
    ToggleButton mSwitch;

    DatabaseHandler mHandler;

    EditText mBuyPrice, mAmountInvested, mQuantity;
    Button mAdd;

    TextView mCoinName, mPrice, mChaneInPrice;
    ImageView mCoinImage;

    String id, name, symbol;

    String total_amount, quantity, buy_price;

    ProgressBar mBar;

    LinearLayout mtradeLayout, mWatchLayout;

    FloatingActionButton mFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coin_detail);

        mHandler = new DatabaseHandler(this);

        toolbar = findViewById(R.id.coindetail_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        mSwitch = findViewById(R.id.switch_action);
        mSwitch.setOnCheckedChangeListener(this);

        mPrice = findViewById(R.id.currentPrice);
        mChaneInPrice = findViewById(R.id.changeInPrice);
        mBar = findViewById(R.id.progressBar);
        mtradeLayout = findViewById(R.id.trade_layout);
        mWatchLayout = findViewById(R.id.watchlist_layout);

        mBuyPrice = findViewById(R.id.edt_buyPrice);
        mAmountInvested = findViewById(R.id.edt_amount);
        mQuantity = findViewById(R.id.edt_quantity);
        mAdd = findViewById(R.id.addButton);
        mFab = findViewById(R.id.add_watchlist);

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        mAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                buy_price = mBuyPrice.getText().toString();
                total_amount = mAmountInvested.getText().toString();
                quantity = mQuantity.getText().toString();

                mHandler.addCoin(
                        new PortfolioCoinData(id, name, symbol, buy_price, quantity, total_amount)
                );
                finish();
                startActivity(new Intent(CoinDetail.this, MainActivity.class));


            }
        });

        mCoinImage = findViewById(R.id.coinImage);
        mCoinName = findViewById(R.id.coinName);

        id = getIntent().getStringExtra("id");
        name = getIntent().getStringExtra("name");
        symbol = getIntent().getStringExtra("symbol");

        mCoinName.setText(symbol + " / " + name);


         loadCoin(id);

    }


    private void loadCoin(final String id) {

        if(!(mBar.getVisibility() == View.VISIBLE)){
            mBar.setVisibility(View.VISIBLE);
        }

        String API_URL = "https://api.coinmarketcap.com/v1/ticker/"+id+"/";

        Volley.newRequestQueue(this)
                .add(
                        new StringRequest(
                                Request.Method.GET,
                                API_URL,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {

                                        mBar.setVisibility(View.GONE);

                                        try {
                                            JSONArray array = new JSONArray(response);

                                            JSONObject jsonObject = array.getJSONObject(0);

                                            mPrice.setText(jsonObject.getString("price_usd"));
                                            mChaneInPrice.setText(jsonObject.getString("percent_change_24h"));

                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }

                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {

                                        mBar.setVisibility(View.GONE);

                                        Snackbar.make(getCurrentFocus(), "Could not load coin", Snackbar.LENGTH_SHORT)
                                                .setAction("RETRY", new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View view) {
                                                        loadCoin(id);
                                                    }
                                                })
                                                .show();

                                    }
                                }
                        )
                );

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.coindetail_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.ac_notif:
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

        if(compoundButton == mSwitch){

            if(b){

                mtradeLayout.setVisibility(View.GONE);
                mWatchLayout.setVisibility(View.VISIBLE);

            }
            if(!b){

                mtradeLayout.setVisibility(View.VISIBLE);
                mWatchLayout.setVisibility(View.GONE);

            }

        }

    }
}
