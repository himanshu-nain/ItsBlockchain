package com.itsblockchain.itsblockchain;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

public class CoinDetail extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    Toolbar toolbar;
    ToggleButton mSwitch;
    String coinSymbol;

    EditText mBuyPrice, mAmountInvested, mQuantity;
    Button mAdd;

    TextView mCoinName;
    ImageView mCoinImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coin_detail);

        coinSymbol = getIntent().getStringExtra("id");

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

        mCoinImage = findViewById(R.id.coinImage);
        mCoinName = findViewById(R.id.coinName);

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



        }

    }
}
