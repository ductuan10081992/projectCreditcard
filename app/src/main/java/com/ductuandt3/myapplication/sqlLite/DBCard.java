package com.ductuandt3.myapplication.sqlLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.ductuandt3.myapplication.entity.CardEntity;

import java.util.ArrayList;
import java.util.List;

public class DBCard extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "CARD_MANAGER";
    private static final String TABLE_NAME = "cards";
    private static final String ID = "id";
    private static final String CARD_NUMBER = "cardNumber";
    private static final String EXPIRATION_MONTH = "expirationMonth";
    private static final String EXPIRATION_YEAR = "expirationYear";
    private static final String CVV_CARD = "cvvCard";
    private static final String POSTAL_CODE= "postalCode";
    private static final String COUNTRY_CODE = "countryCode";
    private static final String PHONE_NUMBER = "phonrNumber";
    private static final String DATE = "date";
    private static int VERSION = 1;

    private Context context;

    private String SQLQuery = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" + ID  + " INTEGER PRIMARY KEY, " +
            CARD_NUMBER + " VARCHAR(200), " +
            EXPIRATION_MONTH + " VARCHAR(200), " +
            EXPIRATION_YEAR + " VARCHAR(200), " +
            CVV_CARD + " VARCHAR(200), " +
            POSTAL_CODE + " VARCHAR(200), " +
            COUNTRY_CODE + " VARCHAR(200), " +
            PHONE_NUMBER + " VARCHAR(200), " +
            DATE + " VARCHAR(200) )";


    public DBCard(Context context){
        super(context, DATABASE_NAME, null, VERSION);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
//        sqLiteDatabase.execSQL(SQLQuery);
//        Log.d("LDT", "onCreate: sucess");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void  showMessage(){
        Toast.makeText(context, "Hello Le Duc Tuan ", Toast.LENGTH_SHORT).show();
    }

    public void  createTable(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.execSQL(SQLQuery);
        Log.d("LDT", "onCreate SQLQuery : sucess");
    }

    public void addCard(CardEntity cardEntity){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CARD_NUMBER, cardEntity.getmCardNumber());
        values.put(EXPIRATION_MONTH, cardEntity.getmExpirationMonth());
        values.put(EXPIRATION_YEAR, cardEntity.getmExpirationYear());
        values.put(CVV_CARD, cardEntity.getmCVVCard());
        values.put(POSTAL_CODE, cardEntity.getmPostalCode());
        values.put(COUNTRY_CODE, cardEntity.getmContryCode());
        values.put(PHONE_NUMBER, cardEntity.getmPhoneNumber());
        values.put(DATE, cardEntity.getmDate());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public ArrayList<CardEntity> getAllCardList() {
        ArrayList<CardEntity> listCard = new ArrayList<CardEntity>();
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                CardEntity cardEntity = new CardEntity();
                cardEntity.setmCardNumber(cursor.getString(1));
                cardEntity.setmExpirationMonth(cursor.getString(2));
                cardEntity.setmExpirationYear(cursor.getString(3));
                cardEntity.setmCVVCard(cursor.getString(4));
                cardEntity.setmPostalCode(cursor.getString(5));
                cardEntity.setmContryCode(cursor.getString(6));
                cardEntity.setmPhoneNumber(cursor.getString(7));
                cardEntity.setmDate(cursor.getString(8));
                listCard.add(cardEntity);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return listCard;
    }

    public void deleteTable(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.close();
    }
}
