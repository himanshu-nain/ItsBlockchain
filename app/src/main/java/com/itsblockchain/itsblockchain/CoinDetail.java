package com.itsblockchain.itsblockchain;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.itsblockchain.itsblockchain.DataProviders.PortfolioCoinData;

public class CoinDetail extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    Toolbar toolbar;
    ToggleButton mSwitch;
    String coinSymbol;

    DatabaseHandler mHandler;

    EditText mBuyPrice, mAmountInvested, mQuantity;
    Button mAdd;

    TextView mCoinName;
    ImageView mCoinImage;

    String id, name, symbol;

    String total_amount, quantity, buy_price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coin_detail);

        coinSymbol = getIntent().getStringExtra("id");
        mHandler = new DatabaseHandler(this);

        toolbar = findViewById(R.id.coindetail_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        mSwitch = findViewById(R.id.switch_action);
        mSwitch.setOnCheckedChangeListener(this);

        mBuyPrice = findViewById(R.id.edt_buyPrice);
        mAmountInvested = findViewById(R.id.edt_amount);
        mQuantity = findViewById(R.id.edt_quantity);
        mAdd = findViewById(R.id.addButton);

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

        /*

        mBuyPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mAmountInvested.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(!mBuyPrice.getText().toString().isEmpty() && !charSequence.toString().isEmpty()){

                    total_amount = charSequence.toString();
                    buy_price = mBuyPrice.getText().toString();

                    double temp;
                    temp = Double.parseDouble(total_amount) / Double.parseDouble(buy_price);

                    mQuantity.setText(String.valueOf(temp));
                }
                if(charSequence.toString().isEmpty()){
                    mQuantity.setText("");
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mQuantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(!mAmountInvested.getText().toString().isEmpty() && !charSequence.toString().isEmpty()){

                    total_amount = mAmountInvested.getText().toString();
                    quantity = charSequence.toString();

                    double temp = Double.parseDouble(total_amount) / Double.parseDouble(quantity);

                    mBuyPrice.setText(String.valueOf(temp));

                }

                if(!mAmountInvested.getText().toString().isEmpty() && charSequence.toString().isEmpty()){
                    mBuyPrice.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        */

        mCoinImage = findViewById(R.id.coinImage);
        mCoinName = findViewById(R.id.coinName);

        id = getIntent().getStringExtra("id");
        name = getIntent().getStringExtra("name");
        symbol = getIntent().getStringExtra("symbol");

        mCoinName.setText(symbol + " / " + name);

        //
        // loadCoin(id);
        //
    }

    /*
    private void loadCoin(final String id) {

        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Loading data. Please wait...");
        dialog.setIndeterminate(false);
        dialog.show();

        String API_URL = "https://api.coinmarketcap.com/v1/ticker/"+id+"/";

        Volley.newRequestQueue(this)
                .add(
                        new StringRequest(
                                Request.Method.GET,
                                API_URL,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {

                                        dialog.dismiss();

                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {

                                        dialog.dismiss();

                                        Snackbar.make(getCurrentFocus(), "", Snackbar.LENGTH_SHORT)
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
*/

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



        }

    }
}
