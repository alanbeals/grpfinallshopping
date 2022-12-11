package com.example.grpfinalshopping;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Date;

import entities.Product;

public class DbHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "grocery_db";
    private static final int DB_VERSION = 1;

    private static final String USER_TABLE = "user";
    private static final String ORDER_TABLE = "orders"; //looks like "order" is a reserved word, so I decided to go with plural
    private static final String ORDER_ITEM_TABLE = "order_item";
    private static final String PRODUCT_TABLE = "product";

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

    // product table specific columns
    private static final String PRODUCT_NAME_COL = "name";
    private static final String PRODUCT_PRICE_COL = "price";

    public DbHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE " + USER_TABLE + "(" +
                ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                USER_FULL_NAME_COL + " TEXT NOT NULL," +
                USER_PHONE_NUMBER_COL + " TEXT NOT NULL," +
                USER_STREET_ADDRESS_COL + " TEXT NOT NULL," +
                USER_CITY_COL + " TEXT NOT NULL," +
                USER_PROVINCE_COL + " TEXT NOT NULL," +
                USER_POSTAL_CODE_COL + " TEXT NOT NULL" + ")"
        );

        sqLiteDatabase.execSQL("CREATE TABLE " + ORDER_TABLE + "(" +
                ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ORDER_USER_ID_COL + " INTEGER NOT NULL REFERENCES " + USER_TABLE + "(" + ID_COL + ")," +
                ORDER_SHIPPING_ADDRESS_COL + " TEXT NOT NULL," +  //this will be the user's address info compiled, comma delimited
                ORDER_DATE_COL + " TEXT" + ")" // order date can be null, thich means order is not yet completed and items are on the cart
        );

        sqLiteDatabase.execSQL("CREATE TABLE " + ORDER_ITEM_TABLE + "(" +
                ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ORDER_ITEM_ORDER_ID_COL + " INTEGER REFERENCES " + ORDER_TABLE + "(" + ID_COL + ")," +
                ORDER_ITEM_PRODUCT_ID_COL + " INTEGER REFERENCES " + PRODUCT_TABLE + "(" + ID_COL + ")," +
                ORDER_ITEM_PRICE_PER_UNIT_COL + " REAL," +
                ORDER_ITEM_QUANTITY_COL + " INTEGER" + ")"
        );

        sqLiteDatabase.execSQL("CREATE TABLE " + PRODUCT_TABLE + "(" +
                ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                PRODUCT_NAME_COL + " TEXT NOT NULL," +
                PRODUCT_PRICE_COL + " REAL" + ")"
        );

    }

    public void addProduct(String name, double price)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(PRODUCT_NAME_COL, name);
        values.put(PRODUCT_PRICE_COL, price);

        db.insert(PRODUCT_TABLE, null, values);
        db.close();
    }
    public ArrayList<Product> getAllProducts()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        Cursor cr = db.rawQuery("SELECT " + PRODUCT_NAME_COL + ", " + PRODUCT_PRICE_COL
                + " FROM " + PRODUCT_TABLE
                , null);

        ArrayList<Product> productArrayList = new ArrayList<>();

        cr.moveToFirst();
        if(cr.getCount() > 0)
        {
            do
            {
                productArrayList.add(new Product(cr.getString(0),cr.getDouble(1)));
            } while (cr.moveToNext());
        }

        db.close();
        return productArrayList;
    }



    public void addUser(String fullName, String phoneNumber, String streetAddress, String city,
                              String province, String postalCode)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(USER_FULL_NAME_COL, fullName);
        values.put(USER_PHONE_NUMBER_COL, phoneNumber);
        values.put(USER_STREET_ADDRESS_COL, streetAddress);
        values.put(USER_CITY_COL, city);
        values.put(USER_PROVINCE_COL, province);
        values.put(USER_POSTAL_CODE_COL, postalCode);

        db.insert(USER_TABLE, null, values);
        db.close();
    }

    public void updateUserProfile(int id, String fullName, String phoneNumber, String streetAddress, String city,
                              String province, String postalCode)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(USER_FULL_NAME_COL, fullName);
        values.put(USER_PHONE_NUMBER_COL, phoneNumber);
        values.put(USER_STREET_ADDRESS_COL, streetAddress);
        values.put(USER_CITY_COL, city);
        values.put(USER_PROVINCE_COL, province);
        values.put(USER_POSTAL_CODE_COL, postalCode);

        db.update(USER_TABLE, values,"id=?", new String[]{String.valueOf(id)});
        db.close();
    }

    public void addToCart(int userId, int productId)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        int orderId = getIncompleteOrderId(userId);

        if(orderId == -1)
        {
            orderId = addNewOrder(userId);
        }

        int orderItemId = getOrderItemId(orderId, productId);

        if(orderItemId != -1)
        {
            String sql = "UPDATE " + ORDER_ITEM_TABLE
                    + " SET " + ORDER_ITEM_QUANTITY_COL + " = " + ORDER_ITEM_QUANTITY_COL + " + 1"
                    + " WHERE " + ID_COL + " = " + orderItemId;
            db.execSQL(sql);
        }
        else
        {
            addNewOrderItem(orderId, productId);
        }

        db.close();
    }


    private int getIncompleteOrderId (int userId)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cr = db.rawQuery("SELECT " + ID_COL + " FROM " + ORDER_TABLE + " WHERE " + ORDER_USER_ID_COL + "=" + userId + " AND " + ORDER_DATE_COL + " IS NULL", null);

        cr.moveToFirst();

        int orderId = cr.getCount() == 0
                ? -1
                : cr.getInt(0);

        return orderId;
    }

    private int addNewOrder(int userId)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String shipping_address = getUserAddress(userId);

        values.put(ORDER_USER_ID_COL, userId);
        values.put(ORDER_SHIPPING_ADDRESS_COL, shipping_address);

        db.insert(ORDER_TABLE, null, values);

        return getIncompleteOrderId(userId);
    }

    // get concatenated user address
    private String getUserAddress(int userId)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cr = db.rawQuery("SELECT "
                        + USER_FULL_NAME_COL + " || ', ' || "
                        + USER_PHONE_NUMBER_COL + " || ', ' || "
                        + USER_STREET_ADDRESS_COL  + " || ', ' || "
                        + USER_CITY_COL  + " || ', ' || "
                        + USER_PROVINCE_COL  + " || ', ' || "
                        + USER_POSTAL_CODE_COL
                        + " FROM " + USER_TABLE + " WHERE " + ID_COL + "=" + userId
                ,null);

        cr.moveToFirst();

        String userAddress = cr.getCount() > 0
                ? cr.getString(0)
                : "no address found";

        return userAddress;
    }

    private int getOrderItemId (int orderId, int productId)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cr = db.rawQuery("SELECT " + ID_COL + " FROM " + ORDER_ITEM_TABLE
                        + " WHERE "
                        + ORDER_ITEM_ORDER_ID_COL + "=" + orderId
                        + " AND "
                        + ORDER_ITEM_PRODUCT_ID_COL + "=" + productId
                , null);

        cr.moveToFirst();

        int orderItemId = cr.getCount() == 0
                ? -1
                : cr.getInt(0);

        return orderItemId;
    }

    private double getProductPrice(int productId)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cr = db.rawQuery("SELECT " + PRODUCT_PRICE_COL + " FROM " + PRODUCT_TABLE
                        + " WHERE "
                        + ID_COL + "=" + productId
                , null);

        cr.moveToFirst();

        double productPrice = cr.getCount() == 0
                ? 0.00
                : cr.getDouble(0);

        return productPrice;

    }

    private void addNewOrderItem(int orderId, int productId)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        double productPrice = getProductPrice(productId);

        values.put(ORDER_ITEM_ORDER_ID_COL, orderId);
        values.put(ORDER_ITEM_PRODUCT_ID_COL, productId);
        values.put(ORDER_ITEM_PRICE_PER_UNIT_COL, productPrice);
        values.put(ORDER_ITEM_QUANTITY_COL, 1);

        db.insert(ORDER_ITEM_TABLE, null, values);
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
