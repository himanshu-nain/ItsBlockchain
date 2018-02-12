package com.itsblockchain.itsblockchain;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.itsblockchain.itsblockchain.DataProviders.PortfolioCoinData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anonymous on 12/2/18.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "portfolio";
    private static final String TABLE_COIN = "coin";

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_SYMBOL = "symbol";
    private static final String KEY_BUY_PRICE = "buyprice";
    private static final String KEY_BUY_QUANTITY = "buyquantity";
    private static final String KEY_BUY_AMOUNT = "buyamount";
    private static final String KEY_LAST_CHANGE = "lastchange";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String CREATE_COIN_TABLE = "CREATE TABLE " + TABLE_COIN + "("
                + KEY_ID + " TEXT UNIQUE,"
                + KEY_NAME + " TEXT,"
                + KEY_SYMBOL + " TEXT UNIQUE, "
                + KEY_BUY_PRICE + " TEXT,"
                + KEY_BUY_QUANTITY + " TEXT,"
                + KEY_BUY_AMOUNT + " TEXT,"
                + KEY_LAST_CHANGE + " TEXT" +")";
        sqLiteDatabase.execSQL(CREATE_COIN_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_COIN);
        onCreate(sqLiteDatabase);
    }

    public void addCoin(PortfolioCoinData coinData) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, coinData.getId());
        values.put(KEY_SYMBOL, coinData.getSymbol());
        values.put(KEY_NAME, coinData.getName());
        values.put(KEY_BUY_PRICE, coinData.getBuy_price());
        values.put(KEY_BUY_AMOUNT, coinData.getBuy_amount());
        values.put(KEY_BUY_QUANTITY, coinData.getBuy_quantity());
        values.put(KEY_LAST_CHANGE, "0");

        db.insert(TABLE_COIN, null, values);
        db.close();
    }

    public List<PortfolioCoinData> getAllCoins() {
        List<PortfolioCoinData> coinList = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_COIN;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                PortfolioCoinData coin = new PortfolioCoinData();
                coin.setId(cursor.getString(0));
                coin.setSymbol(cursor.getString(1));
                coin.setName(cursor.getString(2));
                coin.setBuy_price(cursor.getString(3));
                coin.setBuy_amount(cursor.getString(4));
                coin.setBuy_quantity(cursor.getString(5));
                coin.setLast_change(cursor.getString(6));

                coinList.add(coin);
            } while (cursor.moveToNext());
        }

        return coinList;
    }

    public int getCoinCount() {
        String countQuery = "SELECT  * FROM " + TABLE_COIN;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        // return count
        return count;
    }

    public void deleteCoin(PortfolioCoinData coinData) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_COIN, KEY_SYMBOL + " = ?",
                new String[] { String.valueOf(coinData.getSymbol()) });
        db.close();
    }
}
