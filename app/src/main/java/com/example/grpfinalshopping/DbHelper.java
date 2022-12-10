package com.example.grpfinalshopping;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "grocery_db";
    private static final int DB_VERSION = 1;

    private static final String USER_TABLE_NAME = "user";
    private static final String ORDER_TABLE_NAME = "orders"; //looks like "order" is a reserved word, so I decided to go with plural
    private static final String ORDER_ITEM_TABLE_NAME = "order_item";

    // in common columns
    private static final String ID_COL = "id";

    // user table specific columns
    private static final String USER_FULL_NAME_COL = "full_name";
    private static final String USER_PHONE_NUMBER_COL = "phone_number";
    private static final String USER_STREET_ADDRESS_COL = "street_address";
    private static final String USER_CITY_COL = "city";
    private static final String USER_PROVINCE_COL = "province";
    private static final String USER_POSTAL_CODE_COL = "postal_code";

    // order table specific columns
    private static final String ORDER_USER_ID_COL = "user_id";
    private static final String ORDER_SHIPPING_ADDRESS_COL = "shipping_address";
    private static final String ORDER_DATE_COL = "order_date";

    // order_item table specific columns
    private static final String ORDER_ITEM_ORDER_ID_COL = "order_id";
    private static final String ORDER_ITEM_PRODUCT_ID_COL = "product_id";
    private static final String ORDER_ITEM_PRICE_PER_UNIT_COL = "price_per_unit";
    private static final String ORDER_ITEM_QUANTITY_COL = "quantity";



    public DbHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE " + USER_TABLE_NAME + "(" +
                ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                USER_FULL_NAME_COL + " TEXT," +
                USER_PHONE_NUMBER_COL + " TEXT," +
                USER_STREET_ADDRESS_COL + " TEXT," +
                USER_CITY_COL + " TEXT," +
                USER_PROVINCE_COL + " TEXT," +
                USER_POSTAL_CODE_COL + " TEXT" + ")"
        );

        sqLiteDatabase.execSQL("CREATE TABLE " + ORDER_TABLE_NAME + "(" +
                ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ORDER_USER_ID_COL + " INTEGER REFERENCES " + USER_TABLE_NAME + "(" + ID_COL + ")," +
                ORDER_SHIPPING_ADDRESS_COL + " TEXT," +
                ORDER_DATE_COL + " TEXT" + ")"
        );

        sqLiteDatabase.execSQL("CREATE TABLE " + ORDER_ITEM_TABLE_NAME + "(" +
                ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ORDER_ITEM_ORDER_ID_COL + " INTEGER REFERENCES " + ORDER_TABLE_NAME + "(" + ID_COL + ")," +
                ORDER_ITEM_PRODUCT_ID_COL + " INTEGER REFERENCES " + USER_TABLE_NAME + "(" + ID_COL + ")," +
                ORDER_ITEM_PRICE_PER_UNIT_COL + " TEXT" + ")"
        );


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
